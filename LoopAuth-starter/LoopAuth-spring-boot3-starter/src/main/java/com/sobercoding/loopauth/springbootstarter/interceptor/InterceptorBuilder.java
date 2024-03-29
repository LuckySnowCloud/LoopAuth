package com.sobercoding.loopauth.springbootstarter.interceptor;

import com.sobercoding.loopauth.abac.annotation.CheckAbacAnnotation;
import com.sobercoding.loopauth.abac.carryout.LoopAuthAbac;
import com.sobercoding.loopauth.rbac.annotation.CheckRbacAnnotation;
import com.sobercoding.loopauth.session.annotation.CheckSessionAnnotation;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

/**
 * 拦截器组装类
 * @author Sober
 */
public class InterceptorBuilder {

    /**
     * 拦截器
     */
    LoopAuthInterceptor loopAuthInterceptor = new LoopAuthInterceptor();

    /**
     * abac方式
     * @return
     */
    public Builder abac() {
        loopAuthInterceptor.function = (req, res, handler) -> {
            // 路由
            String route = req.getRequestPath();
            // ABAC鉴权
            LoopAuthAbac.check(route, req.getMethod());
        };
        return new Builder();
    }

    /**
     * 注解方式
     * @return
     */
    public Builder annotation() {
        loopAuthInterceptor.function = (req, res, handler) -> {
            // 获取处理method
            if (handler instanceof HandlerMethod) {
                Method method = ((HandlerMethod) handler).getMethod();
                // 进行验证
                CheckSessionAnnotation.checkMethodAnnotation.accept(method);
                // 进行验证
                CheckRbacAnnotation.checkMethodAnnotation.accept(method);
                // 进行验证
                CheckAbacAnnotation.checkMethodAnnotation.accept(method);
            }
        };
        return new Builder();
    }

    public class Builder {
        /**
         * 构造器
         * @return com.sobercoding.loopauth.springbootstarter.interceptor.LoopAuthInterceptor
         */
        public LoopAuthInterceptor builder() {
            return loopAuthInterceptor;
        }
    }

}
