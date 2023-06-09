package com.nutranet.Controller;

import com.nutranet.Model.DTO.Common.ResponseDTO;
import com.nutranet.Model.DTO.MemberDTO;
import com.nutranet.Model.Entity.MemberEntity;
import com.nutranet.Model.Role.Role;
import com.nutranet.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
public class MemberController {

    @Autowired
    MemberService memberService;

    //회원가입
    @PostMapping("/SignUp")
    public ResponseDTO<?> SignUp(@RequestBody MemberDTO memberDTO){
        return memberService.signUp(memberDTO);
    }

    //로그인
    @PostMapping("/Login")
    public ResponseDTO<?> Login(@RequestBody MemberDTO memberDTO){
        return memberService.login(memberDTO);
    }

}
