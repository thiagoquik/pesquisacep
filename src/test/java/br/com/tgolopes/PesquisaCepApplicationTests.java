package br.com.tgolopes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Thiago Oliveira Lopes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PesquisaCepApplication.class)
@WebAppConfiguration
public class PesquisaCepApplicationTests {

	@Test
	public void contextLoads() {
	}

}