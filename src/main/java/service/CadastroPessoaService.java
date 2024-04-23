package service;

import java.io.Serializable;
import javax.inject.Inject;
import com.sinerji.model.Pessoa;
import com.sinerji.repository.Pessoas;

import util.Transacional;

public class CadastroPessoaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;
	
	@Transacional
	public void salvar (Pessoa pessoa) {
		pessoas.guardar(pessoa);
	}
	
	@Transacional
	public void excluir(Pessoa pessoa) {
		pessoas.remover(pessoa);
	}
}
