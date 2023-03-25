package com.nutranet.Model.DTO.Common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class ResponseDTO<D> {
    //private final String resultCode;
    private final boolean result;
    private final String msg;
    private final D data;

    public static <D> ResponseDTO<D> ofSuccess(String msg, D data){
        return ResponseDTO.of(true, msg, data);
    }

    public static <D> ResponseDTO<D> ofFail(String msg){
        return ResponseDTO.of(false, msg, null);
    }


}
