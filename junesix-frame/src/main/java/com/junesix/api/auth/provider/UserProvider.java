/**
 *
 */
package com.junesix.api.auth.provider;


import com.junesix.api.auth.model.AuthRequest;

/**
 * @author jolestar@gmail.com
 */
public interface UserProvider {

    /**
     * 是否为有效用户
     *
     * @param uid
     * @return
     */
    public boolean isValidUser(long uid);

    /**
     * 该用户是否有权限访问
     *
     * @param request
     * @param uid
     */
    boolean checkCanAccess(AuthRequest request, long uid);


    /**
     * 通过用户名密码认证用户
     *
     * @param loginName     不能为空
     * @param password      不能为空
     * @param ip
     * @return uid
     */
    public long authUser(String loginName, String password, String ip);

}
