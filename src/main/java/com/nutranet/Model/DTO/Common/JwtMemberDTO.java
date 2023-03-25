package com.nutranet.Model.DTO.Common;

import com.nutranet.Model.Entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtMemberDTO {
    private String token;
    private int exprTime;
    private MemberEntity memberEntity;

}
