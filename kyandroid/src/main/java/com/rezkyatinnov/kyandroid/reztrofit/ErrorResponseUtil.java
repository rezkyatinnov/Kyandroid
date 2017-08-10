package com.rezkyatinnov.kyandroid.reztrofit;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by rezkyatinnov on 08/08/2017.
 */

public class ErrorResponseUtil {
    public static ErrorResponse parseError(Response<?> response) {
        Converter<ResponseBody, ErrorResponse> converter = Reztrofit.getInstance().getRetrofit()
                .responseBodyConverter(ErrorResponse.class, new Annotation[0]);
        ErrorResponse error =new ErrorResponse();
        try {
            error = converter.convert(response.errorBody());
        } catch (Exception e) {
            if(response!=null) {
                error.setStatus(response.code());
                try {
                    error.setMessage(response.errorBody().string());
                } catch (Exception e1) {
                }
            }else {
                return new ErrorResponse();
            }
        }
        return error;
    }
}