package br.com.tgolopes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe responsável por lançar uma exceção quando o cep não é encontrado.
 * Esta classe é orquestrada pela classe ControllerException
 * 
 * @author Thiago Oliveira Lopes
 *
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cep não encontrado!")
public class CepNaoEncontradoException extends Exception{
	private static final long serialVersionUID = 146544644L;
}