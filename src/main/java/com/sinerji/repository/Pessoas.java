package com.sinerji.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.sinerji.model.Endereco;
import com.sinerji.model.Pessoa;

import service.CadastroEnderecoService;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@Inject
	private CadastroEnderecoService cadastroEndereco;

	public Pessoas() {

	}

	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> pesquisar(String nome) {
		String jpql = "from Pessoa where nome like :nome ";
		
		TypedQuery<Pessoa> query = manager
				.createQuery(jpql, Pessoa.class);
		
		query.setParameter("nome", nome + "%");
		return query.getResultList();
	}

	public Pessoa guardar(Pessoa pessoa) {
		Pessoa mergedPerson;
		if(!pessoa.getListaEnderecos().isEmpty()) {
			ArrayList<Endereco> enderecos = pessoa.getListaEnderecos();
			
			pessoa.setListaEnderecos(new ArrayList<Endereco>());
			mergedPerson = manager.merge(pessoa);
			
			for (Endereco endereco : enderecos) {
				endereco.setId_pessoa(mergedPerson);
				cadastroEndereco.salvar(endereco);
			}
		}else {
			mergedPerson = manager.merge(pessoa);
		}
		return mergedPerson;
	}

	public void remover(Pessoa pessoa) {
		pessoa = porId(pessoa.getId());
		manager.remove(pessoa);
	}

}
