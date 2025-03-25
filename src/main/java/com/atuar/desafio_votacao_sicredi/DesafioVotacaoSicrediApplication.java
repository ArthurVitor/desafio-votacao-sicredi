package com.atuar.desafio_votacao_sicredi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DesafioVotacaoSicrediApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioVotacaoSicrediApplication.class, args);
	}

}
