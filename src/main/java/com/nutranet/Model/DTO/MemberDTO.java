package com.nutranet.Model.DTO;

import com.nutranet.Model.Entity.MemberEntity;
import com.nutranet.Model.Role.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private int mno;
    @NotBlank
    private String mid;
    @NotBlank
    private String mpw;
    private String memail;
    private String mnick;
    private String mname;
    private String mbirth;
    private String maddress;
    private String mimg;
    private Role role;
    private String token;
    private Long exprTime;
    private LocalDateTime m_createDate;
    private LocalDateTime m_updateDate;


}
