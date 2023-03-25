package com.nutranet.Service;

import com.nutranet.Config.TokenProvider;
import com.nutranet.Model.DTO.Common.ResponseDTO;
import com.nutranet.Model.DTO.MemberDTO;
import com.nutranet.Model.Entity.MemberEntity;
import com.nutranet.Model.Repository.MemberRepository;
import com.nutranet.Model.Role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepos;

    @Autowired
    TokenProvider tokenProvider;

    public ResponseDTO<?> signUp(MemberDTO memberDTO) {
        if (memberDTO.getMid() != null && memberDTO.getMpw() != null
                && memberDTO.getMemail() != null && memberDTO.getMaddress() != null) {
            MemberEntity memberEntity = MemberEntity
                    .builder()
                    .mid(memberDTO.getMid())
                    .mpw(memberDTO.getMpw())
                    .memail(memberDTO.getMemail())
                    .mname(memberDTO.getMname())
                    .mnick(memberDTO.getMnick())
                    .maddress(memberDTO.getMaddress())
                    .mimg(memberDTO.getMimg())
                    .role(Role.MEMBER)
                    .build();
            memberRepos.save(memberEntity);
            return ResponseDTO.ofSuccess("회원가입 완료", null);
        }
        return ResponseDTO.ofFail("정확한 내용을 입력해주세요.");
    }

    public ResponseDTO<?> login(MemberDTO memberDTO) {
        System.out.println("memberDto : " + memberDTO.toString());

        //로그인 정보 확인 (existBy ~ 를 이용)
        try{
            boolean existed = memberRepos.existsByMidAndMpw(memberDTO.getMid(), memberDTO.getMpw());
            if (existed) {
                MemberEntity memberEntity = memberRepos.findByMid(memberDTO.getMid());
                if (memberEntity != null && memberEntity.getRole() != Role.ANONYMOUS) {
                    System.out.println(" ===> memberEntity : "+memberEntity);
                    //토큰 생성
                    String token = tokenProvider.create(memberDTO.getMid());

                    MemberDTO memberDTO2 = MemberDTO
                            .builder()
                            .mno(memberEntity.getMno())
                            .mid(memberEntity.getMid())
                            .mname(memberEntity.getMname())
                            .mnick(memberEntity.getMnick())
                            .memail(memberEntity.getMemail())
                            .mbirth(memberEntity.getMbirth())
                            .maddress(memberEntity.getMaddress())
                            .mimg(memberEntity.getMimg())
                            .role(memberEntity.getRole())
                            .m_createDate(memberEntity.getCreateDate())
                            .token(token)
                            .build();
                    return ResponseDTO.ofSuccess("로그인 성공", memberDTO2);
                }
            }
        }catch(Exception e){
            return ResponseDTO.ofFail("데이터 베이스 에러.");
        }
        return ResponseDTO.ofFail("해당하는 로그인 정보가 존재하지 않습니다.");
    }
}
