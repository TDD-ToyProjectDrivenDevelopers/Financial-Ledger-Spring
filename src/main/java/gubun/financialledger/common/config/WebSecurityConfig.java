package gubun.financialledger.common.config;

import gubun.financialledger.user.auth.CustomAuthenticationFailureHandler;
import gubun.financialledger.user.entity.UserRole;
import gubun.financialledger.user.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() { return new CustomAuthenticationFailureHandler(); }

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/images/**",
            "/vendor/**",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/register", "/register/emailSend", "/login").permitAll()
                    .antMatchers("/admin/**").hasRole(UserRole.ADMIN.toString())
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .failureHandler(authenticationFailureHandler())
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))  // 로그아웃 요청 경로
                    .invalidateHttpSession(true)          // 로그 아웃시 인증정보를 지우하고 세션을 무효화
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/accessDenied")      // -> 403 페이지로
                    .and()
                .rememberMe()
                    .userDetailsService(userDetailsService)
                    //.tokenValiditySeconds(?) //default : 14일, 변경가능.
                    .and()
                .csrf()
                    .and()
                .oauth2Login()
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService);


        // 중복 로그인 관련 세팅
        http
                .sessionManagement()
                    .maximumSessions(1) //세션 최대 허용 수
                    .maxSessionsPreventsLogin(false); // false : 중복 로그인 => 이전 로그인이 풀림

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers(PUBLIC_MATCHERS);
    }
}
