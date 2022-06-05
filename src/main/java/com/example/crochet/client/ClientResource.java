package com.example.crochet.client;

import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

}
