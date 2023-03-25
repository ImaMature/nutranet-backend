package com.nutranet.Config;

import com.nutranet.Model.DTO.MemberDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    // JWT 생성 및 검증을 위한 키
    private static final String SECURITY_KEY = "jwtsecKey!@"; //JWT만들 때 사용할 키
    // 위 키를 바탕으로 데이터 암호화, 복호화

    // JWT 생성하는 메소드
    public String create (String mid){
        // 만료 시간                                             // 현재시간 + 1일
        Date exprTime = (Date) Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        Key key = Keys.hmacShaKeyFor(SECURITY_KEY.getBytes());
        System.out.println("create 넘어옴? key는 ? ==>" + key);
        // JWT 생성
        return Jwts.builder()
                // 암호화에 사용될 알고리즘, 키
                //.signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .signWith(key, SignatureAlgorithm.HS512)
                // Subject(JWT 제목), Issue(생성일), Expiration(만료일) 설정
                .setSubject(mid) //제목
                .setIssuedAt(new Date()) //현재 시간 기반으로 생성
                .setExpiration(exprTime) //만료 시간
                // 생성
                .compact();
    }
    
    // JWT 검증
    public String validate(String token){
        // 매개변수로 받은 token 키를 사용해서 복호화(Decoding)
        // claims는 JWT의 Body에서 인코딩 된 정보의 비트
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECURITY_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println("===> claims : "+claims);
        // 복호화된 토큰의 payload에서 제목(subject)를 가져옴
        return claims.getSubject(); //지정된 서브젝트 받아오기
    }
}
