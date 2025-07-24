package kh.edu.cstad.mbapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    //in database directly
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    // static
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        //Create ADMIN
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin@123")
//                .roles("ADMIN")
//                .build();
//        manager.createUser(admin);
//
//        //Create CUSTOMER
//        UserDetails customer = User.builder()
//                .username("customer")
//                .password("{noop}customer@123")
//                .roles("CUSTOMER")
//                .build();
//        manager.createUser(customer);
//
//        //Create STAFF
//        UserDetails staff = User.builder()
//                .username("staff")
//                .password("{noop}staff@123")
//                .roles("STAFF")
//                .build();
//        manager.createUser(staff);
//        return manager;
//    }

    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        //TODO
        // All requests must be authenticated
        http.authorizeHttpRequests(request -> {
                request
//                        .requestMatchers(HttpMethod.POST,"/api/v1/customers/**")
//                            .hasAnyRole("ADMIN", "STAFF")
//                        .requestMatchers(HttpMethod.PUT,"/api/v1/customers/**")
//                            .hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/v1/customers/**")
//                            .hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/api/v1/customers/**")
//                            .hasAnyRole("ADMIN", "STAFF","CUSTOMER")
//                        .requestMatchers("api/v1/accounts/**")
//                            .hasAnyRole("USER")
                        .requestMatchers("api/v1/accounts/**")
                            .hasAnyRole("USER")
                        .requestMatchers("api/v1/customers/**")
                            .hasAnyRole("USER")
                        .anyRequest().authenticated();});

        // Disable form login default
        http.formLogin(form -> form.disable());
        http.csrf(token -> token.disable());

        // Set security mechanism
        // Basic Authentication (username & password)
        http.httpBasic(Customizer.withDefaults());

        // Set session to stateless
        http.sessionManagement(session -> {session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);});

        return http.build();
    }


}
