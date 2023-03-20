package com.nutranet.Model.DTO;

import com.nutranet.Model.Entity.KeywordEntity;
import com.nutranet.Model.Entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordDTO {
    private int kno;
    private String kname;
    private LocalDateTime k_createDate;
    private LocalDateTime k_updateDate;

    MemberEntity memberEntity;

    public KeywordEntity keywordEntity(){
        return KeywordEntity.builder()
                .kname(this.kname)
                .build();
    }
}
