package com.algaworks.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	// Se por acaso eu quiser fazer uma consulta PERSONALIZADA? Vejamos abaixo...
	List<Cliente> findByNome(String nome); // Sempre é melhor criar seguindo um padrão do framework
	// QUERY METHODS

	List<Cliente> findByNomeContaining(String nome); // Irá buscar o nome pelo trecho da palavra
}
