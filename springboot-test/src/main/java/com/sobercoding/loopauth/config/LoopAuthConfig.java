package com.sobercoding.loopauth.config;

import com.sobercoding.loopauth.context.LoopAuthContextForSpring;
import com.sobercoding.loopauth.session.SessionStrategy;
import com.sobercoding.loopauth.session.config.CookieConfig;
import com.sobercoding.loopauth.session.config.RedisConfig;
import com.sobercoding.loopauth.session.config.SessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sober
 */
@Configuration
public class LoopAuthConfig {


    @Bean
    @ConfigurationProperties(prefix = "loop-auth.session")
    public SessionConfig getSessionConfig() {
        return new SessionConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "loop-auth.session.redis")
    public RedisConfig getRedisConfig() {
        return new RedisConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "loop-auth.session.cookie")
    public CookieConfig getCookieConfig() {
        return new CookieConfig();
    }

    @Autowired(required = false)
    public void setLoopAuthConfig(SessionConfig loopAuthConfig,
                                  RedisConfig redisConfig,
                                  CookieConfig cookieConfig) {
        SessionStrategy.setSessionConfig(loopAuthConfig);
        SessionStrategy.setRedisConfig(redisConfig);
        SessionStrategy.setCookieConfig(cookieConfig);
        SessionStrategy.setLoopAuthContext(new LoopAuthContextForSpring());
    }



}