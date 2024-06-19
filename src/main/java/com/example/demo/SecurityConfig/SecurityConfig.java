// import org.springframework.context.annotation.Bean;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// 	@Bean
// 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// 		http
// 			.authorizeHttpRequests((authorize) -> authorize
// 				.requestMatchers("/login").permitAll()
// 				.anyRequest().authenticated()
// 			);

// 		return http.build();
// 	}

// 	@Bean
// 	public AuthenticationManager authenticationManager(
// 			UserDetailsService userDetailsService,
// 			PasswordEncoder passwordEncoder) {
// 		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
// 		authenticationProvider.setUserDetailsService(userDetailsService);
// 		authenticationProvider.setPasswordEncoder(passwordEncoder);

// 		return new ProviderManager(authenticationProvider);
// 	}

// 	@Bean
// 	public UserDetailsService userDetailsService() {
// 		UserDetails userDetails = User.withDefaultPasswordEncoder()
// 			.username("user")
// 			.password("password")
// 			.roles("USER")
// 			.build();

// 		return new InMemoryUserDetailsManager(userDetails);
// 	}

// 	@Bean
// 	public PasswordEncoder passwordEncoder() {
// 		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
// 	}

// }