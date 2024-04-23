package service;

import java.io.Serializable;

import javax.inject.Inject;

import com.sinerji.model.Endereco;
import com.sinerji.model.Pessoa;
import com.sinerji.repository.Enderecos;
import com.sinerji.repository.Pessoas;

import util.Transacional;

public class CadastroEnderecoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Enderecos enderecos;
	
	@Transacional
	public void salvar (Endereco endereco) {
		enderecos.salvar(endereco);
	}
	
	@Transacional
	public void excluir(Endereco endereco) {
		enderecos.remover(endereco);
	}
}
