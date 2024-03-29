package com.algaworks.osworks.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/* Essa annotation serve para dizer que nesse componente trabalharemos com
EXCEPTION no que tudo que se diz respeito a 'Controller'*/
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var campos = new ArrayList<Problema.Campo>();
//FIXME ESTUDAR COM MAIS AFINCO A IMPLEMENTAÇÃO DO FOREACH
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
//FIXME ESTUDAR COM MAIS AFINCO A QUESTÃO DO 'MESSAGE.PROPERTIES' PARA GERAR MENSAGENS PERSONALIZADAS
			campos.add(new Problema.Campo(nome, mensagem));
		}
		var problema = new Problema(); // Instanciando a classe 'Problema'
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		problema.setDataHora(LocalDateTime.now());
		problema.setCampos(campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
		/*
		 * Ver depois a importância do 'handleExceptionInternal' ao invés de colocar o
		 * nome do método criado.
		 */
	}
}
