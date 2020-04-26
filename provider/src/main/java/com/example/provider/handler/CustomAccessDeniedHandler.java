package com.example.provider.handler;

import com.example.commons.dto.BaseResultDto;
import com.example.commons.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yinfelix
 * @date 2020/3/12
 */
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(CONTENT_TYPE_JSON);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        try {
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtils.getJsonStringFromObject(
                    BaseResultDto.fail(HttpStatus.FORBIDDEN.value(), accessDeniedException.getLocalizedMessage())));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error("CustomAccessDeniedHandler#handle()", e);
        }
    }
}
