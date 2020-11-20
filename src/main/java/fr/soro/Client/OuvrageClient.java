package fr.soro.Client;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.soro.dto.OuvrageDto;
@Service
public class OuvrageClient {
	
	@Value("${app.serveur.url}")
	private String appUrl;

	private RestTemplate restTemplate;
	
	public OuvrageClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	public List<OuvrageDto> getOuvrageBytitredAuteur(String titre,String auteur){		
		ResponseEntity<OuvrageDto[]> response =restTemplate.getForEntity(appUrl+"/search/"+titre+"/"+auteur, OuvrageDto[].class);
		OuvrageDto[] ouvrage = response.getBody();
		List<OuvrageDto> ouvrageDto = Arrays.asList(ouvrage);
		return ouvrageDto;
		
	}
	
	public List<OuvrageDto> getOuvrage(){		
		ResponseEntity<OuvrageDto[]> response =restTemplate.getForEntity(appUrl+"/ouvrages", OuvrageDto[].class);
		OuvrageDto[] ouvrage = response.getBody();
		List<OuvrageDto> ouvrageDto = Arrays.asList(ouvrage);
		return ouvrageDto;
		
	}
	
	public OuvrageDto getOuvrageById(Long ouvrageId){		
		ResponseEntity<OuvrageDto> response =restTemplate.getForEntity(appUrl+"/ouvrages-id/"+ouvrageId, OuvrageDto.class);
		OuvrageDto ouvrage = response.getBody();	
		return ouvrage;
		
	}
	
	public OuvrageDto getOuvrageByTitre(String ouvrageTitre){		
		ResponseEntity<OuvrageDto> response =restTemplate.getForEntity(appUrl+"/ouvrages-titre/"+ouvrageTitre, OuvrageDto.class);
		OuvrageDto ouvrageByTitre = response.getBody();	
		return ouvrageByTitre;
		
	}
}
