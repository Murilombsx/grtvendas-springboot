package br.com.grtvendasspringboot.confgs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.com.grtvendasspringboot.resources.ClienteResource;
import br.com.grtvendasspringboot.resources.EnderecoResource;
import br.com.grtvendasspringboot.resources.PedidoResource;
import br.com.grtvendasspringboot.resources.RepresentanteResource;
import br.com.grtvendasspringboot.resources.UsuarioResource;

@Component
public class JaxRsConfig extends ResourceConfig {
	
	public JaxRsConfig() {
		register(ClienteResource.class);
		register(EnderecoResource.class);
		register(PedidoResource.class);
		register(RepresentanteResource.class);
		register(UsuarioResource.class);
	}

}
