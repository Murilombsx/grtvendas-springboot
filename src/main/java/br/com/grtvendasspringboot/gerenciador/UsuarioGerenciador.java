package br.com.grtvendasspringboot.gerenciador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.grtvendasspringboot.daos.UsuarioDao;
import br.com.grtvendasspringboot.models.Usuario;
import br.com.grtvendasspringboot.utilitarios.CriptografiaService;

@Component
public class UsuarioGerenciador {

	@Autowired
	private UsuarioDao usuarioDao;
	
	public Usuario autentica(Usuario usuario) {
		usuario.setSenha(new CriptografiaService().criptografar(usuario.getSenha()));
		return usuarioDao.autentica(usuario);
	}

}
