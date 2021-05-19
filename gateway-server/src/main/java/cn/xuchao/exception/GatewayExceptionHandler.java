package cn.xuchao.exception;

import cn.xuchao.base.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xkhy
 */
@Slf4j
public class GatewayExceptionHandler extends DefaultErrorWebExceptionHandler {

    public GatewayExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 封装异常属性
     *
     * @param request request
     * @param options options
     * @return /
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable exception = super.getError(request);
        return error(exception);
    }

    /**
     * 返回路由方法
     *
     * @param errorAttributes errorAttributes
     * @return /
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * http响应码
     *
     * @param errorAttributes errorAttributes
     * @return /
     */
    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        return (int) errorAttributes.get("code");
    }

    private Map<String, Object> error(Throwable exception) {
        String message;
        int code;
        if (exception instanceof NotFoundException) {
            message = ((NotFoundException) exception).getReason();
            code = HttpStatus.SERVICE_UNAVAILABLE.value();
        } else if (exception instanceof ResponseStatusException) {
            message = ((ResponseStatusException) exception).getMessage();
            code = ((ResponseStatusException) exception).getStatus().value();
        } else if (exception instanceof BaseException) {
            message = exception.getMessage();
            code = ((BaseException) exception).getCode();
        } else {
            message = exception.getMessage();
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        Map<String, Object> response = new HashMap<>(4);
        response.put("code", code);
        response.put("message", message);
        response.put("data", null);
        return response;
    }

}
