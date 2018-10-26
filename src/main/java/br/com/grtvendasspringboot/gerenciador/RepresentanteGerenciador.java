package br.com.grtvendasspringboot.gerenciador;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.grtvendasspringboot.daos.EnderecoDao;
import br.com.grtvendasspringboot.daos.RepresentanteDao;
import br.com.grtvendasspringboot.dtos.entrada.RepresentanteEditavelDTO;
import br.com.grtvendasspringboot.models.Cliente;
import br.com.grtvendasspringboot.models.Endereco;
import br.com.grtvendasspringboot.models.Pedido;
import br.com.grtvendasspringboot.models.Representante;

@Component
public class RepresentanteGerenciador {

	@Autowired
	private RepresentanteDao representanteDao;

	@Autowired
	private EnderecoDao enderecoDao;

	public List<Representante> todosRepresentantes() {
		return representanteDao.listar();
	}

	public Representante buscaPorId(Integer id) {
		return representanteDao.buscaPorId(id);
	}
	
	public Representante buscaPorNome(String nome) {
		return representanteDao.buscaPorNome(nome);
	}

	@Transactional
	public void remove(Representante representante) {
		Set<Pedido> pedidos = representante.getPedidos();
		Set<Cliente> clientes = representante.getClientes();
		if (!pedidos.isEmpty() || !clientes.isEmpty()) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Não é possível remover um representante com um cliente ou pedido associado").build());
		} else {
			representanteDao.remove(representante);
		}

	}

	@Transactional
	public Representante cadastrar(Representante representante) {
		
		boolean representanteExiste = representanteDao.existe(representante.getCnpj());
		if(representanteExiste) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Esse representante já está cadastrado no sistema").build());
		}

		Endereco endereco = representante.getEndereco();

		if (endereco.getRua() == null || endereco.getRua().trim().equals("") || endereco.getNumero() == null
				|| endereco.getNumero().trim().equals("") || endereco.getBairro() == null
				|| endereco.getBairro().trim().equals("") || endereco.getCidade() == null
				|| endereco.getCidade().trim().equals("") || endereco.getEstado() == null
				|| endereco.getEstado().trim().equals("") || endereco.getCep() == null
				|| endereco.getCep().trim().equals("")) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Campos obrigatórios de endereço não foram preenchidos!").build());
		} else {
			enderecoDao.cadastrar(endereco);
		}
		Date data = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		representante.setDataEntrada(cal);
		if (representante.getCnpj() == null || representante.getCnpj().trim().equals("")
				|| representante.getCpf() == null || representante.getCpf().trim().equals("")
				|| representante.getEmail() == null || representante.getEmail().trim().equals("")
				|| representante.getRazaoSocial() == null || representante.getRazaoSocial().trim().equals("")
				|| representante.getTelefone() == null || representante.getTelefone().trim().equals("")
				|| representante.getRg() == null || representante.getRg().trim().equals("")
				|| representante.getDataEntrada() == null || representante.getDataEntrada().toString().trim().equals("")
				|| representante.getNome() == null || representante.getNome().trim().equals("")) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
					.entity("Campos obrigatórios não foram preenchidos!").build());
		} else {
			representanteDao.cadastrar(representante);
		}
		return representante;
	}

	@Transactional
	public void atualiza(Representante representante, RepresentanteEditavelDTO representanteEditavelDTO) {
		if ((representante.getEmail() != representanteEditavelDTO.getEmail())
				&& (representanteEditavelDTO.getEmail() != null)
				&& (!representanteEditavelDTO.getEmail().trim().equals(""))) {
			representante.setEmail(representanteEditavelDTO.getEmail());
		}

		if ((representante.getTelefone() != representanteEditavelDTO.getTelefone())
				&& (representanteEditavelDTO.getTelefone() != null)
				&& (!representanteEditavelDTO.getTelefone().trim().equals(""))) {
			representante.setTelefone(representanteEditavelDTO.getTelefone());
		}

		if ((representante.getEndereco().getRua() != representanteEditavelDTO.getEndereco().getRua())
				&& (representanteEditavelDTO.getEndereco().getRua() != null)
				&& (!representanteEditavelDTO.getEndereco().getRua().trim().equals(""))) {
			representante.getEndereco().setRua(representanteEditavelDTO.getEndereco().getRua());
		}

		if ((representante.getEndereco().getNumero() != representanteEditavelDTO.getEndereco().getNumero())
				&& (representanteEditavelDTO.getEndereco().getNumero() != null)
				&& (!representanteEditavelDTO.getEndereco().getNumero().trim().equals(""))) {
			representante.getEndereco().setNumero(representanteEditavelDTO.getEndereco().getNumero());
		}

		if ((representante.getEndereco().getComplemento() != representanteEditavelDTO.getEndereco().getComplemento())
				&& (representanteEditavelDTO.getEndereco().getComplemento() != null)
				&& (!representanteEditavelDTO.getEndereco().getComplemento().trim().equals(""))) {
			representante.getEndereco().setComplemento(representanteEditavelDTO.getEndereco().getComplemento());
		}

		if ((representante.getEndereco().getBairro() != representanteEditavelDTO.getEndereco().getBairro())
				&& (representanteEditavelDTO.getEndereco().getBairro() != null)
				&& (!representanteEditavelDTO.getEndereco().getBairro().trim().equals(""))) {
			representante.getEndereco().setBairro(representanteEditavelDTO.getEndereco().getBairro());
		}

		if ((representante.getEndereco().getCidade() != representanteEditavelDTO.getEndereco().getCidade())
				&& (representanteEditavelDTO.getEndereco().getCidade() != null)
				&& (!representanteEditavelDTO.getEndereco().getCidade().trim().equals(""))) {
			representante.getEndereco().setCidade(representanteEditavelDTO.getEndereco().getCidade());
		}

		if ((representante.getEndereco().getEstado() != representanteEditavelDTO.getEndereco().getEstado())
				&& (representanteEditavelDTO.getEndereco().getEstado() != null)
				&& (!representanteEditavelDTO.getEndereco().getEstado().trim().equals(""))) {
			representante.getEndereco().setEstado(representanteEditavelDTO.getEndereco().getEstado());
		}

		if ((representante.getEndereco().getCep() != representanteEditavelDTO.getEndereco().getCep())
				&& (representanteEditavelDTO.getEndereco().getCep() != null)
				&& (!representanteEditavelDTO.getEndereco().getCep().trim().equals(""))) {
			representante.getEndereco().setCep(representanteEditavelDTO.getEndereco().getCep());
		}

		representanteDao.atualiza(representante);
	}

}
