package br.com.grtvendasspringboot.dtos.entrada;

import br.com.grtvendasspringboot.models.Cliente;

public class ClienteResumoDTO {

	private Integer id;
	private String nomeFantasia;

	public Cliente transformaParaObjeto() {
		return new Cliente(id, nomeFantasia);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

}
