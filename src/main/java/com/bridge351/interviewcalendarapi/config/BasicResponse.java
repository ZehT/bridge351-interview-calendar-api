package com.bridge351.interviewcalendarapi.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

/**
 * <p>Generic class that represent all the API Responses.</p>
 *
 * @param <T> - generic data
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponse<T> {

    @Builder.Default
    private String message = Strings.EMPTY;

    @Builder.Default
    private String detail = Strings.EMPTY;

    @Builder.Default
    private int statusCode = HttpStatus.OK.value();

    private T data;

    /**
     * <p>Response with data to be returned to the caller of the API.</p>
     *
     * @param data - object to be returned
     * @param <T>  - generic type
     * @return - the basic response
     */
    public static <T> BasicResponse<T> withData(final T data) {
        final BasicResponse<T> result = new BasicResponse<>();
        result.setData(data);
        return result;
    }

    /**
     * <p>Response with data and message to be returned to the caller of the API.</p>
     *
     * @param data - object to be returned
     * @param <T>  - generic type
     * @return - the basic response
     */
    public static <T> BasicResponse<T> withDataAndMessage(final T data, final String msg) {
        final BasicResponse<T> result = new BasicResponse<>();
        result.setData(data);
        result.setMessage(msg);
        return result;
    }

    /**
     * <p>Failed Responsed to be returned to the call of the API.</p>
     *
     * @param statusCode - status code of the error
     * @param message    - msg of the error
     * @param detail     - any extra information about the error
     * @return - the basic response
     */
    public static BasicResponse fail(final int statusCode, final String message, final String detail) {
        return BasicResponse.builder()
                .statusCode(statusCode)
                .message(message)
                .detail(detail)
                .build();
    }

}
