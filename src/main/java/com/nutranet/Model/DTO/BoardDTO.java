package com.nutranet.Model.DTO;

import com.nutranet.Model.Entity.BoardEntity;
import com.nutranet.Model.Entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private int bno;
    private String btitle;
    private String bcontent;
    private int bview;
    private String bwriter;
    private List <String> bfile;
    private LocalDateTime b_createDate;
    private LocalDateTime b_updateDate;

    public BoardEntity boardEntity(){
        return BoardEntity.builder()
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .build();
    }
}
