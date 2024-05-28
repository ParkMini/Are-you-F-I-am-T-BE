package kr.pah.rufimt.security

import kr.pah.rufimt.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userService: UserService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/api/register", "/api/login").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { form ->
                form.loginProcessingUrl("/api/login")
            }
            .logout { logout ->
                logout.logoutUrl("/api/logout")
            }

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username ->
            val user = userService.findByUsername(username)
            user?.let {
                org.springframework.security.core.userdetails.User(
                    it.username,
                    it.password,
                    listOf(SimpleGrantedAuthority(it.role))
                )
            } ?: throw UsernameNotFoundException("User not found")
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
