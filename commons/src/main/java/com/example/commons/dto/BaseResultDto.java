package com.example.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author yinfelix
 * @date 2020/2/16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResultDto implements Serializable {

    private int statusCode;
    private String message;
    private Object data;

    /**
     * 响应完成，并携带对象数据
     * @param data
     * @return
     */
    public static BaseResultDto ok(Object data) {
        return new BaseResultDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    /**
     * 响应完成，只携带附加信息
     * @param message
     * @return
     */
    public static BaseResultDto ok(String message) {
        return new BaseResultDto(HttpStatus.OK.value(), message, null);
    }

    /**
     * 响应完成，不携带信息
     * @return
     */
    public static BaseResultDto ok() {
        return new BaseResultDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    /**
     * 响应失败
     * @param statusCode
     * @param message
     * @return
     */
    public static BaseResultDto fail(int statusCode, String message) {
        return new BaseResultDto(statusCode, message, null);
    }
}
