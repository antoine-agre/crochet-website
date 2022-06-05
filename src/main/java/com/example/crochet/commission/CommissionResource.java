package com.example.crochet.commission;

import com.example.crochet.client.Client;
import com.example.crochet.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Commission getComm(@PathParam("id") Long id) {
		Optional<Commission> opt = commRepository.findById(id);
		return opt.orElse(null);
	}

	@DELETE
	@Path("{id}")
	public void deletePersonne(@PathParam("id") Long id) {
		commRepository.deleteById(id);
	}

	@PATCH
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Commission patchComm(@PathParam("id") Long id, Commission c) {
		Optional<Commission> optionalCommission = commRepository.findById(id);
		if(optionalCommission.isPresent()){
			Commission out = optionalCommission.get();

			if(c.getTitre() != null) {out.setTitre(c.getTitre());}
			if(c.getType() != null) {out.setType(c.getType());}
			if(c.getPrix() != null) {out.setPrix(c.getPrix());}
			if(c.getStatut() != null) {out.setStatut(c.getStatut());}
			if(c.getClient() != null) {out.setClient(c.getClient());}

			//p.setAge(age);
			commRepository.save(out);
			return out;
		}
		else {return null;}
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Commission putComm(@PathParam("id") Long id, Commission comm) {
		if(commRepository.findById(id).isPresent()) {
			comm.setId(id);
			commRepository.save(comm);
			return comm;
		}
		else {return null;}
	}

}
