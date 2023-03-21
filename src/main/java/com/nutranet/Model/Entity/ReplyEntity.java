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
@Table(name = "reply")
@Builder
public class ReplyEntity extends TimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rno;
    @Column (name = "rcontent")
    private String rcontent;
    @Column (name = "rdepth")
    private int rdepth;
    @Column (name = "rgroup")
    private int rgroup;
    @Column (name = "rsort")
    private int rsort;


}
