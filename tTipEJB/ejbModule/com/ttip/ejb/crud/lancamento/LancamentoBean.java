package com.ttip.ejb.crud.lancamento;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ttip.dao.LancamentoDAO;
import com.ttip.model.LancamentoVO;
import com.ttip.model.VOInterface;
import com.ttip.types.TipoLancamento;

/**
 * Session Bean implementation class LancamentoBean
 */
@Stateless
@Remote
public class LancamentoBean {
	
	@Inject
	private LancamentoDAO lancamentoDAO;
	
    public void insert(final LancamentoVO lancamento) {
    	
    	this.validate(lancamento);
    	
    	lancamento.setFgAtivo(true);

    	// FIXME: criar metodos e excecoes para validacao
    	lancamentoDAO.insert(lancamento);
    	
    }

    public void update(final LancamentoVO lancamento) {
    	
    	if (lancamento.get_id() <= 0) {
    		// FIXME: criar métodos para validação 
    		//throws ValidacaoException()
    	}
    	
    	
    	this.validate(lancamento);

    	// FIXME: criar metodos e excecoes para validacao
    	lancamentoDAO.update(lancamento);
    	
    }

    public void delete(final LancamentoVO lancamento) {
    	
    	if (lancamento.get_id() <= 0) {
    		// FIXME: criar métodos para validação 
    		//throws ValidacaoException()
    	}
    	
    	
    	// FIXME: criar metodos e excecoes para validacao
    	lancamentoDAO.delete(lancamento);
    	
    }

	private void validate(LancamentoVO lancamento) {
		
		if (!lancamento.getFgRealizado()) {
			lancamento.setDtRealizado(lancamento.getDtPrevisao());
		}
		
		if (!lancamento.getFgRealizado()) {
			lancamento.setDtRealizado(lancamento.getDtPrevisao());
		}
		
		// TODO: gerar excecao de validacao
		
	}

	public List<LancamentoVO> getAll() {
    	// FIXME: criar metodos e excecoes para validacao
		List<LancamentoVO> lancamento = lancamentoDAO.selectAll(true);
		this.calculate(lancamento);
		return lancamento;
	}

	public LancamentoVO getById(long id) {
    	// FIXME: criar metodos e excecoes para validacao
		return lancamentoDAO.select(id);
	}

	private void calculate(List<LancamentoVO> lancamento) {

		double vlSaldo = 0;
		double vlPrevisao = 0;
		int fator = 1;
		LancamentoVO item;
		
		for (int i = lancamento.size() - 1; i >= 0; i--) {
			
			item = lancamento.get(i);
			
			fator = item.getSgTipoLancamento().equals(TipoLancamento.CREDITO) ? 1 : -1;
			
			vlPrevisao += (item.getValor() * fator);
			
			if (item.getFgRealizado()) {
				vlSaldo += (item.getValor() * fator);
			}
			
			item.setVlPrevisao(vlPrevisao);
			item.setVlSaldo(vlSaldo);
			
		}		
		
	}

}
