package com.ttip.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Teste
 *
 */
@Entity

public class Teste implements Serializable {

	   
	@Id
	private int chave;
	private static final long serialVersionUID = 1L;

	public Teste() {
		super();
	}   
	public int getChave() {
		return this.chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}
   
}
