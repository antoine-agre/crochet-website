package com.example.crochet.commission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public Commission() {
	}

	public Commission(String titre, TypeComm type, Float prix, StatutComm statut) {
		this.titre = titre;
		this.type = type;
		this.prix = prix;
		this.statut = statut;
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
}
