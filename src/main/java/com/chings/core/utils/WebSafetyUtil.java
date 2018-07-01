package com.chings.core.utils;

import java.util.UUID;

/**
 * @Author 网络安全工具
 * @Date 2018/7/1
 */
public class WebSafetyUtil {

    public static String getRandomToken(){
        return UUID.randomUUID().toString();
    }

}
