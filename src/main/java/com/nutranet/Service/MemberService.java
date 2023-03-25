package com.nutranet.Service;

import com.nutranet.Model.DTO.Common.ResponseDTO;
import com.nutranet.Model.DTO.MemberDTO;
import com.nutranet.Model.Entity.MemberEntity;
import com.nutranet.Model.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepos;

    public ResponseDTO<?> signUp(MemberDTO memberDTO) {
        System.out.println("memberDto : "+memberDTO.toString() );
        if(memberDTO.getMid() != null && memberDTO.getMpw() != null
                && memberDTO.getMemail() != null && memberDTO.getMaddress() != null){
            MemberEntity memberEntity = MemberEntity
                    .builder()
                    .mid(memberDTO.getMid())
                    .mpw(memberDTO.getMpw())
                    .memail(memberDTO.getMemail())
                    .mname(memberDTO.getMname())
                    .mnick(memberDTO.getMnick())
                    .maddress(memberDTO.getMaddress())
                    .mimg(memberDTO.getMimg())
                    .build();
            memberRepos.save(memberEntity);
            return ResponseDTO.ofSuccess("회원가입 완료", null);
        }
        return ResponseDTO.ofFail("정확한 내용을 입력해주세요.");
    }

}
