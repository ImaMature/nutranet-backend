package com.nutranet.Model.DTO;

import com.nutranet.Model.Entity.BoardEntity;
import com.nutranet.Model.Entity.ReplyEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private int rno;
    private String rcontent;
    private int rdepth;
    private int rgroup;
    private int rsort;
    private String rwriter;
    private LocalDateTime r_createDate;
    private LocalDateTime r_updateDate;
}
