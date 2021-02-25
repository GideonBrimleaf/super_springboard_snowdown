package super_springboard_snowdown

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.server.ServerHttpSecurity.http

@Configuration
class OktaOAuth2WebSecurityConfigurerAdapter: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
//        http?.csrf()?.disable()?.authorizeRequests()
        http?.authorizeRequests()
            ?.antMatchers("/")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()?.oauth2Login()
    }
}