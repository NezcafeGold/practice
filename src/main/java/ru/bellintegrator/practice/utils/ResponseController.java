package ru.bellintegrator.practice.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ResponseController implements ResponseBodyAdvice<Object> {

    private String errorName;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        HttpServletResponse servletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();

        if (servletResponse.getStatus() == 200) {
            if (o == null) {
                SuccessView successView = new SuccessView();
                return new WrapperObj<Object>(successView);
            }
        } else {
            ErrorView errorView = new ErrorView(errorName);
            return errorView;
        }
        return new WrapperObj<Object>(o);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    private class WrapperObj<T> {
        private final Object data;

        public WrapperObj(Object data) {
            this.data = data;
        }
    }

    @ExceptionHandler(Exception.class)
    public Exception handleException(Exception e) {
        errorName = e.getMessage();
        return e;
    }
}
