package cn.xuchao;

import cn.xuchao.base.BaseResponse;
import cn.xuchao.request.FindByUsernameRequest;
import cn.xuchao.response.FindByUsernameResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuchao
 */
@FeignClient("srm-system-server")
@RequestMapping("/srm/sys/user")
public interface UserApi {

    /**
     * 根据用户名获取用户信息
     *
     * @param request request
     * @return response
     */
    @GetMapping("/findByUsername")
    BaseResponse<FindByUsernameResponse> findByUsername(FindByUsernameRequest request);

}
