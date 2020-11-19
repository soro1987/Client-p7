package fr.soro.restclient;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import fr.soro.dto.OuvrageDto;
import fr.soro.service.OuvrageService;

@RestController
public class OuvrageClient {

	private OuvrageService ouvrageService;
	
	
	public OuvrageClient(OuvrageService ouvrageService) {
		this.ouvrageService = ouvrageService;
	}

	@GetMapping("/ouvrages")
	public ModelAndView ouvrages (ModelAndView modelAndView){		
		List<OuvrageDto> ouvrages = ouvrageService.getOuvrage();		
		modelAndView.addObject("ouvrages", ouvrages);
		modelAndView.setViewName("tous");
		return modelAndView;	
	}
	
	@GetMapping("/ouvrages/{id}")
	public ModelAndView ouvrage (@PathVariable(value = "id") Long id,ModelAndView modelAndView){		
		OuvrageDto ouvrage = ouvrageService.getOuvrageById(id);		
		modelAndView.addObject("ouvrage", ouvrage);
		modelAndView.setViewName("ouvrage-id");
		return modelAndView;
		
	}
}
