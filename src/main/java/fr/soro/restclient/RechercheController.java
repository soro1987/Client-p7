package fr.soro.restclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



import fr.soro.Client.ExemplaireClient;
import fr.soro.Client.OuvrageClient;
import fr.soro.dto.OuvrageDto;

@RestController
public class RechercheController {

	
	private ExemplaireClient exemplaireClient;
	private OuvrageClient ouvrageClient;
	
	public RechercheController(ExemplaireClient exemplaireService,OuvrageClient ouvrageClient) {
		this.exemplaireClient=exemplaireService;
		this.ouvrageClient=ouvrageClient;
	}
	
	
	@GetMapping("/recherche")
	public ModelAndView findOuvrage(ModelAndView modelAndView){			
		modelAndView.setViewName("recherche");
		return modelAndView;
	
	}
	
	@RequestMapping(value="/search", method = {RequestMethod.GET})
	public ModelAndView search(@RequestParam(value = "motcle", defaultValue = "defaultmotcle")String motcle,ModelAndView modelAndView){				
		List<OuvrageDto> ouvragesRechercher = ouvrageClient.getOuvrageBytitredAuteur(motcle);
		modelAndView.addObject("ouvragesRechercher", ouvragesRechercher);
		modelAndView.setViewName("resultat-recherche");
		return modelAndView;
	}
	
	@GetMapping("/dispo/{ouvrageId}")
	public ModelAndView getOuvrageCountBybibliotheque(@PathVariable(value = "ouvrageId") Long ouvrageId,ModelAndView modelAndView){			
		Map<String, Object> ouvrageCountBybiblio = ouvrageClient.getOuvrageCountBybibliotheque(ouvrageId);
		modelAndView.addObject("ouvrageCountBybiblio", ouvrageCountBybiblio);
		modelAndView.setViewName("disponibilite-ouvrage");
		return modelAndView;
	
	}
	
	
}
