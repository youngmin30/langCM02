package com.jym.langCM02.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 정적파일 ignore
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/css/**", "/js/**", "/img/**", "/error/**", "/lib/**");
    }

    // URL 정보 등록
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests( authorize -> authorize
                        // URL 에 따른 권한 설정
                        .mvcMatchers(
                                "/members/join",
                                "/members/login" // 7-5 login의 url을 추가하고, 위 라인에 , 추가
                        )
                        .anonymous()
                        .mvcMatchers(
                                "/articles/**",
                                "/", // 6-4 index의 url인 "/" 추가하고, 위 라인에 , 추가
                                "/members/modify", // 12-2 modify.html 추가
                                "boards/add" // 13-6
                        )
                        .permitAll()
                        .mvcMatchers(
                                "/adm/**"
                        )
                        .hasRole("ADMIN")
                        .anyRequest()
                        .denyAll()
                )
            .formLogin()// 폼 기반 선언
                .loginPage("/members/login")
                .loginProcessingUrl("/members/doLogin") // 7-6 /members/doLogin으로 변경함
                .usernameParameter("loginId")
                .passwordParameter("loginPw")
                .defaultSuccessUrl("/")
            .and() // 로그아웃 관리
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JESSIONID")
                    .clearAuthentication(true)
            .and()
                .sessionManagement()
                    .invalidSessionUrl("/")
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true)
                    .expiredUrl("/");

    }

    // 비밀번호 Encoder Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}