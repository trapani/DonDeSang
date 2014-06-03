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
 * Medecin generated by hbm2java
 */
@Entity
@Table(name = "medecin", catalog = "dondesang")
public class Medecin implements java.io.Serializable {

	private int idMedecin;
	private String nom;
	private String prenom;
	private Set<Collecte> collectes = new HashSet<Collecte>(0);

	public Medecin() {
	}

	public Medecin(int idMedecin, String nom, String prenom) {
		this.idMedecin = idMedecin;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Medecin(int idMedecin, String nom, String prenom,
			Set<Collecte> collectes) {
		this.idMedecin = idMedecin;
		this.nom = nom;
		this.prenom = prenom;
		this.collectes = collectes;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idMedecin", unique = true, nullable = false)
	public int getIdMedecin() {
		return this.idMedecin;
	}

	public void setIdMedecin(int idMedecin) {
		this.idMedecin = idMedecin;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medecin")
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
