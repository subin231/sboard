package com.sboard.security;

import com.sboard.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Builder
public class MyUserDetails implements UserDetails, OAuth2User { // OAuth2 인증 호환을 위해 OAuth2User 추가

    // User 엔티티 선언
    private User user;

    // OAuth 인증에 사용되는 속성
    private Map<String, Object> attributes;
    private String accessToken;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 계정이 갖는 권한 목록 생성
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole())); // 계정 권한 앞에 접두어 ROLE_ 붙여야 됨
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPass();
    }

    @Override
    public String getUsername() {
        return user.getUid();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부(true: 만료안됨, false: 만료)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠김 여부(true: 잠김아님, false: 잠김)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호 만료 여부(true: 만료안됨, false: 만료)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부(true: 활성화, false: 비활성)
        return true;
    }


}
