package original.FavPaperApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    InMemoryUserDetailsManager userDetailsService() {
//        UserDetails admin = User
//                .withUsername("admin")
//                .password(passwordEncoder().encode("admin1234"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails test1 = User
//                .withUsername("test1")
//                .password(passwordEncoder().encode("test1"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, test1);
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // 開発環境のみ、CSRF保護を無効化
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/paper/list", true) // ログイン成功後にリダイレクトするURL
                        .permitAll()  // ログインURLは認証不要
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout-success")
                        .permitAll()  // ログアウトURLは認証不要
                )

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers( // 認証不要
                                "/css/**",
                                "/images/**",
                                "/",
                                "/logout-success"
//                             "/user" //ユーザー登録
                        ).permitAll()

//                        .requestMatchers(HttpMethod.POST, // POSTリクエストの認証不要
//                                "/user" //ユーザー登録
//                        ).permitAll()

                        .requestMatchers(  // adminロールのみアクセス可能
                                "/paper/edit",
                                "/fav_memo/list",
                                "/user/list",
                                "/user/edit"
                        ).hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST) // すべてのPOSTリクエストはadminロールのみ可能
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE) // すべてのDELETEリクエストはadminロールのみ可能
                        .hasRole("ADMIN")

                        .anyRequest().authenticated() //  他のURLはログイン後アクセス可能
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            // 未認証時に index ページにリダイレクト
                            response.sendRedirect("/");
                        })
                );

        return http.build();
    }
}
