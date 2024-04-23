package com.sinerji.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 80)
	private String nome;
	
	@Column(name = "idade", nullable = false, length = 3)
	private int idade;
	
	@Column(name = "sexo", nullable = false, length = 18)
	private char sexo;
	
	@Transient
	private ArrayList<Endereco> listaEnderecos = new ArrayList<Endereco>();
	
	public Pessoa() {
		
	}
	
	//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public ArrayList<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(ArrayList<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	
}
