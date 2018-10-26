package br.com.grtvendasspringboot.dtos.entrada;

import br.com.grtvendasspringboot.models.Endereco;

/*
	Classe de inserção dos dados de um endereço, que filtra apenas os campos que deseja receber:
		- todos campos do endereço, menos o id
*/

public class EnderecoDTO {

	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;

	public Endereco transformaParaObjeto() {
		return new Endereco(rua, numero, complemento, bairro, cidade, estado, cep);
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
