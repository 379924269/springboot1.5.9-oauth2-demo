package moe.cnkirito.security.oauth2.code.exception.handler;

import moe.cnkirito.security.oauth2.code.constant.ExceptionConst;
import moe.cnkirito.security.oauth2.code.exception.BusinessException;
import moe.cnkirito.security.oauth2.code.tips.ErrorTip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.text.MessageFormat;
import java.util.Set;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author 华仔
 * @date 2017年8月17日10:16:22
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorTip exception(Exception e) {
        e.printStackTrace();
        logger.error("服务器未处理异常：{}", e.getMessage());
        return new ErrorTip(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public ErrorTip exception(BusinessException e, HttpServletResponse res) {
        res.setStatus(1000);
        logger.info("自定义业务异常：{}", e.getMessage());
        return new ErrorTip(1000, e.getMessage());
    }


    /**
     * 405 - Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorTip httpRequestMethodNotSupportedException(Exception e) {
        logger.error("请求方法不允许:{}", e.getMessage());
        return new ErrorTip(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorTip handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数:{}", e.getMessage());
        return new ErrorTip(HttpStatus.BAD_REQUEST.value(), MessageFormat.format("缺少请求参数：{0}", e.getMessage()));
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorTip handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败:{}", e.getMessage());
        return new ErrorTip(HttpStatus.BAD_REQUEST.value(), MessageFormat.format("参数解析失败：{0}", e.getMessage()));
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorTip handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败:{}", e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return new ErrorTip(HttpStatus.BAD_REQUEST.value(), MessageFormat.format("参数验证失败：{0}", message));
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorTip handleBindException(BindException e) {
        logger.error("参数绑定失败:{}", e.getMessage());
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return new ErrorTip(HttpStatus.BAD_REQUEST.value(), MessageFormat.format("参数绑定失败,字段：{0}", message));
    }


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorTip handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败:{}", e.getMessage());
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return new ErrorTip(HttpStatus.BAD_REQUEST.value(), MessageFormat.format("参数验证失败{0}", message));
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorTip handleValidationException(ValidationException e) {
        logger.error("参数验证失败:{}", e.getMessage());
        return new ErrorTip(HttpStatus.BAD_REQUEST.value(), MessageFormat.format("参数验证失败{0}", e.getMessage()));
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorTip methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.error("请求参数类型转换异常：{}", e.getMessage());
        return new ErrorTip(HttpStatus.BAD_REQUEST.value(), MessageFormat.format("请求参数类型转换异常：{0}", e.getMessage()));
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorTip noHandlerFoundException(NoHandlerFoundException e) {
        logger.error("Not Found:{}", e.getMessage());
        return new ErrorTip(HttpStatus.NOT_FOUND.value(), "Not Found");
    }

    /**
     * 1000 - 处理字段重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorTip duplicateKeyException(DuplicateKeyException e, HttpServletResponse res) {
        String msg = e.getCause().getMessage();
        String field = msg.substring(msg.indexOf("key") + 3).trim().replace("'", "");
        String value = msg.substring(msg.indexOf("entry") + 5, msg.indexOf("for")).trim().replace("'", "");
        res.setStatus(ExceptionConst.CUSTOM_EXCEPTION_CODE);
        switch (field) {
            case "name":
                field = "名称";
                break;
            case "phone":
                field = "电话号码";
                break;
            case "unionid":
                field = "名称号码md5";
                break;
            case "sn":
                field = "患者编号";
                break;
        }
        return new ErrorTip(ExceptionConst.CUSTOM_EXCEPTION_CODE, MessageFormat.format("{0}【{1}】已存在", field, value));
    }
}
