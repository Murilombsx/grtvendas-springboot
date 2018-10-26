package br.com.grtvendasspringboot.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.grtvendasspringboot.gerenciador.EnderecoGerenciador;
import br.com.grtvendasspringboot.models.Endereco;

@Component
@Path("/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoGerenciador enderecoGerenciador;
	
	// FUNCIONANDO OK
	@GET
	@Path("/detalhe/{idEndereco}")
	@Produces(MediaType.APPLICATION_JSON)
	public Endereco descricao(@PathParam("idEndereco") Integer idEndereco) {
		return enderecoGerenciador.buscaPorId(idEndereco);
	}

	// FUNCIONANDO OK
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Endereco> descricao() {
		return enderecoGerenciador.todosEnderecos();
	}

}
