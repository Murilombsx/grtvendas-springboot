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

import br.com.grtvendasspringboot.dtos.entrada.RepresentanteDTO;
import br.com.grtvendasspringboot.dtos.entrada.RepresentanteEditavelDTO;
import br.com.grtvendasspringboot.dtos.resposta.RepresentanteDTOResposta;
import br.com.grtvendasspringboot.dtos.resposta.RepresentanteIdEstDTOResposta;
import br.com.grtvendasspringboot.dtos.resposta.RepresentanteResumoDTOResposta;
import br.com.grtvendasspringboot.gerenciador.RepresentanteGerenciador;
import br.com.grtvendasspringboot.models.Representante;

@Component
@Path("/representantes")
public class RepresentanteResource {

	@Autowired
	private RepresentanteGerenciador representanteGerenciador;

	// Funcionando ok
	// Lista todos representantes de forma resumida
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RepresentanteResumoDTOResposta> listar() {
		List<Representante> representantesOriginalAuxiliar = representanteGerenciador.todosRepresentantes();
		List<RepresentanteResumoDTOResposta> representantes = new ArrayList<>();
		for (Representante representanteOriginalAuxiliar : representantesOriginalAuxiliar) {
			RepresentanteResumoDTOResposta representante = new RepresentanteResumoDTOResposta()
					.transformaEmDTO(representanteOriginalAuxiliar);
			representantes.add(representante);
		}

		return representantes;
	}

	// Funcionando ok
	// Detalha um representante, com todas informações sobre ele, incluso endereço,
	// clientes e pedidos
	@GET
	@Path("/detalhe/{idRepresentante}")
	@Produces(MediaType.APPLICATION_JSON)
	public RepresentanteDTOResposta detalhe(@PathParam("idRepresentante") Integer idRepresentante) {
		Representante representanteOriginalAuxiliar = representanteGerenciador.buscaPorId(idRepresentante);
		RepresentanteDTOResposta representante = new RepresentanteDTOResposta()
				.transformaEmDTO(representanteOriginalAuxiliar);

		return representante;
	}

	// Funcionando ok
	// Pode deletar um representante através de seu id
	@POST
	@Path("/deletar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletar(Integer idRepresentante) {
		Representante representante = representanteGerenciador.buscaPorId(idRepresentante);
		representanteGerenciador.remove(representante);

		return Response.status(Response.Status.OK).build();
	}

	// Funcionando ok
	// Cadastra um representante
	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(RepresentanteDTO representanteDTO) {
		representanteGerenciador.cadastrar(representanteDTO.transformaParaObjeto());

		return Response.status(Response.Status.OK).build();
	}

	// Funcionando ok
	// Possibilita mudar algumas informacoes do representante, incluso endereço
	@POST
	@Path("/editar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editar(RepresentanteEditavelDTO representanteEditavelDTO) {
		Representante representanteOriginal = representanteGerenciador.buscaPorId(representanteEditavelDTO.getId());
		representanteGerenciador.atualiza(representanteOriginal, representanteEditavelDTO);

		return Response.status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/buscar/{nomeRepresentante}")
	@Produces(MediaType.APPLICATION_JSON)
	public RepresentanteIdEstDTOResposta buscaPorNome(@PathParam("nomeRepresentante") String nomeRepresentante) {
		Representante representanteOriginalAuxiliar = representanteGerenciador.buscaPorNome(nomeRepresentante);
		RepresentanteIdEstDTOResposta representante = new RepresentanteIdEstDTOResposta()
				.transformaEmDTO(representanteOriginalAuxiliar);

		return representante;
	}

}
