package com.example.crochet.commission;

import com.example.crochet.client.Client;

import javax.persistence.*;

@Entity
public class Commission {

	enum TypeComm {PETITE, MOYENNE, GRANDE, QUAD, AUTRE}
	enum StatutComm {DEMANDE, DISCUSSION, ATTENTE_PAIEMENT, PAYEE, FINIE}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private TypeComm type;
	private Float prix; //unité : euro
	private StatutComm statut;
	@ManyToOne(cascade = CascadeType.MERGE)
	//@JoinColumn(name = "client_id")
	private Client client;

	public Commission() {
	}

	public Commission(String titre, TypeComm type, Float prix, StatutComm statut, Client client) {
		this.titre = titre;
		this.type = type;
		this.prix = prix;
		this.statut = statut;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public TypeComm getType() {
		return type;
	}

	public void setType(TypeComm type) {
		this.type = type;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public StatutComm getStatut() {
		return statut;
	}

	public void setStatut(StatutComm statut) {
		this.statut = statut;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
