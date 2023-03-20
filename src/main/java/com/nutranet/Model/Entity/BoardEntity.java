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
@Table(name = "board")
@Builder
public class BoardEntity extends TimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column (name = "btitle")
    private String btitle;
    @Column (name = "bcontent")
    private String bcontent;
    @Column (name = "bfile")
    private String bfile;
    @Column (name = "bview")
    private int bview;


}
