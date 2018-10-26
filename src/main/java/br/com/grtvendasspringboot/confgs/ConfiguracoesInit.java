package br.com.grtvendasspringboot.confgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages= {"br.com.grtvendasspringboot.confgs"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages= {"br.com.grtvendasspringboot"})
public class ConfiguracoesInit extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ConfiguracoesInit.class, args);
	}
	
	
	
}
