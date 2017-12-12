package com.ttip.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.GsonBuilder;
import com.ttip.ejb.crud.lancamento.LancamentoBean;
import com.ttip.model.LancamentoVO;

@Path("/lancamentos")
public class LancamentoAPI {
	
	@EJB
	private LancamentoBean lancamentoBean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLancamento() {
		
		GsonBuilder gsonB = new GsonBuilder();
		
		gsonB.setDateFormat("YYYY-MM-DDTHH:MM:SSZ");
		List<LancamentoVO> lancamento = lancamentoBean.getAll();
		
		Response response = Response.ok(lancamento).build();
		response.getHeaders().add("Access-Control-Allow-Orign", "*");
		
		return response;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getById(@PathParam("id") long id) {
		
		GsonBuilder gsonB = new GsonBuilder();
		
		gsonB.setDateFormat("YYYY-MM-DDTHH:MM:SSZ");
		LancamentoVO lancamento = lancamentoBean.getById(id);
		
		Response response = Response.ok(lancamento).build();
		response.getHeaders().add("Access-Control-Allow-Orign", "*");
		
		return response;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postLancamento(LancamentoVO lancamento) {
		
		if (lancamento.get_id() <= 0) {
			lancamentoBean.insert(lancamento);
		}
		
		else {
			lancamentoBean.update(lancamento);
		}
		
		// FIXME: ver sobre tratamento da URI
		return  Response.status(200).entity(lancamento).build();
		
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		
		GsonBuilder gsonB = new GsonBuilder();
		
		gsonB.setDateFormat("YYYY-MM-DDTHH:MM:SSZ");
		LancamentoVO lancamento = lancamentoBean.getById(id);
		
		lancamentoBean.delete(lancamento);
		
		Response response = Response.ok(lancamento).build();
		response.getHeaders().add("Access-Control-Allow-Orign", "*");
		
		return response;
	}
	
}


/*****
 * @Path("{c}")
 * @GET
 * @Produces("application/xml")
 * public String convertCtoFfromInput(@PathParam("c") Double c) {
 * 	Double fahrenheit;
 * 	Double celsius = c;
 * 	fahrenheit = ((celsius * 9) / 5) + 32;
 * 
 * 	String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
 * 	return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
 * }
 *****/ 