package br.com.grtvendasspringboot.dtos.entrada;

import br.com.grtvendasspringboot.models.Representante;

public class RepresentanteIdDTO {

	private Integer id;

	public Representante transformaParaObjeto() {
		return new Representante(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
