package com.sobercoding.loopauth.springbootstarter;

import com.sobercoding.loopauth.LoopAuthStrategy;
import com.sobercoding.loopauth.config.LoopAuthConfig;
import com.sobercoding.loopauth.context.LoopAuthContext;
import com.sobercoding.loopauth.dao.LoopAuthDao;
import com.sobercoding.loopauth.permission.PermissionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;


/**
 * @program: LoopAuth
 * @author: Sober
 * @Description:
 * @create: 2022/07/30 22:57
 */
public class LoopAuthInject {

    /**
     * @Method: setLoopAuthConfig
     * @Author: Sober
     * @Version: 0.0.1
     * @Description: 注入loopAuthConfig到core
     * @param loopAuthConfig 配置对象
     * @Return:
     * @Exception:
     * @Date: 2022/7/31 0:00
     */
    @Autowired(required = false)
    public void setLoopAuthConfig(LoopAuthConfig loopAuthConfig) {
        LoopAuthStrategy.setLoopAuthConfig(loopAuthConfig);
    }

    /**
     * @Method: setLoopAuthContext
     * @Author: Sober
     * @Version: 0.0.1
     * @Description: 注入上下文
     * @param loopAuthContext 上下文
     * @Return: void
     * @Exception:
     * @Date: 2022/8/10 18:44
     */
    @Autowired(required = false)
    public void setLoopAuthContext(LoopAuthContext loopAuthContext){
        LoopAuthStrategy.setLoopAuthContext(loopAuthContext);
    }

    /**
     * 注入权限认证Bean
     *
     */
    @Autowired(required = false)
    public void setPermissionInterface(PermissionInterface permissionInterface) {
        LoopAuthStrategy.setPermissionInterface(permissionInterface);
    }

    /**
     * 注入持久层
     *
     */
    @Autowired(required = false)
    public void setLoopAuthDao(LoopAuthDao loopAuthDao) {
        LoopAuthStrategy.setLoopAuthDao(loopAuthDao);
    }

}