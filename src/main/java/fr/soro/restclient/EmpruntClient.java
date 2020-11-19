package fr.soro.restclient;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.soro.dto.EmpruntDto;
import fr.soro.dto.OuvrageDto;
import fr.soro.service.EmpruntService;
import fr.soro.service.OuvrageService;

@RestController
public class EmpruntClient {
	
	private EmpruntService empruntService;
	
	
	public EmpruntClient(EmpruntService empruntService) {
		this.empruntService = empruntService;
	}

	@GetMapping("/user-emprunts/{id}")
	public ModelAndView getUserEmprunts(@PathVariable(value = "id") Long id,ModelAndView modelAndView){		
		List<EmpruntDto> emprunts = empruntService.getUserEmprunts(id);		
		modelAndView.addObject("emprunts", emprunts);
		modelAndView.setViewName("user-emprunts");
		return modelAndView;
		
	}
}
