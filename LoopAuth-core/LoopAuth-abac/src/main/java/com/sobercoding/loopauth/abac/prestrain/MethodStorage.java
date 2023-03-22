package com.sobercoding.loopauth.abac.prestrain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法存储
 * @author Sober
 */
public class MethodStorage {

    /**
     * 验证方法
     */
    private final Map<String, Object> verifyMap = new ConcurrentHashMap<>();

    /**
     * 属性方法
     */
    private final Map<String, Object> propertyMap = new ConcurrentHashMap<>();

    /**
     * 载入验证方法
     * @param key
     * @param func
     */
    public void putVerify(String key, Object func) {
        verifyMap.put(key, func);
    }

    /**
     * 载入属性方法
     * @param key
     * @param func
     */
    public void putProperty(String key, Object func) {
        propertyMap.put(key, func);
    }

    /**
     * get
     * @return
     */
    public Map<String, Object> getVerifyMap() {
        return verifyMap;
    }

    /**
     * get
     * @return
     */
    public Map<String, Object> getPropertyMap() {
        return propertyMap;
    }
}
