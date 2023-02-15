package com.nativecreativa.appointment_booking.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthProvider databaseAuthenticationProvider;

    public SecurityConfig(AuthProvider databaseAuthenticationProvider) {
        this.databaseAuthenticationProvider = databaseAuthenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/h2"); // do not remove this line
        // TODO: If you are implementing the security requirements, remove this following line
        //  web.ignoring().antMatchers("/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

                 http.csrf().disable().authorizeRequests()
                .antMatchers("/","/fitness-centers","/about","/contact-us","/register","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()//.loginPage("/login.html")
                        // .loginProcessingUrl("/home/login")
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/",true)
                .and()
                .logout().logoutUrl("/logout").clearAuthentication(true)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(databaseAuthenticationProvider);
    }


}
