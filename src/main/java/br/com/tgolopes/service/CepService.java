package br.com.tgolopes.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.tgolopes.entity.Cep;
import br.com.tgolopes.exception.CepNaoEncontradoException;
import br.com.tgolopes.repository.CepRepository;

/**
 * Classe que representa os serviços de busca de cep que será consumido pela classe CepController
 * 
 * @author Thiago Oliveira Lopes
 *
 */
@Service
@Validated
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CepService {

	//Atributo que representa a injeção de dependência 
	@Autowired
	private CepRepository cepRep;
	
	/**
	 * Método que busca todos os ceps.
	 * @return
	 */
	public List<Cep> consultarTodos(){
		return this.cepRep.findAll();
	}
	
	/**
	 * Método que busca apenas um cep, passando o número do cep por parâmetro.
	 * Caso o cep não seja encontrado ele mostrará uma mensagem "Cep não encontrado!".
	 * Retorna o Cep salvo.
	 * @param cep
	 * @return
	 * @throws CepNaoEncontradoException
	 */
	public Cep consultarPorCep(String cep) throws CepNaoEncontradoException {
		Validate.isTrue(cep.matches("\\d{8}"), "CEP inválido.");
		Cep cepConsultado = this.cepRep.findOneByNumCep(cep);
		return cepConsultado;
	}
	
	/**
	 * Método que caso o cep não seja encontrado ele substitui o último digito por 0 até encontrar um cep por proximidade ou até seu valor chegar em 0. 
	 * Caso o cep não seja encontrado ele mostrará uma mensagem "Cep não encontrado!".
	 * @param cep
	 * @return
	 * @throws CepNaoEncontradoException
	 */
	public String substituirUltimoDigitoCep(String cep) throws CepNaoEncontradoException{
		int cepZerado = Integer.parseInt(cep);
		if(cepZerado <= 0){
			throw new CepNaoEncontradoException();
		}
		while(cep.charAt(cep.length() - 1) == '0'){
			cep = StringUtils.removeEnd(cep, "0");
		}
		return StringUtils.rightPad(cep.substring(0, cep.length() - 1),8, "0");
	}
}