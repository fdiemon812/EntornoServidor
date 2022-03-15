package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase para configurar rutas CORS
 * @author estudiante
 *
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	
	private String url = "http://localhost:4200/";
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				registry.addMapping("/login")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH", "Content-Type","X-Requested-With",
						"accept","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/register")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				
				//Estos comprueban token AUTHORIZATION
				registry.addMapping("/home/token")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/home/usuario")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/centro/{id}")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centros")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro/{id}/aulas")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro/{id}/aula")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro/{id}/alumno")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/centro/{id}/alumnos")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/centro/{id}/alumno/{idAlumno}")
				.allowedOrigins(url)
				.allowedHeaders("Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.allowedMethods("PUT", "DELETE","OPTIONS", "GET", "POST", "HEAD")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/centro/{id}/aula/{idAula}")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/centro/{id}/aula/{idAula}/alumnos")
				.allowedOrigins(url)
				.allowedHeaders("GET","POST","OPTIONS","DELETE","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/alumno")
				.allowedOrigins(url)
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/alumno/{idAlumno}")
				.allowedOrigins(url)
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				
				registry.addMapping("/aula")
				.allowedOrigins(url)
//				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
		
				
				registry.addMapping("/aula/{id}")
				.allowedOrigins(url)
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				registry.addMapping("/aulas")
				.allowedOrigins(url)
				.allowedMethods("PUT", "OPTIONS", "GET", "POST", "HEAD")
				.allowedHeaders("GET","POST","OPTIONS","PUT" ,"HEAD", "PATCH","Content-Type","X-Requested-With",
						"accept","Authorization","Origin","Access-Control-Request-Method:PUT, OPTIONS","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
				
				
				registry.addMapping("/usuario")
				.allowedOrigins(url)
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