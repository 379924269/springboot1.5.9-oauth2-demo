package moe.cnkirito.security.oauth2.code.tips;

import org.springframework.http.HttpStatus;

/**
 * 返回给前台的成功提示
 *
 * @author 华仔
 * @date 2017年8月17日10:18:46
 */
public class SuccessTip extends Tip {

    public SuccessTip() {
        super.code = HttpStatus.OK.value();
        super.message = "操作成功";
    }

    public SuccessTip(String msg) {
        super.code = HttpStatus.OK.value();
        super.message = msg;
    }
}
