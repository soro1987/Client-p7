package fr.soro.restclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeClient {

	
	@GetMapping("/accueil")
	public ModelAndView getAccueil(ModelAndView modelAndView) {
		modelAndView.setViewName("full-width");
		return modelAndView;
	}
	
}
