package moe.cnkirito.security.oauth2.code.tips;


import moe.cnkirito.security.oauth2.code.enums.ErrorEnum;

/**
 * 返回给前台的错误提示
 *
 * @author 华仔
 * @date 2017年8月17日10:18:46
 */
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ErrorTip(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }
}
