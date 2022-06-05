package com.example.crochet.commission;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("commissions")
public class CommissionResource {

	@Autowired
	private CommissionRepository commRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Commission> getListeComms(){
		ArrayList<Commission> out = new ArrayList<>();
		commRepository.findAll().forEach(out :: add);
		return out;
	}

}
