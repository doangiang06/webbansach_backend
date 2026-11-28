package giang.kma.webbansach_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import giang.kma.webbansach_backend.service.UserService;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserService userService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider(userService);
       
        dap.setPasswordEncoder(passwordEncoder);
        return dap;//dap dùng để kiểm tra một object có quyền gì 
    }
   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/sach").permitAll()
                    .requestMatchers(HttpMethod.GET, "/hinh-anh").permitAll()
                    .requestMatchers(HttpMethod.GET, "/nguoi-dung").hasRole("ADMIN")
    );

    http.httpBasic(Customizer.withDefaults());

    http.csrf(csrf -> csrf.disable());

    return http.build();
}
}