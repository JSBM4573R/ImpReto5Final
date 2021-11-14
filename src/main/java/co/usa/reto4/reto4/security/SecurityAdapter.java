package co.usa.reto4.reto4.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        *http.authorizeRequests se establecen las urls que se les permitirá acceso
        / raiz, /error, carpetas webjars, /api/ lo que venga dejelo entrar **
        */
        // @formatter:off
        http.authorizeRequests(a -> a
                .antMatchers("/", "/error", "/webjars/**", "/api/**").permitAll()
                .anyRequest().authenticated()
        )
        .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        )
        .csrf(c -> c
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        )
        // @formatter:on
        .logout(l -> l
                .logoutSuccessUrl("/index.html").permitAll()
        )
        .oauth2Login().defaultSuccessUrl("/Category.html", true);

        //Abre los Cors permitiendo peticiones realizadas por MasterTech(GET,PUT,DELETE,UPDATE).
        http.cors().and().csrf().disable();
    }
}