package br.com.grtvendasspringboot.gerenciador;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.grtvendasspringboot.daos.ClienteDao;
import br.com.grtvendasspringboot.daos.EnderecoDao;
import br.com.grtvendasspringboot.dtos.entrada.ClienteEditavelDTO;
import br.com.grtvendasspringboot.models.Cliente;
import br.com.grtvendasspringboot.models.Endereco;
import br.com.grtvendasspringboot.models.Pedido;

@Component
public class ClienteGerenciador {
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private EnderecoDao enderecoDao;
	
	@Transactional
	public Cliente cadastrar(Cliente cliente) {
		
		boolean clienteExiste = clienteDao.existe(cliente.getCnpj());
		if(clienteExiste) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Esse cliente já está cadastrado no sistema").build());
		}

		if (cliente.getRepresentante().getId() == null) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("É necessário ter um representante associado ao cliente").build());
		}

		Endereco endereco = cliente.getEndereco();

		if (endereco.getRua() == null || endereco.getRua().trim().equals("") || endereco.getNumero() == null
				|| endereco.getNumero().trim().equals("") || endereco.getBairro() == null
				|| endereco.getBairro().trim().equals("") || endereco.getCidade() == null
				|| endereco.getCidade().trim().equals("") || endereco.getEstado() == null
				|| endereco.getEstado().trim().equals("") || endereco.getCep() == null
				|| endereco.getCep().trim().equals("")) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Campos obrigatórios de endereço não foram preenchidos").build());
		} else {
			enderecoDao.cadastrar(endereco);
		}

		if (cliente.getCnpj() == null || cliente.getCnpj().trim().equals("") || cliente.getInscricaoEstadual() == null
				|| cliente.getInscricaoEstadual().trim().equals("") || cliente.getEmail() == null
				|| cliente.getEmail().trim().equals("") || cliente.getRazaoSocial() == null
				|| cliente.getRazaoSocial().trim().equals("") || cliente.getTelefone() == null
				|| cliente.getTelefone().trim().equals("") || cliente.getPessoaContato() == null
				|| cliente.getPessoaContato().trim().equals("")) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Campos obrigatórios não foram preenchidos").build());
		} else {
			clienteDao.cadastrar(cliente);
		}
		return cliente;
	}

	public List<Cliente> todosClientes() {
		return clienteDao.listar();
	}

	public Cliente buscaPorId(int id) {
		return clienteDao.buscaPorId(id);
	}

	@Transactional
	public void remove(Cliente cliente) {
		Set<Pedido> pedidos = cliente.getPedidos();
		if (!pedidos.isEmpty()) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Não é possível remover um cliente com pedidos cadastrados").build());
		} else {
			clienteDao.remove(cliente);
		}
	}

	@Transactional
	public void atualiza(Cliente cliente, ClienteEditavelDTO clienteEditavelDTO) {
		if ((cliente.getNomeFantasia() != clienteEditavelDTO.getNomeFantasia())
				&& (clienteEditavelDTO.getNomeFantasia() != null)
				&& (!clienteEditavelDTO.getNomeFantasia().trim().equals(""))) {
			cliente.setNomeFantasia(clienteEditavelDTO.getNomeFantasia());
		}

		if ((cliente.getPessoaContato() != clienteEditavelDTO.getPessoaContato())
				&& (clienteEditavelDTO.getPessoaContato() != null)
				&& (!clienteEditavelDTO.getPessoaContato().trim().equals(""))) {
			cliente.setPessoaContato(clienteEditavelDTO.getPessoaContato());
		}

		if ((cliente.getEmail() != clienteEditavelDTO.getEmail()) && (clienteEditavelDTO.getEmail() != null)
				&& (!clienteEditavelDTO.getEmail().trim().equals(""))) {
			cliente.setEmail(clienteEditavelDTO.getEmail());
		}

		if ((cliente.getTelefone() != clienteEditavelDTO.getTelefone()) && (clienteEditavelDTO.getTelefone() != null)
				&& (!clienteEditavelDTO.getTelefone().trim().equals(""))) {
			cliente.setTelefone(clienteEditavelDTO.getTelefone());
		}

		if ((cliente.getEndereco().getRua() != clienteEditavelDTO.getEndereco().getRua())
				&& (clienteEditavelDTO.getEndereco().getRua() != null)
				&& (!clienteEditavelDTO.getEndereco().getRua().trim().equals(""))) {
			cliente.getEndereco().setRua(clienteEditavelDTO.getEndereco().getRua());
		}

		if ((cliente.getEndereco().getNumero() != clienteEditavelDTO.getEndereco().getNumero())
				&& (clienteEditavelDTO.getEndereco().getNumero() != null)
				&& (!clienteEditavelDTO.getEndereco().getNumero().trim().equals(""))) {
			cliente.getEndereco().setNumero(clienteEditavelDTO.getEndereco().getNumero());
		}

		if ((cliente.getEndereco().getComplemento() != clienteEditavelDTO.getEndereco().getComplemento())
				&& (clienteEditavelDTO.getEndereco().getComplemento() != null)
				&& (!clienteEditavelDTO.getEndereco().getComplemento().trim().equals(""))) {
			cliente.getEndereco().setComplemento(clienteEditavelDTO.getEndereco().getComplemento());
		}

		if ((cliente.getEndereco().getBairro() != clienteEditavelDTO.getEndereco().getBairro())
				&& (clienteEditavelDTO.getEndereco().getBairro() != null)
				&& (!clienteEditavelDTO.getEndereco().getBairro().trim().equals(""))) {
			cliente.getEndereco().setBairro(clienteEditavelDTO.getEndereco().getBairro());
		}

		if ((cliente.getEndereco().getCidade() != clienteEditavelDTO.getEndereco().getCidade())
				&& (clienteEditavelDTO.getEndereco().getCidade() != null)
				&& (!clienteEditavelDTO.getEndereco().getCidade().trim().equals(""))) {
			cliente.getEndereco().setCidade(clienteEditavelDTO.getEndereco().getCidade());
		}

		if ((cliente.getEndereco().getEstado() != clienteEditavelDTO.getEndereco().getEstado())
				&& (clienteEditavelDTO.getEndereco().getEstado() != null)
				&& (!clienteEditavelDTO.getEndereco().getEstado().trim().equals(""))) {
			cliente.getEndereco().setEstado(clienteEditavelDTO.getEndereco().getEstado());
		}

		if ((cliente.getEndereco().getCep() != clienteEditavelDTO.getEndereco().getCep())
				&& (clienteEditavelDTO.getEndereco().getCep() != null)
				&& (!clienteEditavelDTO.getEndereco().getCep().trim().equals(""))) {
			cliente.getEndereco().setCep(clienteEditavelDTO.getEndereco().getCep());
		}

		if ((cliente.getRepresentante().getId() != clienteEditavelDTO.getRepresentante().getId())
				&& (clienteEditavelDTO.getRepresentante().getId() != null)
				&& (!clienteEditavelDTO.getRepresentante().getId().toString().trim().equals(""))) {
			cliente.getRepresentante().setId(clienteEditavelDTO.getRepresentante().getId());
		}

		clienteDao.atualiza(cliente);
	}

	public Cliente buscaPorNome(String nome) {
		return clienteDao.buscaPorNome(nome);
	}

}
