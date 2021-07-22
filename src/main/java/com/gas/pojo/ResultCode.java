package com.gas.pojo;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.pojo
 * @ClassName: ResultCode
 * @Author: gwl
 * @Description:
 * @Date: 2020/7/29 16:13
 * @Version: 1.0
 */
public enum ResultCode {

    /**
     * 空问题
     */
    PARAM_NOT_NULL(101, "请求参数不能为空!"),
    ID_NOT_NULL(102, "ID不能为空!"),
    IDS_NOT_NULL(103, "IDS数组不能为空!"),
    USERNAME_NOT_NULL(104, "用户名不能为空!"),
    PWD_NOT_NULL(105, "密码不能为空!"),
    KAPTCHA_NOT_NULL(106, "验证码不能为空!"),
    KAPTCHA_ERROR(107, "验证码错误!"),


    /**
     * 不合法问题
     */
    TOKEN_NOLEGAL(201, "不合法的token或token过期!"),
    DATE_NOLEGAL(202, "不合法的日期格式!"),
    REQ_NOLEGAL(203, "不合法的请求参数!"),
    FILE_SIZE_NOLEGAL(204, "不合法的文件大小!"),



    /**
     * 权限问题
     */
    NOT_PERMISSION(403, "无资源访问权限!"),
    ILLEGAL_INVASION(444, "禁止非法入侵！！!"),
    REPEAT_LOGIN(445, "该用户已登录，是否继续登录？"),

    /**
     * 账户问题
     */
    USER_ALREADY_REG(408, "该用户已经注册!"),
    NO_THIS_USER(409, "没有此用户!"),
    USER_NOT_EXISTED(410, "没有此用户!"),
    ACCOUNT_FREEZED(401, "账号被冻结!"),
    OLD_PWD_NOT_RIGHT(402, "原密码不正确!"),
    TWO_PWD_NOT_MATCH(407, "两次输入密码不一致!"),
    USER_PWD_ERROR(406, "用户名或密码不对!!"),
    LOGIN_SUCCESS(168, "登录成功!!"),
    CACHE_EXPIRATION(405, "用户缓存失败!!"),

    /**
     * 增删改查
     */
    QUERY_SUCCESS(501, "查询成功!"),
    QUERY_ERROR(502, "查询失败!"),
    INSERT_SUCCESS(503, "保存成功!"),
    INSERT_ERROR(504, "保存失败!"),
    DELETE_SUCCESS(505, "删除成功!"),
    DELETE_ERROR(506, "删除失败!"),
    UPDATE_SUCCESS(507, "更新成功!"),
    UPDATE_ERROR(508, "更新失败!"),
    BATCH_DELETE_SUCCESS(509, "批量删除成功!"),
    BATCH_DELETE_ERROR(510, "批量删除失败!"),
    BATCH_INSERT_SUCCESS(511,"批量添加成功!"),
    BATCH_INSERT_ERROR(512,"批量添加失败!"),
    BATCH_UPDATE_SUCCESS(513, "批量更新成功!"),
    BATCH_UPDATE_ERROR(514, "批量更新失败!"),
    AUDIT_SUCCESSFUL(515, "审核成功!"),
    AUDIT_ERROR(516, "审核失败!"),
    IMG_SUCCESS(517, "图片上传成功"),
    IMG_ERROR(518, "图片上传失败"),

    /**
     * 请求状态
     */
    SYS_SUCCESS(200, "请求成功!"),
    SYS_ERROR(400, "请求失败!"),
    NOT_FOUND(404, "接口不存在!"),

    /**
     * 请求状态
     */
    TOP_UP_SUCCESS(701, "充值成功!"),
    TOP_UP_ERROR(702, "充值失败!"),
    CONSUME_SUCCESS(703, "消费成功!"),
    CONSUME_ERROR(704, "消费失败!"),

    /**
     * 错误的请求
     */
    SERVER_ERROR(500, "系统繁忙，请稍候再试!"),





    /**
     * 服务熔断降级
     */
    SERVICE_EXCEPTION(603,"服务暂不可用！请稍后尝试或联系管理员！！"),

    /**
     * 其它问题
     * */
    REPEAT_ERROR(901,"重复领取！");


    ResultCode(int resCode, String resMsg) {
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    /**
     * 错误信息
     */
    private String resMsg;
    /**
     * 错误码 0：成功 非0：失败
     */
    private Integer resCode;

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

}
