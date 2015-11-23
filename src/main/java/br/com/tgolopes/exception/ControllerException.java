
package br.com.tgolopes.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe responsável por orquestar as exceções lançadas pela aplicação por meio da anotação @ControllerAdvice.
 * 
 * @author Thiago Oliveira Lopes
 *
 */

@ControllerAdvice
public class ControllerException {
	
	@ExceptionHandler(DataAccessException.class)
	public void handlerDataAccessException(HttpServletResponse response, Exception ex) throws IOException{
		String messageEx = String.format("Houve um erro ao acessar os dados: ", ex.getMessage());
		response.sendError(HttpStatus.BAD_GATEWAY.value(), messageEx);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public void handlerConstraintViolationException(HttpServletResponse response, Exception ex) throws IOException{
		String messageEx = String.format("O Cep digitado está incorreto ou vazio!", ex.getMessage());
		response.sendError(HttpStatus.BAD_REQUEST.value(), messageEx);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public void handleIllegalArgumentException(HttpServletResponse response, Exception ex) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
}