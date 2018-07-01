package com.chings.core.common.inter;

/**
 * @Author Administrator
 * @Date 2018/7/1
 */
public interface Token {

    /**
     * 生成token
     * @return
     */
    String createToken();

    /**
     * 存储token
     * @return
     */
    boolean storeToken(String token);

    /**
     * 验证token
     * @return
     */
    boolean vertifyToken(String token);

}
