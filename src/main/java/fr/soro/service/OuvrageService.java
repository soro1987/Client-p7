package fr.soro.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import fr.soro.dto.OuvrageDto;
import fr.soro.dto.UserDto;
@Service
public class OuvrageService {
	
	@Value("${app.serveur.url}")
	private String appUrl;
	@Autowired
	private RestTemplate restTemplate;
	
//	public OuvrageService(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
//	}

	
	public List<OuvrageDto> getOuvrage(){		
		ResponseEntity<OuvrageDto[]> response =restTemplate.getForEntity(appUrl+"/ouvrages", OuvrageDto[].class);
		OuvrageDto[] ouvrage = response.getBody();
		List<OuvrageDto> ouvrageDto = Arrays.asList(ouvrage);
		return ouvrageDto;
		
	}
	
	public OuvrageDto getOuvrageById(Long ouvrageId){		
		ResponseEntity<OuvrageDto> response =restTemplate.getForEntity(appUrl+"/ouvrages/1", OuvrageDto.class);
		OuvrageDto ouvrage = response.getBody();	
		return ouvrage;
		
	}
}
