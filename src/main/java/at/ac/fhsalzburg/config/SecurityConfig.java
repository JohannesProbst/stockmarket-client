package at.ac.fhsalzburg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Johan on 09.12.2016.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/app/webjars/**").permitAll();
        http.authorizeRequests().
                antMatchers("/app/secure/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .and().formLogin()  //login configuration
                .loginPage("/app/login")
                .loginProcessingUrl("/appLogin")
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/app/secure/home")
                .and().logout()    //logout configuration
                .logoutUrl("/appLogout")
                .logoutSuccessUrl("/app/login?logout");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //TODO: use persistence layer instead
        auth.inMemoryAuthentication().withUser("admin@example.com").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user@example.com").password("user").roles("USER");
    }
}
