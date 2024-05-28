package kr.pah.rufimt.security

import jakarta.servlet.http.HttpServletResponse
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

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
                form
                    .loginProcessingUrl("/api/login")
                    .successHandler(authenticationSuccessHandler())
                    .failureHandler(authenticationFailureHandler())
            }
            .logout { logout ->
                logout.logoutUrl("/api/logout")
            }
            .exceptionHandling { exceptions ->
                exceptions
                    .authenticationEntryPoint { request, response, authException ->
                        response.characterEncoding = "UTF-8"
                        response.contentType = "application/json; charset=UTF-8"
                        response.status = HttpServletResponse.SC_UNAUTHORIZED
                        response.writer.write("{\"message\": \"로그인 후 이용 가능합니다.\"}")
                    }
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
            } ?: throw UsernameNotFoundException("아이디가 존재하지 않습니다.")
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationSuccessHandler(): AuthenticationSuccessHandler {
        return AuthenticationSuccessHandler { request, response, authentication ->
            response.characterEncoding = "UTF-8"
            response.contentType = "application/json; charset=UTF-8"
            response.writer.write("{\"message\": \"로그인 되었습니다.\"}")
        }
    }

    @Bean
    fun authenticationFailureHandler(): AuthenticationFailureHandler {
        return AuthenticationFailureHandler { request, response, exception ->
            response.characterEncoding = "UTF-8"
            response.contentType = "application/json; charset=UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            when (exception) {
                is UsernameNotFoundException -> response.writer.write("{\"message\": \"아이디가 존재하지 않습니다.\"}")
                else -> response.writer.write("{\"message\": \"비밀번호가 잘못되었습니다.\"}")
            }
        }
    }
}
