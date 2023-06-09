package com.nutranet.Model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    MEMBER("ROLE_MEMBER", "일반 회원"),
    ANONYMOUS("ROLE_ANONYMOUS", "익명 회원"),
    ADMIN("ROLE_ADMIN", "관리자");

    private String key;
    private String title;

}
