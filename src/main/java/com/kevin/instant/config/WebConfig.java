package com.kevin.instant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: Instant
 * @create: 2020-04-26 22:37
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@Component
@ConfigurationProperties(prefix = "file-service")
public class WebConfig {

    /**
     * 上传路径
     */
    private static String profile;

    public static String getProfile() {
        return profile;
    }

    public static void setProfile() {
        WebConfig.profile = profile;
    }

    // 获取上传头像路径
    public static String getAvatarPath() {
        return profile + "avatar/";
    }

    // 获取下载路径
    public static String getDownloadPath() {
        return profile + "download/";
    }

    // 获取上传路径
    public static String getUploadPath() {
        return profile + "upload/";
    }

}
