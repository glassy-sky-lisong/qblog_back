package cn.quasar.blog.config;

import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandleConfig {

    @ExceptionHandler(SQLException.class)
    public MessageResult SQLException(SQLException e) {
        log.error(e.getMessage());
        return new MessageResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageStatus.FAIL.getStatus(),null, e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public MessageResult NullException(NullPointerException e) {
        log.error(e.getMessage());
        return new MessageResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageStatus.FAIL.getStatus(), null, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  MessageResult MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<String>();

        for (ObjectError error:
                e.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        log.error(errors.toString());
        return new MessageResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageStatus.FAIL.getStatus(), null, errors);
    }

    @ExceptionHandler(BindException.class)
    public MessageResult BindException(BindException e) {
        List<String> errors = new ArrayList<String>();

        for (ObjectError error:
                e.getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        log.error(errors.toString());
        return new MessageResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageStatus.FAIL.getStatus(), null, errors);
    }

    @ExceptionHandler(ValidationException.class)
    public MessageResult ValidationException(ValidationException e) {
        String s = e.getMessage();
        s =  s.substring(s.indexOf(":"));
        log.error(e.getMessage());
        return new MessageResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageStatus.FAIL.getStatus(), null, s);
    }

    @ExceptionHandler(CustomException.class)
    public MessageResult CustomException(CustomException e) {
        log.error(e.getMessage());
        return new MessageResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageStatus.FAIL.getStatus(), null, e.getMessage());
    }
}
