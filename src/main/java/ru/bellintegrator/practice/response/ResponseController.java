package ru.bellintegrator.practice.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseController implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object responseBody, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        if (!(responseBody instanceof ErrorView)) {
            if (responseBody == null) {
                SuccessView successView = new SuccessView();
                return new WrapData<Object>(successView);
            } else {
                return new WrapData<Object>(responseBody);
            }
        }
        return responseBody;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorView handleException(Exception e) {
        String exceptionMessage;
        if (e instanceof HttpMessageNotReadableException) {
            exceptionMessage = "Тело запроса пустое или необрабатываемое";
        } else if (e instanceof MethodArgumentTypeMismatchException || e instanceof HttpClientErrorException) {
            exceptionMessage = "Неверный формат запроса";
        } else {
            exceptionMessage = e.getMessage();
        }
        return new ErrorView(exceptionMessage);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    private static class WrapData<T> {
        private final Object data;

        public WrapData(Object data) {
            this.data = data;
        }
    }
}
