package cn.quasar.blog.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MessageResult {
    /**
     *   code: number
     *   status: number or enum(success, fail)
     *   data: any
     *   error: error
     *
     */

    private int code;
    private int status;
    private Object data;
    private List<String> errors;

    public MessageResult(int code, int status, Object data, List<String> errors) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.errors = errors;
    }

    public MessageResult(int code, int status, Object data, String error) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.errors = new ArrayList<>(10);
        this.errors.add(error);
    }
}
