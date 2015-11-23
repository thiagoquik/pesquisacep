package br.com.tgolopes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe responsável por lançar uma exceção quando o cep for inválido.
 * Esta classe é orquestrada pela classe ControllerException
 * 
 * @author Thiago Oliveira Lopes
 *
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cep digitado é inválido!")
public class CepInvalidoException extends Exception{
	private static final long serialVersionUID = 656876L;
}