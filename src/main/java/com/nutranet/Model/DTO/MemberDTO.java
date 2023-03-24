package com.nutranet.Model.DTO;

import com.nutranet.Model.Entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String mid;
    private String mpw;
    private String memail;
    private String mnick;
    private String mname;
    private String mbirth;
    private String maddress;
    private String mimg;
    private LocalDateTime m_createDate;
    private LocalDateTime m_updateDate;

    public MemberEntity memberEntity(){
        return MemberEntity.builder()
                .mid(this.mid)
                .mpw(this.mpw)
                .mname(this.mname)
                .memail(this.memail)
                .mnick(this.mnick)
                .mbirth(this.mbirth)
                .mimg(this.mimg)
                .build();
    }
}
