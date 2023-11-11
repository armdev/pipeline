package io.banqr.myameria.spadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
//@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class SpadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpadminApplication.class, args);
    }

//    @Configuration(proxyBeanMethods = false)
//    public static class SecuritySecureConfig {
//
//        private final String adminContextPath;
//
//        public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
//            this.adminContextPath = adminServerProperties.getContextPath();
//        }
//
//        @Bean
//        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//            successHandler.setTargetUrlParameter("redirectTo");
//            successHandler.setDefaultTargetUrl(this.adminContextPath + "/");
//
//            http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
//                    .requestMatchers(new AntPathRequestMatcher(this.adminContextPath + "/assets/**"))
//                    .permitAll()
//                    .requestMatchers(new AntPathRequestMatcher(this.adminContextPath + "/login"))
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated())
//                    .formLogin((formLogin) -> formLogin.loginPage(this.adminContextPath + "/login")
//                    .successHandler(successHandler))
//                    .logout((logout) -> logout.logoutUrl(this.adminContextPath + "/logout"))
//                    .httpBasic(Customizer.withDefaults())
//                    .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                    .ignoringRequestMatchers(
//                            new AntPathRequestMatcher(this.adminContextPath + "/instances", HttpMethod.POST.toString()),
//                            new AntPathRequestMatcher(this.adminContextPath + "/instances/*",
//                                    HttpMethod.DELETE.toString()),
//                            new AntPathRequestMatcher(this.adminContextPath + "/actuator/**")));
//
//            return http.build();
//        }
//
//    }
}
