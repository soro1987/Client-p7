package fr.soro.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OuvrageDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9001709522446841330L;

	private Long id;
	
	private String titre;
	
	private String auteur;
	
	private Date dateParution;
	
	private String description;
	
	private String categorie;
	
	private  int nbreExemplaireDispo=0;
	
	private List<ExemplaireDto> exemplaires;

	public OuvrageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OuvrageDto(Long id, String titre, String auteur, Date dateParution, String description, String categorie,
			int nbreExemplaireDispo, List<ExemplaireDto> exemplaires) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.dateParution = dateParution;
		this.description = description;
		this.categorie = categorie;
		this.nbreExemplaireDispo = nbreExemplaireDispo;
		this.exemplaires = exemplaires;
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

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Date getDateParution() {
		return dateParution;
	}

	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public int getNbreExemplaireDispo() {
		return nbreExemplaireDispo;
	}

	public void setNbreExemplaireDispo(int nbreExemplaireDispo) {
		this.nbreExemplaireDispo = nbreExemplaireDispo;
	}

	public List<ExemplaireDto> getExemplaires() {
		return exemplaires;
	}

	public void setExemplaires(List<ExemplaireDto> exemplaires) {
		this.exemplaires = exemplaires;
	}

	@Override
	public String toString() {
		return "OuvrageDto [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", dateParution=" + dateParution
				+ ", description=" + description + ", categorie=" + categorie + ", nbreExemplaireDispo="
				+ nbreExemplaireDispo + ", exemplaires=" + exemplaires + "]";
	}
	
	
	
}
