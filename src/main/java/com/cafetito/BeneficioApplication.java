package com.cafetito;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
      
}
