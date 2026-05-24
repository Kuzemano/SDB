package r.real.config.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${app.remember-me.key}")
    private String rememberMeKey;

    private final JwtTokenProvider jwtTokenProvider;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final SignInSuccessHandler signInSuccessHandler;

    //@Value("${app.remember-me.key}")
    //private String rememberMeKey;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider,
                          ClientRegistrationRepository clientRegistrationRepository,
                          SignInSuccessHandler signInSuccessHandler) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.signInSuccessHandler = signInSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
/*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .passwordEncoder(passwordEncoder::encode)
                        .password("user")
                        .roles("USER")
                        .build(),
                User.withUsername("admin")
                        .passwordEncoder(passwordEncoder::encode)
                        .password("admin")
                        .roles("ADMIN")
                        .build()
        );
    }
*/

    @Bean
    @Order(3)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives("default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'; object-src 'none'")
                        ))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/drivers").hasAnyAuthority(
                                "ROLE_USER", "ROLE_ADMIN", "SCOPE_sdb.user", "SCOPE_sdb.admin")
                        .requestMatchers("/drivers/**").hasAnyAuthority(
                                "ROLE_ADMIN", "SCOPE_sdb.admin")
                        .anyRequest().authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                        .permitAll()
                )
                .rememberMe(rm -> rm
                        .key(rememberMeKey)
                        .tokenValiditySeconds(7 * 24 * 60 * 60)
                );
        return http.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler handler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        handler.setPostLogoutRedirectUri("{baseUrl}/");
        return handler;
    }
    @Bean
    @Order(2)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http,
                                                      JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.securityMatcher("/api/**")
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/api/login")
                        .successHandler(new SignInSuccessHandler(jwtTokenProvider))
                        .permitAll())
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint((req, res, authEx) -> {
                            res.setHeader("WWW-Authenticate", "Basic realm=\"\"");
                            res.setStatus(HttpStatus.UNAUTHORIZED.value());
                        })
                        .accessDeniedHandler(new AccessDeniedHandlerImpl()));
        return http.build();
    }


    @Bean
    @Order(1)
    public SecurityFilterChain h2ConsoleFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/h2/**")
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(r -> r.anyRequest().permitAll())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }




}