package rc.bootsecurity.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import rc.bootsecurity.db.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private UserPrincipalDetailsService userPrincipalDetailsService;
  private UserRepository userRepository;

  public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserRepository userRepository) {
    this.userPrincipalDetailsService = userPrincipalDetailsService;
    this.userRepository = userRepository;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();

    List<String> origins = new ArrayList<String>();
    origins.add("*");
    configuration.setAllowedOrigins(origins);

    List<String> methods = new ArrayList<String>();
    methods.add("HEAD");
    methods.add("GET");
    methods.add("POST");
    methods.add("PUT");
    methods.add("DELETE");
    methods.add("PATCH");
    configuration.setAllowedMethods(methods);

    configuration.setAllowCredentials(true);

    List<String> headers = new ArrayList<String>();
    headers.add("Authorization");
    headers.add("Cache-Control");
    headers.add("Content-Type");
    configuration.setAllowedHeaders(headers);

    List<String> exposedHeaders = new ArrayList<String>();
    exposedHeaders.add("Authorization");
    exposedHeaders.add("Cache-Control");
    exposedHeaders.add("Content-Type");
    configuration.setExposedHeaders(exposedHeaders);

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll()
        // .antMatchers("/api/products/**").permitAll()
        .and().addFilter(new JwtAuthenticationFilter(authenticationManager()))
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository)).authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login").permitAll().antMatchers(HttpMethod.GET, "/api/products/**").permitAll()
        // .antMatchers(HttpMethod.GET, "/**").permitAll()
        .antMatchers("/**").authenticated();

    http.cors();
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

    return daoAuthenticationProvider;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
