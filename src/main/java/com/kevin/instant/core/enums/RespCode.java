package com.kevin.instant.core.enums;

/**
 * 响应码枚举<br>
 * @author Kevin on 2019年3月1日<br>
 */

public enum RespCode {

    /**
     * 成功处理业务逻辑
     */
    SUCCESS(200),

    /**
     * 业务逻辑处理失败
     */
    FAIL(400),

    /**
     * 未认证（签名错误,TOKEN错误）
     */
    UNAUTHORIZED(401),

    /**
     * 未认证（用户未登录）
     */
    UNAUTHENTICATION(402),

    /**
     * 没有资源访问授权
     */
    UNAUTHORIZATION(403),

    /**
     * 接口不存在
     */
    NOT_FOUND(404),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500),

    /**
     * 表明支付订单未支付
     */
    ORDERNOTPAID(600),

    /**
     * 表明支付订单已支付
     */
    ORDERPAID(601),

    /**
     * 用户信息未设置
     */
    USER_INFO_NOT_SET(700);

    private int code;

    RespCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
