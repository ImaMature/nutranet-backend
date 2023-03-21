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
@Table(name = "keyword")
@Builder
public class KeywordEntity extends TimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kno;
    @Column (name = "kname")
    private String kname;


}
