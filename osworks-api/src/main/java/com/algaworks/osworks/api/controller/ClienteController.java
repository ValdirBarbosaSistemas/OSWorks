package com.algaworks.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;

@RestController // Serve para dizer que a classe é um controlador REST
public class ClienteController {

	@GetMapping("/clientes") // Aqui ele vai mapear o método para se colocado no GET do navegador
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("João");
		cliente1.setTelefone("34 99999-1111");
		cliente1.setEmail("joaodascouves@algaworks.com");

		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setTelefone("11 77777-1111");
		cliente2.setEmail("mariadasilva@algaworks.com");

		return Arrays.asList(cliente1, cliente2);
	}

	// VER MAIS SOBRE O ASSUNTO DE RESTCONTROLLER E GETMAPPING
}
