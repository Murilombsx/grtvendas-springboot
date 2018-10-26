package br.com.grtvendasspringboot.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.grtvendasspringboot.dtos.entrada.ClienteDTO;
import br.com.grtvendasspringboot.dtos.entrada.ClienteEditavelDTO;
import br.com.grtvendasspringboot.dtos.resposta.ClienteDTOResposta;
import br.com.grtvendasspringboot.dtos.resposta.ClienteResumoDTOResposta;
import br.com.grtvendasspringboot.gerenciador.ClienteGerenciador;
import br.com.grtvendasspringboot.models.Cliente;

@Component
@Path("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteGerenciador clienteGerenciador;
	
	// Funcionando ok
	// Cadastra um cliente
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(ClienteDTO clienteDTO) {
		clienteGerenciador.cadastrar(clienteDTO.transformaParaObjeto());

		return Response.status(Response.Status.OK).build();
	}

	// Funcionando ok
	// Lista todos clientes de forma resumida
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClienteResumoDTOResposta> listar() {
		List<Cliente> clientesOriginalAuxiliar = clienteGerenciador.todosClientes();
		List<ClienteResumoDTOResposta> clientes = new ArrayList<>();
		for (Cliente clienteOriginalAuxiliar : clientesOriginalAuxiliar) {
			ClienteResumoDTOResposta cliente = new ClienteResumoDTOResposta().transformaEmDTO(clienteOriginalAuxiliar);
			clientes.add(cliente);
		}

		return clientes;
	}

	// Funcionando ok
	// Detalha um cliente, com todas informações sobre ele, incluso endereço,
	// representante e pedidos
	@GET
	@Path("/detalhe/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteDTOResposta detalhe(@PathParam("idCliente") Integer idCliente) {
		Cliente clienteOriginalAuxiliar = clienteGerenciador.buscaPorId(idCliente);
		ClienteDTOResposta cliente = new ClienteDTOResposta().transformaEmDTO(clienteOriginalAuxiliar);

		return cliente;
	}

	// Funcionando ok
	// Pode deletar um cliente através de seu id
	@POST
	@Path("/deletar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(Integer idCliente) {
		Cliente cliente = clienteGerenciador.buscaPorId(idCliente);
		clienteGerenciador.remove(cliente);

		return Response.status(Response.Status.OK).build();
	}

	// Funcionando ok
	// Possibilita mudar algumas informacoes do cliente, incluso endereço e
	// representante através de seu id
	@POST
	@Path("/editar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editar(ClienteEditavelDTO clienteEditavelDTO) {
		Cliente clienteOriginal = clienteGerenciador.buscaPorId(clienteEditavelDTO.getId());
		clienteGerenciador.atualiza(clienteOriginal, clienteEditavelDTO);

		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("/buscar/{nomeCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteResumoDTOResposta buscaPorNome(@PathParam("nomeCliente") String nomeCliente) {
		Cliente clienteOriginalAuxiliar = clienteGerenciador.buscaPorNome(nomeCliente);
		ClienteResumoDTOResposta cliente = new ClienteResumoDTOResposta().transformaEmDTO(clienteOriginalAuxiliar);

		return cliente;
	}

	@GET
	@Path("/teste")
	@Produces(MediaType.APPLICATION_JSON)
	public String teste() {
		String teste;
		teste = "testando 123";

		return teste;
	}

}
