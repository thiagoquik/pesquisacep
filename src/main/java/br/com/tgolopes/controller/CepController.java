package br.com.tgolopes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tgolopes.entity.Cep;
import br.com.tgolopes.exception.CepNaoEncontradoException;
import br.com.tgolopes.service.CepService;

/**
 * Classe que representa o serviço REST pela anotação @RestController que irá controlar a aplicação pelas chamadas do browser.
 * A anotação @RequestMapping mapea a url e a captura quando  mesma for digitada no browser.
 * 
 * @author Thiago Oliveira Lopes
 * 
 */

@RestController
@RequestMapping(value = "/pesquisarCep")
public class CepController {
	
	//Atributo que representa a injeção de dependência 
	@Autowired
	private CepService cepRepository;
	
	/**
	 * Método responsável por mostar todos os ceps no formato JSON pela requisição GET
	 * @return
	 */
	@RequestMapping(value = "/pesquisarTodos", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Cep> pesquisarTodos(){
		return cepRepository.consultarTodos();
	}
	
	/**
	 * Método responsáverl por mostrar apenas um cep em formato JSON, passando apelas seu número por GET.
	 * Caso o cep não seja encontrado ele mostrará uma mensagem "Cep não encontrado!".
	 * @return 
	 * @param cep
	 * @throws CepNaoEncontradoException
	 */
	@RequestMapping(value = "/{cep}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Cep pesquisarPorNumeroCep(@PathVariable String cep) throws CepNaoEncontradoException {
		Cep cepRecebido = cepRepository.consultarPorCep(cep);
		if(cepRecebido != null){
			return cepRecebido;
		}else{
			return this.pesquisarPorNumeroCep(cepRepository.substituirUltimoDigitoCep(cep));
		}
		 
	} 
}
