package br.com.tgolopes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tgolopes.entity.Cep;


/**
 * Interface que representa o DAO do Cep da aplicação, ela que faz a comunicação da transação com o banco de dados.
 * 
 * @author Thiago Oliveira Lopes
 *
 */
@Repository
public interface CepRepository extends JpaRepository<Cep, Long>{
	
	/**
	 * Método responsável por consultar um cep passando seu número como parâmetro.
	 * 
	 * @param cep
	 * @return
	 */
	Cep findOneByNumCep(String cep);
}
