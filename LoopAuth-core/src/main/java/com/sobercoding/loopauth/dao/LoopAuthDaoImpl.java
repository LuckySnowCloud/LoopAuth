package com.sobercoding.loopauth.dao;

import com.sobercoding.loopauth.exception.LoopAuthLoginException;
import com.sobercoding.loopauth.model.TokenModel;
import com.sobercoding.loopauth.model.UserSession;
import com.sobercoding.loopauth.util.JsonUtil;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: LoopAuth
 * @author: Sober
 * @Description:
 * @create: 2022/07/20 23:35
 */
public class LoopAuthDaoImpl implements LoopAuthDao {


    /**
     * 登录状态缓存
     * 用户id 和 Token模型 键值对应
     * 等于缓存UserSession类数据 但是都序列化成String了
     */
    private Map<String, String> userSessions = new ConcurrentHashMap<>();


    /**
     * @Method: getUserSession
     * @Author: Sober
     * @Version: 0.0.1
     * @Description: 获取用户会话
     * @param userId 用户id
     * @Return:
     * @Exception:
     * @Date: 2022/8/8 17:16
     */
    public UserSession getUserSession(String userId) {
        String tokens = LoopAuthLoginException.notLogin(this.userSessions.get(userId));
        Set<TokenModel> tokenModelSet = JsonUtil.jsonToList(tokens,TokenModel.class);
        return new UserSession()
                .setUserId(userId)
                .setTokens(tokenModelSet);
    }

    /**
     * @Method: setUserSession
     * @Author: Sober
     * @Version: 0.0.1
     * @Description: 写入用户会话
     * @param userSession 会话模型
     * @Return:
     * @Exception:
     * @Date: 2022/8/8 17:16
     */
    public void setUserSession(UserSession userSession) {
        String json = JsonUtil.objToJson(userSession.getTokens());
        this.userSessions.put(
                userSession.getUserId(),
                json
        );
    }

    /**
     * @Method: setUserSession
     * @Author: Sober
     * @Version: 0.0.1
     * @Description: 清空登录状态
     * @param userId 用户id
     * @Return:
     * @Exception:
     * @Date: 2022/8/8 17:16
     */
    @Override
    public void removeUserSession(String userId) {
        this.userSessions.remove(userId);
    }
}
