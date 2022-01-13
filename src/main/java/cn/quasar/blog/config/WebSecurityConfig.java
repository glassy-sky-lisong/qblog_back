package cn.quasar.blog.config;

import cn.quasar.blog.service.IUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;
import java.io.PrintWriter;

@SpringBootConfiguration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserDetailService userDetailService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/user/registry").permitAll()
                .antMatchers("/user/article").hasRole("user")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//          .loginPage("http://localhost:3000/#/login")
                .loginProcessingUrl("/login")
                .successHandler((req, res, authentication) -> {
                    res.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = res.getWriter();
                    writer.write((new ObjectMapper()).writeValueAsString(authentication.getPrincipal()));
                    writer.flush();
                    writer.close();
                })
                .failureHandler((req, res, exception) -> {
                    res.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = res.getWriter();
                    writer.write((new ObjectMapper()).writeValueAsString(exception.getMessage()));
                    writer.flush();
                    writer.close();
                })
                .permitAll()
                .and()
                .rememberMe()
                .key("java-boy")
                .tokenRepository(jdbcTokenRepository())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = resp.getWriter();

                    writer.write((new ObjectMapper()).writeValueAsString("登出成功"));
                    writer.flush();
                    writer.close();
                })
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authenticationException) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = resp.getWriter();
                    writer.write((new ObjectMapper()).writeValueAsString("尚未登录请重新登陆"));
                    writer.flush();
                    writer.close();
                });
    }
}
