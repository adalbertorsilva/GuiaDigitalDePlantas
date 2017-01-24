package br.org.itv.guia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.org.itv.guia.manager.DirectoryManager;
import br.org.itv.guia.model.Specie;

@SpringBootApplication
public class GuiaApplication {

	@Autowired
	private DirectoryManager directoryManager;
	
	public static void main(String[] args) {
		SpringApplication.run(GuiaApplication.class);	
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfig(){
//		
//		return new WebMvcConfigurerAdapter() {
//			
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/").allowedOrigins("http://localhost:8080", "http://e0618828.ngrok.io", "http://e0618828.ngrok.io/guia","http://localhost:8080/guia");
//			}
//			
//		};
//		
//	}
	
//	@Bean
//	public CommandLineRunner seed(){
//		return (args) ->{
//			
//			directoryManager.createSpeciesSeedFile();
//			
//			for(Specie specie : directoryManager.getSpecieReposiory().findAll()){
//				
//				System.out.println(specie.getScientificName());
//				
//			}
//			
//		};
//	}
}
