package br.org.itv.guia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
