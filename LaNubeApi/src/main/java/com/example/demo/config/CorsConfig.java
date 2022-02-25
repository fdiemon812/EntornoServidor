package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				registry.addMapping("/login")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH", "Content-Type","X-Requested-With",
						"accept","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/register")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				
				//Estos comprueban token AUTHORIZATION
				registry.addMapping("/home/token")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/centro")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/centro/{id}")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centros")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro/{id}/aulas")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro/{id}/aula")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro/{id}/alumno")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro/{id}/aula/{idAula}")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/alumno")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/alumno/{idAlumno}")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				
				registry.addMapping("/aula")
				.allowedOrigins("http://localhost:4200")
//				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
		
				
				registry.addMapping("/aula/{id}")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/aulas")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/usuario")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
//				registry.addMapping("/alumno/{idAlumno}")
//				.allowedOrigins("http://localhost:4200")
//				.allowedHeaders("GET","PUT","Content-Type","X-Requested-With",
//						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
//				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				

				
				
			}
		};
	}


}