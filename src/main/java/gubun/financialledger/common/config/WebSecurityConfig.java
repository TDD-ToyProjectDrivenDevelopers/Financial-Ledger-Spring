package gubun.financialledger.common.config;

import gubun.financialledger.user.handler.CustomAuthenticationFailureHandler;
import gubun.financialledger.user.entity.UserRole;
import gubun.financialledger.user.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig  {

    private final UserDetailsService userDetailsService;
    private final PrincipalOauth2UserService principalOauth2UserService;

    private static final String[] ANONYMOUS_MATCHERS = {
            "/register", "/email/**", "/login/**", "/inquiry/**", "/account/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() { return new CustomAuthenticationFailureHandler(); }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //안티패턴 기반 사용자 인가 API 적용
        http.authorizeRequests()
                .antMatchers(ANONYMOUS_MATCHERS).anonymous()
                .antMatchers("/admin/**").hasRole(UserRole.ADMIN.toString())
                .anyRequest().authenticated();

        // 일반 로그인 사용
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .loginProcessingUrl("/login")
                .failureHandler(authenticationFailureHandler());

        //OAuth 로그인 사용
        http.oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .userInfoEndpoint()
                .userService(principalOauth2UserService);

        // 로그아웃시 remember-me 쿠키도 지운다.
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("remember-me");

        // remember-me 기능 추가
        http.rememberMe()
                .userDetailsService(userDetailsService);

        // 인증 실패 시(ex: 익명 사용자가 index 페이지 접근 등) 로그인 페이지로 이동
        http.exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendRedirect("/login"));

        /**
         * 중복 로그인 관련
         * 세션 최대 허용 수 : 1
         * 다른 세션에서 로그인 시 이전 세션 없앰
         */
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);

        // CSRF 보안 기능 사용 -> 로그인 시 응답으로 CSRF 토큰을 넘겨줌
        http.csrf();

        return http.build();
    }

    //정적 리소스 접근제한
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
