package moe.cnkirito.security.oauth2.code.enums;

/**
 * description: 自定义业务异常枚举，所有的都可以放到这里， 通过
 *
 * @author: 华仔
 * @date: 2020/5/9
 */
public enum ErrorEnum {
    CREATE_JOB_FAILE(500, "创建任务失败"),
    EXECUTE_JOB_FAILE(500, "执行任务失败"),
    DELETE_JOB_FAILE(500, "执行任务失败"),
    RESUME_JOB_FAILE(500, "执行任务失败"),
    PAUSE_JOB_FAILE(500, "暂停任务失败"),
    UPGREADE_JOB_FAILE(500, "更新定时任务失败"),
    GET_CRON_TRIGGER_EXCEPTION(600, "获取crontrigger异常");

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
