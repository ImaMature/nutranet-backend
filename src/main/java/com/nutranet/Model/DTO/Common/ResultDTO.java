package com.nutranet.Model.DTO.Common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class ResultDTO<D> {
    private final String resultCode;
    private final String msg;
    private final D data;

    public static <D> ResultDTO<D> ofSuccess(String msg, D data){
        return new ResultDTO<>("SUCCESS", msg, data);
    }

    public static <D> ResultDTO<D> ofFail(String msg, D data){
        return new ResultDTO<>("FAIL", msg, data);
    }


}
