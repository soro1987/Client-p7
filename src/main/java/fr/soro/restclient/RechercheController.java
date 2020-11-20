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
	public ModelAndView search(@RequestParam(value = "titre", defaultValue = "defaultTitre")String titre,@RequestParam(value = "auteur", defaultValue = "defaultAuteur") String auteur,ModelAndView modelAndView){				
		List<OuvrageDto> ouvragesRechercher = ouvrageClient.getOuvrageBytitredAuteur(titre, auteur);
		modelAndView.addObject("ouvragesRechercher", ouvragesRechercher);
		modelAndView.setViewName("resultat-recherche");
		return modelAndView;
	}
	
	@GetMapping("/recherche-titre")
	public ModelAndView getOuvrageCountBybibliotheque(@RequestParam(value = "titreOuvrage", defaultValue = "n") String titreOuvrage,ModelAndView modelAndView){	
		OuvrageDto ouvrageTrouver = ouvrageClient.getOuvrageByTitre(titreOuvrage);
		Map<Long, Object> ouvrageCountBybiblio = exemplaireClient.getOuvrageCountBybibliotheque(ouvrageTrouver.getId());
		modelAndView.addObject("ouvrageCountBybiblio", ouvrageCountBybiblio);
		modelAndView.setViewName("resultat-recherche");
		return modelAndView;
	
	}
	
	
}
