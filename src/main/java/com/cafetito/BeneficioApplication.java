package com.cafetito;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BeneficioApplication {
//    public class BeneficioApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BeneficioApplication.class, args);
	}
        
        @Bean
        public ModelMapper ModelMapper(){
            return new ModelMapper();
        }
        
//        @Bean public WebMvcConfigurer corsConfigurer() { 
//            return new WebMvcConfigurer() {
//                @Override 
//                public void addCorsMappings(CorsRegistry registry) {
//                    registry.addMapping("/**")
//                            .allowedOrigins("*")
//                            .allowedMethods("GET", "POST", "PUT", "DELETE", "HTTP", "HTTPS")
//                            .allowedHeaders("*");
//                } 
//            };
//        }
      
}
