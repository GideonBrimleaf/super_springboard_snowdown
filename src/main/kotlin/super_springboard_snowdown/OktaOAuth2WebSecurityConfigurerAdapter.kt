package super_springboard_snowdown

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class OktaOAuth2WebSecurityConfigurerAdapter: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
            ?.antMatchers("/")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()?.oauth2Login()
    }
}