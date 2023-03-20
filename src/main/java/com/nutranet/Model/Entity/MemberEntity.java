package com.nutranet.Model.Entity;

import com.nutranet.Model.Entity.Common.TimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "member")
@Builder
public class MemberEntity extends TimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    @Column (name = "mid")
    private String mid;
    @Column (name = "mpw")
    private String mpw;
    @Column (name = "memail")
    private String memail;
    @Column (name = "mnick")
    private String mnick;
    @Column (name = "mname")
    private String mname;
    @Column (name = "mbirth")
    private String mbirth;
    @Column (name = "mimg")
    private String mimg;

}
