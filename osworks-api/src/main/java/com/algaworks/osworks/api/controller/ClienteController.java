package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.service.CadastroClienteService;

@RestController // Serve para dizer que a classe é um controlador REST
@RequestMapping("/clientes") // Tudo o que tiver em '/clientes' ele vai fazer a requisição
// VER MAIS SOBRE O ASSUNTO DE RESTCONTROLLER E GETMAPPING
public class ClienteController {

	@Autowired // Dessa forma eu estou querendo pegar as informações de ClienteRepository
				// (Injeção de dependência)
	private ClienteRepository clienteRepository;

	@Autowired
	private CadastroClienteService cadastroClienteService;
	
//	@PersistenceContext // Desse modo ele vai 'injetar' as dependencias do EntityManager
//	private EntityManager manager;

	@GetMapping() // Aqui ele vai mapear o método para se colocado no GET do navegador
	public List<Cliente> listar() {
		/*
		 * var cliente1 = new Cliente(); cliente1.setId(1L); cliente1.setNome("João");
		 * cliente1.setTelefone("34 99999-1111");
		 * cliente1.setEmail("joaodascouves@algaworks.com");
		 * 
		 * var cliente2 = new Cliente(); cliente2.setId(2L); cliente2.setNome("Maria");
		 * cliente2.setTelefone("11 77777-1111");
		 * cliente2.setEmail("mariadasilva@algaworks.com");
		 * 
		 * return Arrays.asList(cliente1, cliente2);
		 */
//		return manager.createQuery("from Cliente", Cliente.class).getResultList();

		return clienteRepository.findAll();
//		 return clienteRepository.findByNome("João da Silva");
//		return clienteRepository.findByNomeContaining("Si"); 
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
//		return cliente.orElse(null); // No caso se não encontrar nenhum cliente ele retorna null
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping // Toda alteração ele passa por aqui devido ao POST do http
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente) { // O 'RequestBody' serve para transf. o json
																			// do
		// POSTMAN em objeto cliente
		return cadastroClienteService.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build(); // Questões do '@Valid' ver a implementação em 'Cliente'
		}

		cliente.setId(clienteId);
		cliente = cadastroClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) { // Como é uma remoção o correto seria colocar
																		// como void
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cadastroClienteService.excluir(clienteId);

		return ResponseEntity.noContent().build();
		/*
		 * Parâmetro de resposta correta para o tipo delete '204'. Ao invés de colocar o
		 * 200, pois assim que efetuar a transação, ele automaticamente exclui o dado
		 */
	}
}
