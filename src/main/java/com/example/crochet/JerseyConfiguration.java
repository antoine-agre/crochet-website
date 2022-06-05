package com.example.crochet;

import com.example.crochet.client.ClientResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("rest")
@Configuration
public class JerseyConfiguration extends ResourceConfig {
	public JerseyConfiguration() {
		register(ClientResource.class);
	}
}
