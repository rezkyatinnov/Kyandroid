package com.rezkyatinnov.kyandroid.reztrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rezkyatinnov on 08/08/2017.
 */

public class ErrorResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    private HttpStatus httpStatus;

    public ErrorResponse() {
        httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse {" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
