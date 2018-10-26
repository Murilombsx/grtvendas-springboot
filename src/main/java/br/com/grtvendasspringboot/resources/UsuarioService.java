package br.com.grtvendasspringboot.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.grtvendasspringboot.dtos.entrada.UsuarioDTO;
import br.com.grtvendasspringboot.gerenciador.UsuarioGerenciador;
import br.com.grtvendasspringboot.models.Usuario;
import br.com.grtvendasspringboot.utilitarios.LoginService;

@Component
@Path("/usuario")
public class UsuarioService {

	@Autowired
	private UsuarioGerenciador usuarioGerenciador;
	
	// Funcionando ok
	// Autentica um usuário
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioGerenciador.autentica(usuarioDTO.transformaParaObjeto());
		
		String token = new LoginService().geraToken(usuario.getId(), usuario.getEmail(), usuario.getNome(),1);
		
		Response.ResponseBuilder rb = Response.status(Response.Status.OK).header("Access-Control-Expose-Headers", "*");
		Response response = rb.header("x-access-token", token)
								.build();
		
		return response;
		//return Response.ok(token).build();
		//return Response.status(Response.Status.OK).build();
	}
	
}
