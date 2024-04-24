package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sinerji.model.Endereco;
import com.sinerji.model.Pessoa;

import service.CadastroPessoaService;
import util.FacesMessages;

@Named
@ViewScoped
public class CadastroPessoasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesMessages messages;

	@Inject
	private CadastroPessoaService cadastroPessoaService;


	// Atributo para guardar uma pessoa
	private Pessoa pessoa = new Pessoa();

	// Atributo para guardar a lista de pessoas cadastradas
	private ArrayList<Pessoa> pessoasLista = new ArrayList<Pessoa>();

	// Atributo para guardar um endereço
	private Endereco endereco = new Endereco();

	// Atributo para guardar a lista de endereços de uma pessoa
	private ArrayList<Endereco> enderecosLista = new ArrayList<Endereco>();

	public void novaPessoa() {
		pessoa = new Pessoa();
	}
	
	public void novoEndereco() {
		endereco = new Endereco();
	}
	
	public void novoListaEndereco() {
		enderecosLista = new ArrayList<Endereco>();
	}
	
	public void salvar() {
		if (pessoa.getNome().isBlank()) {
			return;
		}
		if (pessoa.getSexo() == '\0') {
			// O char está vazio
			return;
		}
		if (enderecosLista.size() < 0) {
			return;
		}

		if(!enderecosLista.isEmpty()) {
			pessoa.setListaEnderecos(enderecosLista);
		}
		
		cadastroPessoaService.salvar(pessoa);

		messages.info("Pessoa Cadastrada");
		
		novaPessoa();
		novoEndereco();
		novoListaEndereco();
	}

	public void adicionarEndereco() {

		Endereco novoEndereco = new Endereco();
		novoEndereco.setEstado(endereco.getEstado());
		novoEndereco.setCidade(endereco.getCidade());
		novoEndereco.setLogradouro(endereco.getLogradouro());
		novoEndereco.setNumero(endereco.getNumero());
		novoEndereco.setCep(endereco.getCep());

		// Adiciona o novo endereço à lista de endereços
		enderecosLista.add(novoEndereco);

		// Limpa o campo de entrada do endereço
		novoEndereco();
	}
	
	public void removerEndereco(Endereco enderecoRemover) {
		enderecosLista.remove(enderecoRemover);
	}

	public void paginaListaDeCadastrados() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect("ListaDePessoas.xhtml?faces-redirect=true");
	}

	// Getters e Setters

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoasLista;
	}

	public void setPessoas(ArrayList<Pessoa> pessoas) {
		this.pessoasLista = pessoas;
	}

	public List<Endereco> getEnderecos() {
		return enderecosLista;
	}

	public void setEnderecos(ArrayList<Endereco> enderecos) {
		this.enderecosLista = enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
