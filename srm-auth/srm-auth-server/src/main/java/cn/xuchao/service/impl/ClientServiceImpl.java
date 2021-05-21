package cn.xuchao.service.impl;

import cn.xuchao.entity.Client;
import cn.xuchao.mapper.ClientMapper;
import cn.xuchao.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xkhy
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService{
}
