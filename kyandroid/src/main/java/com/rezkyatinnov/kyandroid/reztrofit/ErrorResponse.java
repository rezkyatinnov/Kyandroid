package com.rezkyatinnov.kyandroid.reztrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rezkyatinnov on 08/08/2017.
 */

public class ErrorResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("description")
    private String description;

    private HttpStatus httpStatus;

    public ErrorResponse() {
        httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.description = httpStatus.getReasonPhrase();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "ErrorResponse {" +
                "status='" + code + '\'' +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
