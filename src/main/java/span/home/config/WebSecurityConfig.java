package span.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.sql.DataSource;

/**
 * Создано Span 10.04.2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final
    DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public WebSecurityConfig(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password, TRUE from expenses_log.users where username=?").passwordEncoder(new BCryptPasswordEncoder())
                .authoritiesByUsernameQuery(
                        "select '1' as username,role from expenses_log.roles where id = (select role_id_id from expenses_log.users where username=?)");
    }

    /**
     * Override this method to configure {@link WebSecurity}. For example, if you wish to
     * ignore certain requests.
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/css/**", "/static/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*", "/error-login.html", "/css/**", "/js/**", "/img/**", "/404").permitAll()
                .antMatchers("/log/*").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .requiresChannel()
                .anyRequest().requiresInsecure()
                .and()
                .sessionManagement()
                .sessionFixation()
                .none()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/check_login")
                .defaultSuccessUrl("/homepage.html", true)
                .failureUrl("/error-login.html");

        http.logout().
                logoutUrl("/perform_logout").
                logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler()).deleteCookies("JSESSIONID");
//        http.exceptionHandling().accessDeniedPage("/403");
    }

}
