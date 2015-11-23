package br.com.tgolopes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Classe responsável por controlar as páginas da aplicação.
 * 
 * @author Thiago Oliveira Lopes
 *
 */

@Controller
@RequestMapping(value = "/")
public class ViewController {
	/**
	 * Este método será invocado quando não for digitado nenhum parametro via GET e ele retorna a página pesquisar.html.
	 * @return
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String abrirPesquisaCep() {
        return "pesquisar";
    }
    
    /**
     * Este método será invocado quando  for digitado o parametro pesquisaCep via GET e ele retorna a página pesquisar.html, passando pesquisaCep via GET.
     * @return
     */
    @RequestMapping(value = "pesquisaCep", method = RequestMethod.GET)
    public String paginaConsultarCep() {
        return "pesquisar";
    }
}