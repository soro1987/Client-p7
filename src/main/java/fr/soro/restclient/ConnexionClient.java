package fr.soro.restclient;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.soro.SecurityClient;
import fr.soro.dto.UserDto;




@RestController
public class ConnexionClient {
		
	@Value("${app.serveur.url}")
	private String appUrl;
	private RestTemplate restTemplate;	
	private SecurityClient securityClient;
		
	public ConnexionClient(SecurityClient securityClient,RestTemplate restTemplate) {
		this.securityClient=securityClient;
		this.restTemplate=restTemplate;
	}


	
	
	@GetMapping(value = "/register") // initialisation du login
	public ModelAndView register(ModelAndView modelAndView) {
		modelAndView.setViewName("register");
		UserDto userDto = new UserDto();
		modelAndView.addObject("userRegister", userDto);
		return modelAndView;
	}
	

	
	
	@GetMapping(value = "/login") // initialisation du login
	public ModelAndView loginView(ModelAndView modelAndView) {
		modelAndView.setViewName("loginForm");
		UserDto userDto = new UserDto();
		modelAndView.addObject("userForm", userDto);
		return modelAndView;
	}
	
	
	@PostMapping(value = "/login") 
	public ModelAndView loginPost(ModelAndView modelAndView,@ModelAttribute("userForm")UserDto userForm) throws JsonProcessingException {
	//	Creation de la requette
		HttpHeaders authenticationHeaders = getHeaders();
		String authenticationBody = getBody(userForm);
		HttpEntity<String> authenticationEntity = new HttpEntity<String>(authenticationBody,
				authenticationHeaders);
		//Envoie de la requette
		ResponseEntity<String> authenticationResponse = restTemplate.exchange(appUrl+"auth/signin",
				HttpMethod.POST, authenticationEntity, String.class);
		//Test si la requette est ok
		if (authenticationResponse.getStatusCode().equals(HttpStatus.OK))
		{
		//Stockage du token ds le cache avec la key username
		String username = userForm.getUsername();	
		String token = "Bearer " + authenticationResponse.getBody();
		securityClient.storeToken(token, username);	
		modelAndView.setViewName("accueil");
		return modelAndView;
		} else 
		{
			modelAndView.setViewName("error");
			return modelAndView;
		}
	}
	

	private String getBody(final UserDto userDto) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(userDto);
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
	
	
	@GetMapping("/users")
	public ModelAndView getusers (ModelAndView modelAndView){		
		ResponseEntity<UserDto[]> response =restTemplate.getForEntity(appUrl+"user", UserDto[].class);
		UserDto[] users = response.getBody();
		List<UserDto> usersDto = Arrays.asList(users);
		modelAndView.addObject("usersForm", usersDto);
		modelAndView.setViewName("listUsers");
		return modelAndView;
		
	}

	
//	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView login(@ModelAttribute("userLogin") User userLogin, HttpSession session) {
//		User userConfirmed = this.userRepository.findByMailAndPassword(userLogin.getMail(), userLogin.getPassword());
//		session.setAttribute("critere", "lieu");
//
//		if (userConfirmed != null) {// On verifie si userConfirm est different de nul pr savoir si l utilisateur
//									// existe
//			session.setAttribute("userSession", userConfirmed);// On crée une variable de session a l aide de l objet
//			ModelAndView model = new ModelAndView("redirect:/home"); // userConfirm qui sera dispo ds toute l appli
//			return model;
//		} else {
//			ModelAndView model = new ModelAndView();
//			model.addObject("errormsg", "Identifiant incorrect !");
//			model.setViewName("loginView");// Renvoie la page thymleaf
//			return model;
//		}
//
//	}
//	
	

}
