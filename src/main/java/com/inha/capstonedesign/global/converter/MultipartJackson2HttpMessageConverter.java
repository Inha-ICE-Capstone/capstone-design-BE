package com.inha.capstonedesign.global.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

/**
 * 이 Converter 의 설명
 * Postman, 프론트 에서는 multipart 내의 dto 를 application/json 타입으로 지정해서 보낼 수 있지만
 * Swagger 는 multipart 내의 dto 를 기본적으로 정의되지 않은 바이너리 데이터를 나타내는 MIME 타입인 application/octet-stream 로 보낸다
 * 하지만 application/octet-stream 을 처리해주는 converter 가 없어서 application/octet-stream' not supported 오류가 발생
 * 이 클래스는 AbstractJackson2HttpMessageConverter 을 상속받고 그 생성자에 MediaType.APPLICATION_OCTET_STREAM 을 넣어서
 * 이 converter 가 application/octet-stream 타입을 처리할 수 있게 해준다
 * 그래서 application/octet-stream 이 들어오면 AbstractJackson2HttpMessageConverter 의 readInternal 이 읽는다
 * 하지만 이 converter 가 모든 application/octet-stream 을 처리하는 로직을 만든게 아니라
 * ObjectMapper 에 의해 바이트 배열 또는 문자열 형태로 처리하는데 Jackson 은 JSON 형태의 데이터를 처리하므로
 * application/octet-stream 가 JSON 형태가 아니라면 오류가 발생할 수 있고 JSON 형태라면 application/json 타입을 받은것처럼 처리한다.
 * 그리모 도든 write 메소드를 false 로 오버라이딩 함으로써 읽기만 가능하고 Java 객체를 HTTP Response 하는 작업은 지원하지 않는다.
 *
 * 해당 에러는 아래와 같음
 * WARN 15408 --- [nio-8080-exec-8] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Unexpected end-of-input: expected close marker for Object (start marker at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 1]); nested exception is com.fasterxml.jackson.core.io.JsonEOFException: Unexpected end-of-input: expected close marker for Object (start marker at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 1])<EOL> at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 11, column: 1]]
 */
@Component
public class MultipartJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    /**
     * "Content-Type: multipart/form-data" 헤더를 지원하는 HTTP 요청 변환기
     */
    public MultipartJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper, MediaType.APPLICATION_OCTET_STREAM);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    protected boolean canWrite(MediaType mediaType) {
        return false;
    }
}
