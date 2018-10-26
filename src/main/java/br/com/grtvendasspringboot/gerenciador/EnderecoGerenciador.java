package br.com.grtvendasspringboot.gerenciador;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.grtvendasspringboot.daos.EnderecoDao;
import br.com.grtvendasspringboot.models.Endereco;

@Component
public class EnderecoGerenciador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EnderecoDao enderecoDao;
	
	public Endereco buscaPorId(int id) {
		return enderecoDao.buscaPorId(id);
	}
	
	public List<Endereco> todosEnderecos() {
		return enderecoDao.listar();
	}

	public Endereco cadastrar(Endereco endereco) {
		enderecoDao.cadastrar(endereco);
		
		return endereco;
	}

}
