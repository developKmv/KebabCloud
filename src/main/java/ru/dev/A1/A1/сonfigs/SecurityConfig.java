package ru.dev.A1.A1.сonfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.dev.A1.A1.data.UserRepository;
import ru.dev.A1.A1.security.UserRepositoryUserDetailsService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


/*    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }*/

/*    public UserDetailsManager users(DataSource dataSource){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        return users;
    }*/

 /*   @Bean
    public UserRepositoryUserDetailsService userDetailsService(UserRepository repo){
        return new UserRepositoryUserDetailsService(repo);
    }*/

/*    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder bCryptPasswordEncoder, UserRepositoryUserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }*/

/*    @Bean
    public JdbcUserDetailsManager userDetailsService(){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }*/

/*    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "buzz", encoder.encode("1"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        usersList.add(new User(
                "woody", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        return new InMemoryUserDetailsManager(usersList);
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        .requestMatchers("/design", "/orders/*").hasRole("USER")
                                        .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients").hasAuthority("SCOPE_deleteIngredients")
                                        .requestMatchers("/","/style.css","/images/**","/scripts/**","/login")
                                        .permitAll().anyRequest().authenticated().and().oauth2ResourceServer(oauth2 -> oauth2.jwt()).httpBasic()/*.realmName("Kebab Cloud")*/;
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
//                .requestMatchers("/", "/**").permitAll()
        ).headers(headers -> headers.frameOptions(new Customizer<HeadersConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.FrameOptionsConfig>() {
                    @Override
                    public void customize(HeadersConfigurer<HttpSecurity>.FrameOptionsConfig frameOptionsConfig) {
                        frameOptionsConfig.sameOrigin();
                    }
                }))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/api/kebab/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/data-api/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/design/getKebabOrder"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/api/ingredients/**"))
                );

        http.formLogin().loginPage("/login").defaultSuccessUrl("/design").failureUrl("/login?error=true")
                .and().logout().logoutSuccessUrl("/");

        return http.build();
    }

}
