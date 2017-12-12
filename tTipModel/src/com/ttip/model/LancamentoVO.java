package com.ttip.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ttip.types.TipoLancamento;

/**
 * Entity implementation class for Entity: LancamentoVO
 *
 */
@Entity
@Table(name="FN_LANCAMENTO")
@SequenceGenerator(name="SEQ_LANCAMENTO")
public class LancamentoVO implements VOInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_LANCAMENTO")
	@Id
	private long _id;
	private String descricao;
	private double valor;

	@Column(columnDefinition="CHAR")
	private TipoLancamento sgTipoLancamento;

	@Temporal(TemporalType.DATE)
	private Date dtPrevisao;
	@Temporal(TemporalType.DATE)
	private Date dtRealizado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtInclusao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAlteracao;

	private Boolean fgRealizado;
	
	
	@Transient
	private Double vlPrevisao;
	
	@Transient
	private Double vlSaldo;
	
	private Boolean fgAtivo;
	
	public LancamentoVO() {
		super();
	}   
	public long get_id() {
		return this._id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
   
	public Date getDtPrevisao() {
		return dtPrevisao;
	}

	public void setDtPrevisao(Date dtPrevisao) {
		this.dtPrevisao = dtPrevisao;
	}

	public Date getDtRealizado() {
		return dtRealizado;	}

	public void setDtRealizado(Date dtRealizado) {
		this.dtRealizado = dtRealizado;
	}

	public Date getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	
	public TipoLancamento getSgTipoLancamento() {
		return sgTipoLancamento;
	}

	public void setSgTipoLancamento(TipoLancamento sgTipoLancamento) {
		this.sgTipoLancamento = sgTipoLancamento;
	}
	
	public boolean getFgRealizado() {
		return fgRealizado;
	}

	public void setFgRealizado(boolean fgRealizado) {
		this.fgRealizado = fgRealizado;
	}
	
	public void setVlSaldo(Double vlSaldo) {
		this.vlSaldo = vlSaldo;
	}

	public Double getVlSaldo() {
		return this.vlSaldo;
	}	
	
	public void setVlPrevisao(Double vlPrevisao) {
		this.vlPrevisao = vlPrevisao;
	}
	
	public Double getVlPrevisao() {
		return this.vlPrevisao;
	}	
	
	public boolean getFgAtivo() {
		return fgAtivo;
	}

	public void setFgAtivo(boolean fgAtivo) {
		this.fgAtivo = fgAtivo;
	}
	
	@PrePersist
	private void beforeInsert() {
		setDtInclusao(new Date());
		setDtAlteracao(new Date());
	}

	@PreUpdate
	private void beforeUpdate() {
		setDtAlteracao(new Date());
	}

}
