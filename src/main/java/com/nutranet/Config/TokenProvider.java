package com.nutranet.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenProvider {

    // JWT 생성 및 검증을 위한 키, 이것을 바탕으로 데이터 암호화, 복호화
    @Value("${jwt.token.key}")
    private String SECURITY_KEY;

    // JWT 생성하는 메소드
    public String create (String mid){
        //String -> byte[]
        byte[] decodedKeyBytes = Base64.getDecoder().decode(SECURITY_KEY);
        //byte[] -> Key (HMAC SHA necessary!!)
        Key privateKey = Keys.hmacShaKeyFor(decodedKeyBytes);

        try {
            // Key -> String
            //String encodedKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            //System.out.println("@@비밀 키 : "+encodedKey);
            //System.out.println("@@원본 String 키 : "+ SECURITY_KEY);

            // 만료 시간                                             // 현재시간 + 1일
            Date exprTime = (Date) Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

            // JWT 생성
            return Jwts.builder()
                    // 암호화에 사용될 알고리즘, 키
                        //.signWith(알고리즘(enum), 키(String)) -> 옛날 버전
                        //.signWith(키(Key), 알고리즘(enum)) -> 현재 버전
                    .signWith(privateKey, SignatureAlgorithm.HS512)
                    // Subject(JWT 제목), Issue(생성일), Expiration(만료일) 설정
                    .setSubject(mid) //제목
                    .setIssuedAt(new Date()) //현재 시간 기반으로 생성
                    .setExpiration(exprTime) //만료 시간
                    // 생성
                    .compact();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
