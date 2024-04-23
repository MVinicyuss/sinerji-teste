package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.sinerji.model.Endereco;
import com.sinerji.model.Pessoa;
import com.sinerji.repository.Enderecos;
import com.sinerji.repository.Pessoas;

import service.CadastroEnderecoService;
import service.CadastroPessoaService;
import util.FacesMessages;

@Named
@ViewScoped
public class CadastroPessoasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoas pessoas;

	@Inject
	private Enderecos enderecos;

	@Inject
	private FacesMessages messages;

	@Inject
	private CadastroPessoaService cadastroPessoaService;
	
	@Inject
	private CadastroEnderecoService cadastroEnderecoService;

	// Atributo para guardar uma pessoa
	private Pessoa pessoa = new Pessoa();

	// Atributo para guardar a lista de pessoas cadastradas
	private List<Pessoa> pessoasLista = new ArrayList<Pessoa>();

	// Atributo para guardar um endereço
	private Endereco endereco = new Endereco();

	// Atributo para guardar a lista de endereços de uma pessoa
	private List<Endereco> enderecosLista = new ArrayList<Endereco>();

	public String salvar() {
		if (pessoa.getNome().isBlank()) {
			return null;
		}
		if (pessoa.getIdade() == 0) {
			return null;
		}
		if (pessoa.getSexo() == '\0') {
			// O char está vazio
			return null;
		}
		if (enderecosLista.size() < 0) {
			return null;
		}
		
		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setNome(pessoa.getNome());
		novaPessoa.setIdade(pessoa.getIdade());
		novaPessoa.setSexo(pessoa.getSexo());
		cadastroPessoaService.salvar(novaPessoa);
		

		return "ListaDePessoas.xhtml";

	}

	public void adicionarEndereco() {

		if (endereco.getEstado().isEmpty() || endereco.getCidade().isEmpty() || endereco.getLogradouro().isEmpty()
				|| endereco.getNumero() == 0 || endereco.getCep().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Preencha todos os campos do endereço."));
			return;
		}

		Endereco novoEndereco = new Endereco();
		novoEndereco.setEstado(endereco.getEstado());
		novoEndereco.setCidade(endereco.getCidade());
		novoEndereco.setLogradouro(endereco.getLogradouro());
		novoEndereco.setNumero(endereco.getNumero());
		novoEndereco.setCep(endereco.getCep());

		// Adiciona o novo endereço à lista de endereços
		enderecosLista.add(novoEndereco);

		// Limpa o campo de entrada do endereço
		endereco = new Endereco();
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

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoasLista = pessoas;
	}

	public List<Endereco> getEnderecos() {
		return enderecosLista;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecosLista = enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
