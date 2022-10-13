package com.sobercoding.loopauth.config;

import com.sobercoding.loopauth.abac.AbacStrategy;
import com.sobercoding.loopauth.abac.model.authProperty.IntervalType;
import com.sobercoding.loopauth.abac.model.authProperty.TimeInterval;
import com.sobercoding.loopauth.abac.model.builder.AbacPolicyFunBuilder;
import com.sobercoding.loopauth.model.LoopAuthHttpMode;
import com.sobercoding.loopauth.model.LoopAuthVerifyMode;
import com.sobercoding.loopauth.servlet.filter.LoopAuthServletFilter;
import com.sobercoding.loopauth.session.carryout.LoopAuthSession;
import org.springframework.context.annotation.*;

/**
 * @program: LoopAuth
 * @author: Sober
 * @Description:
 * @create: 2022/08/09 15:22
 */
@Configuration
public class MyLoopAuthConfig {

    /**
     * 注册 [LoopAuth 全局过滤器] 此优先级高于  注解  如这里报错就不在进入注解
     */
    @Bean
    public LoopAuthServletFilter getSaServletFilter() {
        return new LoopAuthServletFilter()
                .addInclude("/**")
                .addExclude("/test/login", LoopAuthHttpMode.GET)
                // 认证函数: 每次请求执行
                .setLoopAuthFilter((isIntercept,route) -> {
                    if (isIntercept){
                        // 拦截
                        LoopAuthSession.isLogin();
                    }
                })
                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setLoopAuthErrorFilter(e -> {
                    e.printStackTrace();
                    return e.getMessage();
                });
    }

//    @Bean
//    public LoopAuthDao getLoopAuthDao() {
//        return new JedisDaoImpl();
//    }

    @Bean
    public void policyFun() {
        AbacStrategy.maFunctionMap = new AbacPolicyFunBuilder()
                .loginId()
                .loginIdNot()
                .role(LoopAuthVerifyMode.OR)
                .permission(LoopAuthVerifyMode.OR)
                // 时间区间范围内
                .setPolicyFun("timeSection",(value, rule) -> {
                    TimeInterval timeInterval = (TimeInterval) rule;
                    long newTime = IntervalType.NONE.creation();
                    return newTime > timeInterval.getStart() && newTime < timeInterval.getEnd();
                })
                .build();
    }

}
