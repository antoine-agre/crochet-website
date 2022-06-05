package com.example.crochet.commission;

import com.example.crochet.client.Client;
import com.example.crochet.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("commissions")
public class CommissionResource {

	@Autowired
	private CommissionRepository commRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Commission> getListeComms(@QueryParam("idClient") Long idClient, @QueryParam("active") boolean active){

		ArrayList<Commission> out = new ArrayList<>();
		commRepository.findAll().forEach(out::add);

		if(idClient != null) {
			out.removeIf(c -> {return (c.getClient() == null || !c.getClient().getId().equals(idClient));});
		}

		if(active == true) {
			out.removeIf(c -> {return (!(c.getStatut() == Commission.StatutComm.PAYEE || c.getStatut() == Commission.StatutComm.ATTENTE_PAIEMENT));});
		}

		return out;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Commission createComm(Commission c){return commRepository.save(c);}

}
