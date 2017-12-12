package com.ttip.ejb.crud;

import javax.ejb.Remote;

import com.ttip.model.VOInterface;

@Remote
public interface CRUDBeanInterface {
	
	public void create(final VOInterface vo);	

}
