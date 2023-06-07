package com.br.usuarioatividade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UsuarioAtividadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioAtividadeApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "!";
	}
}
