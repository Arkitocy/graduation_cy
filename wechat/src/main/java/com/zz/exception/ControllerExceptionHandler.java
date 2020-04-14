package com.zz.exception;

import com.zz.vo.ResultVO;
import com.zz.util.ResultVoUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.NonUniqueResultException;

/**
 * 统一异常处理
 * @ControllerAdvice
 * @author 小蜜蜂
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 返回json的错误信息 NonUniqueResultException
     * @param e
     * @return
     */
    @ExceptionHandler(NonUniqueResultException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleUserNotExistsException(NonUniqueResultException e) {
//        Map<String, Object> map = new HashMap<>(16);
//        map.put("detail", "查询不唯一");
//        map.put("message", e.getMessage());
        return ResultVoUtil.error(500,e.getMessage());
    }

    @ExceptionHandler(DataValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handleDataValidationException(DataValidationException e) {
        return ResultVoUtil.error(500,"输入信息验证错误---"+e.getMessage());
    }

}
