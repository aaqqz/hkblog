package com.hkblog.api.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 *     "code": "400",
 *     "message": "잘못된 요청입니다",
 *     "validation": {
 *         "title": "값을 입력해주세요"
 *     }
 * }
 */
@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY ) // response data 중 비어 있는 data는 제거
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public void addValidation(String fieldName, String errorMessage) {
        System.out.println("fieldName = " + fieldName);
        System.out.println("errorMessage = " + errorMessage);
        System.out.println("11" + this.validation.getClass());
        this.validation.put(fieldName, errorMessage);
    }
}
