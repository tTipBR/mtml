package com.ttip.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ttip.model.LancamentoVO;
import com.ttip.model.VOInterface;

@Dependent
public class LancamentoDAO implements AbstractDAO {
	
	@PersistenceContext (unitName=DEFAULT_DS)
    private EntityManager em;
    
    @Inject
    public LancamentoDAO() {
    }
    
    public void insert(final VOInterface lancamento) {
    	em.persist(lancamento);
	}

    public void update(final VOInterface lancamento) {
    	LancamentoVO vo = (LancamentoVO) em.merge(lancamento);
    	em.persist(vo);
	}

    public void delete(final VOInterface lancamento) {
    	LancamentoVO vo = (LancamentoVO) em.merge(lancamento);
    	vo.setFgAtivo(false);
    	em.persist(vo);
	}

	public List<LancamentoVO> selectAll(boolean fgAtivo) {
		
		TypedQuery<LancamentoVO> qry;
		
		if (fgAtivo) {
			qry  = em.createQuery("select l from LancamentoVO l where l.fgAtivo = 't' order by l.dtRealizado desc", LancamentoVO.class);
		}
		
		else {
			qry  = em.createQuery("select l from LancamentoVO l order by l.dtRealizado desc", LancamentoVO.class);
		}
		
		List<LancamentoVO> result = qry.getResultList();
		return result;
	}

	public LancamentoVO select(long id) {
		LancamentoVO result = em.find(LancamentoVO.class, id); 
		return result;
	}

}
