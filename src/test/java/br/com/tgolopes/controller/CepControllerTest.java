package br.com.tgolopes.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.tgolopes.PesquisaCepApplication;
import br.com.tgolopes.help.HelpTest;

/**
 * 
 * @author Thiago Oliveira Lopes
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PesquisaCepApplication.class)
@WebAppConfiguration
public class CepControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

	@Test
	public void pesquisarNumeroCepQueNaoExiste() throws Exception {
		String numCep = "09178963";
		this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andDo(print());
	}
	
	@Test
	public void pesquisarNumeroCepQueExiste() throws Exception {
		String numCep = "09175260";
		this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void pesquisarCepExistente() throws Exception {
    	String numCep = "78000000";
    	this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
    			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
    			.andDo(HelpTest.setContentType("charset=utf-8"))
    			.andExpect(jsonPath("logradouro", equalTo("Rua Caravelas")))
    			.andExpect(jsonPath("bairro", equalTo("Vila Pires")))
    			.andExpect(jsonPath("cidade", equalTo("Santo André")))
    			.andExpect(jsonPath("estado", equalTo("São Paulo")));
	}
	
	@Test
	public void pesquisarCepExistentePorProximidade() throws Exception {
    	String numCep = "09175261";
    	this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
    			.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
    			.andDo(HelpTest.setContentType("charset=utf-8"))
    			.andExpect(jsonPath("logradouro", equalTo("Rua Das Boninas")))
    			.andExpect(jsonPath("bairro", equalTo("Vila Helena")))
    			.andExpect(jsonPath("cidade", equalTo("Santo André")))
    			.andExpect(jsonPath("estado", equalTo("São Paulo")));
	}
	
	@Test
	public void validarCepFaltandoNumeros() throws Exception {
		String numCep = "1234";
		this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test
	public void validarCepNumerosAMais() throws Exception {
		String numCep = "123456789";
		this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test
	public void validarCepComLetras() throws Exception {
		String numCep = "12345aec";
		this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test
	public void validarCepComCaracterEspecial() throws Exception {
		String numCep = "12345ec-";
		this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test
	public void validarCepNulo() throws Exception {
		String numCep = null;
		this.mockMvc.perform(get(String.format("/pesquisarCep/%s", numCep))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andDo(print());
	}
}