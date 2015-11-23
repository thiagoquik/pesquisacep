package br.com.tgolopes.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Classe que representa a Entidade CEP no banco de dados.
 * 
 * @author Thiago Oliveira Lopes
 *
 */
@Entity
public class Cep implements Serializable{
	private static final long serialVersionUID = 987987987654654654L;
	
	@Id
	@Column(name = "ID_CEP")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(max = 8, message = "O Cep é composto por apenas 8 números.")
	@Pattern(regexp = "\\d{8}", message = "Cep deve conter 8 números")
	@NotNull
	@Column(name = "NUM_CEP")
	private String numCep;
	
	@NotNull
	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@NotNull
	@Column(name = "CIDADE")
	private String cidade;
	
	@NotNull
	@Column(name = "ESTADO")
	private String estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumCep() {
		return numCep;
	}

	public void setNumCep(String numCep) {
		this.numCep = numCep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}