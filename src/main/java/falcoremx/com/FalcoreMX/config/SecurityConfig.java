package falcoremx.com.FalcoreMX.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(customCorsConfigurationSource()))
                .csrf(csrf -> csrf.disable()) // deshabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permite acceso a todos los endpoints
                )
                .formLogin(form -> form.disable()) // deshabilita form login
                .httpBasic(basic -> basic.disable()); // deshabilita basic auth

        return http.build();
    }

    // Inyectar el CorsConfigurationSource
    @Bean
    public org.springframework.web.cors.CorsConfigurationSource customCorsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        config.setAllowedOriginPatterns(java.util.Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(java.util.Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        config.setAllowedHeaders(java.util.Arrays.asList("*"));
        config.setExposedHeaders(java.util.Arrays.asList("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
