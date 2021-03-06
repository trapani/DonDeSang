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
 * Ville generated by hbm2java
 */
@Entity
@Table(name = "ville", catalog = "dondesang")
public class Ville implements java.io.Serializable {

	private int idVille;
	private String nomVille;
	private String cp;
	private Set<Adresse> adresses = new HashSet<Adresse>(0);

	public Ville() {
	}

	public Ville(int idVille, String nomVille, String cp) {
		this.idVille = idVille;
		this.nomVille = nomVille;
		this.cp = cp;
	}

	public Ville(int idVille, String nomVille, String cp, Set<Adresse> adresses) {
		this.idVille = idVille;
		this.nomVille = nomVille;
		this.cp = cp;
		this.adresses = adresses;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idVille", unique = true, nullable = false)
	public int getIdVille() {
		return this.idVille;
	}

	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}

	@Column(name = "NomVille", nullable = false, length = 100)
	public String getNomVille() {
		return this.nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	@Column(name = "CP", nullable = false, length = 10)
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ville")
	public Set<Adresse> getAdresses() {
		return this.adresses;
	}

	public void setAdresses(Set<Adresse> adresses) {
		this.adresses = adresses;
	}

}
