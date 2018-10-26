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

import br.com.grtvendasspringboot.dtos.entrada.PedidoDTO;
import br.com.grtvendasspringboot.dtos.entrada.PedidoEditavelDTO;
import br.com.grtvendasspringboot.dtos.resposta.PedidoDTOResposta;
import br.com.grtvendasspringboot.dtos.resposta.PedidoResumoDTOResposta;
import br.com.grtvendasspringboot.gerenciador.PedidoGerenciador;
import br.com.grtvendasspringboot.models.Pedido;

@Component
@Path("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoGerenciador pedidoGerenciador;

	// Funcionando ok
	// Lista todos pedidos de forma resumida
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PedidoResumoDTOResposta> listar() {
		List<Pedido> pedidosOriginalAuxiliar = pedidoGerenciador.todosPedidos();
		List<PedidoResumoDTOResposta> pedidos = new ArrayList<>();
		for (Pedido pedidoOriginalAuxiliar : pedidosOriginalAuxiliar) {
			PedidoResumoDTOResposta pedido = new PedidoResumoDTOResposta().transformaEmDTO(pedidoOriginalAuxiliar);
			pedidos.add(pedido);
		}

		return pedidos;
	}

	// Funcionando ok
	// Detalha um pedido, com todas informações sobre ele, incluso
	// representante e cliente
	@GET
	@Path("/detalhe/{idPedido}")
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoDTOResposta detalhe(@PathParam("idPedido") Integer idPedido) {
		Pedido pedidoOriginalAuxiliar = pedidoGerenciador.buscaPorId(idPedido);
		PedidoDTOResposta pedido = new PedidoDTOResposta().transformaEmDTO(pedidoOriginalAuxiliar);

		return pedido;
	}

	// Funcionando ok
	// Pode deletar um pedido através de seu id
	@POST
	@Path("/deletar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(Integer idPedido) {
		Pedido pedido = pedidoGerenciador.buscaPorId(idPedido);
		pedidoGerenciador.remove(pedido);

		return Response.status(Response.Status.OK).build();
	}

	// Funcionando ok
	// Cadastra um pedido
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(PedidoDTO pedidoDTO) {
		pedidoGerenciador.cadastrar(pedidoDTO.transformaParaObjeto());

		return Response.status(Response.Status.OK).build();
	}

	// Funcionando ok
	// Possibilita mudar algumas informacoes do pedido
	@POST
	@Path("/editar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editar(PedidoEditavelDTO pedidoEditavelDTO) {
		Pedido pedidoOriginal = pedidoGerenciador.buscaPorId(pedidoEditavelDTO.getId());
		pedidoGerenciador.atualiza(pedidoOriginal, pedidoEditavelDTO);

		return Response.status(Response.Status.OK).build();
	}

}
