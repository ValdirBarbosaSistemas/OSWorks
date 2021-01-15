package com.algaworks.osworks.api.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/* Essa annotation serve para dizer que nesse componente trabalharemos com
EXCEPTION no que tudo que se diz respeito a 'Controller'*/
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
}
