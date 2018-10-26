package br.com.grtvendasspringboot.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Repository;

import br.com.grtvendasspringboot.models.Usuario;

@Repository
@Transactional
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public Usuario autentica(Usuario usuario) {
		try {
			return manager
					.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha", Usuario.class)
					.setParameter("email", usuario.getEmail()).setParameter("senha", usuario.getSenha())
					.getSingleResult();
		} catch (NoResultException e) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Usuário ou senha incorreta!").build());
		}
	}

}
