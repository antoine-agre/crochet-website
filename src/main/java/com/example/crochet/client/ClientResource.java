package com.example.crochet.client;

import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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

}
