package pojo;

// Generated 30 mai 2014 10:49:31 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Infirmiere generated by hbm2java
 */
@Entity
@Table(name = "infirmiere", catalog = "dondesang")
public class Infirmiere implements java.io.Serializable {

	private int idInfirmiere;
	private String nom;
	private String prenom;
	private Set<Collecte> collectes = new HashSet<Collecte>(0);

	public Infirmiere() {
	}

	public Infirmiere(int idInfirmiere, String nom, String prenom) {
		this.idInfirmiere = idInfirmiere;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Infirmiere(int idInfirmiere, String nom, String prenom,
			Set<Collecte> collectes) {
		this.idInfirmiere = idInfirmiere;
		this.nom = nom;
		this.prenom = prenom;
		this.collectes = collectes;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idInfirmiere", unique = true, nullable = false)
	public int getIdInfirmiere() {
		return this.idInfirmiere;
	}

	public void setIdInfirmiere(int idInfirmiere) {
		this.idInfirmiere = idInfirmiere;
	}

	@Column(name = "Nom", nullable = false, length = 100)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "Prenom", nullable = false, length = 50)
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "infirmiere")
	public Set<Collecte> getCollectes() {
		return this.collectes;
	}

	public void setCollectes(Set<Collecte> collectes) {
		this.collectes = collectes;
	}
	
	public String toString(){
		return this.nom + " "  +this.prenom;
	}

}
