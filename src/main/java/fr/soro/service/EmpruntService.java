package fr.soro.service;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.soro.dto.EmpruntDto;
import fr.soro.dto.OuvrageDto;
@Service
public class EmpruntService {
	
	@Value("${app.serveur.url}")
	private String appUrl;

	private RestTemplate restTemplate;
	
	public EmpruntService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	public List<EmpruntDto> getUserEmprunts(Long userId){		
		ResponseEntity<EmpruntDto[]> response =restTemplate.getForEntity(appUrl+"/emprunts-user/"+userId, EmpruntDto[].class);
		EmpruntDto[] emprunt = response.getBody();
		List<EmpruntDto> userEmprunts = Arrays.asList(emprunt);
		return userEmprunts;		
	}
	
	public List<EmpruntDto> getEmprunts(){		
		ResponseEntity<EmpruntDto[]> response =restTemplate.getForEntity(appUrl+"/emprunts", EmpruntDto[].class);
		EmpruntDto[] emprunt = response.getBody();
		List<EmpruntDto> empruntDto = Arrays.asList(emprunt);
		return empruntDto;		
	}
	
	public EmpruntDto getEmpruntDtoById(Long empruntId){		
		ResponseEntity<EmpruntDto> response =restTemplate.getForEntity(appUrl+"/emprunts/"+empruntId, EmpruntDto.class);
		EmpruntDto emprunt = response.getBody();	
		return emprunt;		
	}

}
