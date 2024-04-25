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
	
	@Inject
	private Enderecos enderecos;

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
	
	public ArrayList<Pessoa> findAll(){
		String sql = "from Pessoa "
                + "";
   
		TypedQuery<Pessoa> query = manager.createQuery(sql, Pessoa.class);
		
		return (ArrayList<Pessoa>) query.getResultList();
	}
	
	public List <Pessoa> findAllComEnderecos(){
		
		ArrayList<Pessoa> pessoasEncontradas = new ArrayList<Pessoa>();
		
		String sql = "from Pessoa "
                + "";
   
		TypedQuery<Pessoa> query = manager.createQuery(sql, Pessoa.class);
		
		pessoasEncontradas = (ArrayList<Pessoa>) query.getResultList();
		
		for (Pessoa pessoa : pessoasEncontradas) {
			String sql2 = "from Endereco ender where ender.id_pessoa = :idPessoa";
	   
			TypedQuery<Endereco> query2 = manager.createQuery(sql2, Endereco.class);
			
			query2.setParameter("idPessoa", pessoa);
			
			ArrayList<Endereco> enderecosEncontrados = (ArrayList<Endereco>) query2.getResultList();
			
			if(!enderecosEncontrados.isEmpty()) {
				pessoa.setListaEnderecos(enderecosEncontrados);
			}
		}
		
		return pessoasEncontradas;
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
		ArrayList<Endereco> enderecosADeletar = enderecos.findAllEnderecoPorIdPessoa(pessoa);
		
		if(!enderecosADeletar.isEmpty()) {
			for (Endereco endereco : enderecosADeletar) {
				cadastroEndereco.excluir(endereco);
			}
		}
		
		manager.remove(pessoa);
	}

}
