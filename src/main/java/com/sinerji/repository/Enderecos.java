package com.sinerji.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sinerji.model.Endereco;
import com.sinerji.model.Pessoa;

public class Enderecos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Enderecos() {
		
	}
	
	public Enderecos(EntityManager manager) {
		this.manager = manager;
	}
	
	public Endereco porId(Long id) {
		return manager.find(Endereco.class, id);
	}
	
	public List<Endereco> pesquisar(String descricao){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
		
		Root<Endereco> root = criteriaQuery.from(Endereco.class);
		
		criteriaQuery.select(root);
		
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));
		
		TypedQuery<Endereco> query = manager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	
	public void salvar(Endereco endereco) {
		manager.merge(endereco);
	}
	
	public void remover(Endereco endereco) {
		endereco = porId(endereco.getId());
		manager.remove(endereco);
	}
	
}
