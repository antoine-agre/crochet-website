package com.example.crochet.client;

import com.example.crochet.commission.Commission;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("clients")
public class ClientResource {

	@Autowired
	private ClientRepository clientRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> getListeClients(){
		ArrayList<Client> out = new ArrayList<>();
		clientRepository.findAll().forEach(out :: add);
		return out;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Client createClient(Client c){return clientRepository.save(c);}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client getClient(@PathParam("id") Long id) {
		Optional<Client> opt = clientRepository.findById(id);
		return opt.orElse(null);
	}

	@DELETE
	@Path("{id}")
	public void deleteClient(@PathParam("id") Long id) {
		clientRepository.deleteById(id);
	}

	@PATCH
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client patchClient(@PathParam("id") Long id, Client c) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if(optionalClient.isPresent()){
			Client out = optionalClient.get();

			if(c.getNom() != null) {out.setNom(c.getNom());}
			if(c.getContacts() != null) {out.setContacts(c.getContacts());}

			//p.setAge(age);
			clientRepository.save(out);
			return out;
		}
		else {return null;}
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Client putClient(@PathParam("id") Long id, Client cli) {
		if(clientRepository.findById(id).isPresent()) {
			cli.setId(id);
			clientRepository.save(cli);
			return cli;
		}
		else {return null;}
	}

}
