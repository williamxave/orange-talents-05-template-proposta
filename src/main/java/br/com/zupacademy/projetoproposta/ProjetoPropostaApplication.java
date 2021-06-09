package br.com.zupacademy.projetoproposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class ProjetoPropostaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjetoPropostaApplication.class, args);
	}
}
