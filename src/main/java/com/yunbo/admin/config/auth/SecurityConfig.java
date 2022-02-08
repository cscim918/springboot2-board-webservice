package com.yunbo.admin.config.auth;

import com.yunbo.admin.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()// h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                .authorizeRequests()// URL별 권한 관리를 설정
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()// 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())// 주소 가진 API는 USER만 가능
                .anyRequest().authenticated()// 설정된 값 이외 나머지 URL들
                // 권한 관리 대상을 지정하는 옵션. URL,HTTP 메소드별로 관리가 가능
                .and()
                    .logout()
                        .logoutSuccessUrl("/")// 로그아웃 성공 시 /주소로 이동
                .and()
                    .oauth2Login()// 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint()// 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
                            .userService(customOAuth2UserService);// 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록

    }
}
