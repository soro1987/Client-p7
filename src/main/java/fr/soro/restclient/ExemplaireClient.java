package fr.soro.restclient;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.soro.dto.BiblioDto;
import fr.soro.dto.ExemplaireDto;
import fr.soro.dto.OuvrageDto;
import fr.soro.service.ExemplaireService;

@RestController
public class ExemplaireClient {
	Long id=(long) 1;
	private ExemplaireService exemplaireService;
	
	public ExemplaireClient(ExemplaireService exemplaireService) {
		this.exemplaireService=exemplaireService;
	}

	@GetMapping("/exemplaires-by-biblio")
	public ModelAndView getExBybiblio (ModelAndView modelAndView){		
		List<ExemplaireDto> exemplaires = exemplaireService.getExemplairesByBiblio(id);
		modelAndView.addObject("exemplaires", exemplaires);
		modelAndView.setViewName("exemplaire-biblio");
		return modelAndView;
	}
	
	@GetMapping("/exemplaires")
	public ModelAndView getExemplaire(ModelAndView modelAndView){		
		List<ExemplaireDto> exemplaires = exemplaireService.getExemplaires();
		modelAndView.addObject("exemplaires", exemplaires);
		modelAndView.setViewName("accueil");
		for (ExemplaireDto exemplaire : exemplaires) {
		    BiblioDto biblio = exemplaire.getBibliotheque();
		    	System.out.println("================================="+biblio.getNom());
			}

		return modelAndView;
		
	}

}
