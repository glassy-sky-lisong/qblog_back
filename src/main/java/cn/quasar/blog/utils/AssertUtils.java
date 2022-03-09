package cn.quasar.blog.utils;

import cn.quasar.blog.exception.CustomException;
import org.springframework.stereotype.Component;

@Component
public class AssertUtils {

    public AssertUtils customExceptionHandler(boolean condition, String error) {
        if (condition) {
            throw new CustomException( error == null ? "" : error );
        }

        return this;
    }

    public AssertUtils IfHandler(boolean condition, CallBack callBack) {
        if (condition) {
            callBack.call();
        }

        return this;
    }
}
