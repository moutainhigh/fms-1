package org.fms.auth.provider.pojo;

public enum ResponseCode {

    SUCCESS(200, "success."),
    ERROR(1001, "error."),
    PARA_ERROR(1002, "parameters error."),
    TOKEN_ERROR(1003, "token error.");

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
