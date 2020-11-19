package fr.soro.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.soro.dto.EmpruntDto;
import fr.soro.dto.ExemplaireDto;
@Service
public class ExemplaireService {
	
	@Value("${app.serveur.url}")
	private String appUrl;
	private ExemplaireService exemplaireService;
	private RestTemplate restTemplate;
	
	public ExemplaireService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<ExemplaireDto> getExemplaires(){		
		ResponseEntity<ExemplaireDto[]> response =restTemplate.getForEntity(appUrl+"/exemplaires", ExemplaireDto[].class);
		ExemplaireDto[] exemplaire = response.getBody();
		List<ExemplaireDto> exemplaireDto = Arrays.asList(exemplaire);
		return exemplaireDto;
		
	}
	
	public List<ExemplaireDto> getExemplairesByBiblio(Long biblioId){		
		ResponseEntity<ExemplaireDto[]> response =restTemplate.getForEntity(appUrl+"/exemplaires-biblio/"+biblioId, ExemplaireDto[].class);
		ExemplaireDto[] exemplaires = response.getBody();
		List<ExemplaireDto> exemplairesByBiblio = Arrays.asList(exemplaires);
		return exemplairesByBiblio;
		
	}
	
	
//	public List<ExemplaireDto> getExemplairesByBiblio(Long biblioId){		
//		List<ExemplaireDto> allExemplaires = exemplaireService.getExemplaires();
//		List<ExemplaireDto> exemplaireByBiblio = new ArrayList<ExemplaireDto>();
//		for (ExemplaireDto exemplaire : allExemplaires) {
//		    if (exemplaire.getBibliotheque().getId()==biblioId) {
//		    	exemplaireByBiblio.add(exemplaire);
//		    	System.out.println(exemplaire.getId());
//			}
//		}
//		return exemplaireByBiblio;
		
//	}
	
	
	public ExemplaireDto getExemplaireById(Long exemplaireId){		
		ResponseEntity<ExemplaireDto> response =restTemplate.getForEntity(appUrl+"/exemplaires/"+exemplaireId, ExemplaireDto.class);
		ExemplaireDto exemplaire = response.getBody();	
		return exemplaire;
		
	}

}
