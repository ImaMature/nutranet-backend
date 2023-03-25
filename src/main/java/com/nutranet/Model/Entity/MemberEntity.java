package com.nutranet.Model.Entity;

import com.nutranet.Model.Entity.Common.TimeEntity;
import com.nutranet.Model.Role.Role;
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
    @Column(name="maddress")
    private String maddress;
    @Column (name = "mimg")
    private String mimg;
    @Column (name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    //role key를 반환하는 메소드 //나중에 시큐리티 사용 시 키 보관
    public String getRoleKey(){
        return this.role.getKey();
    }

}
