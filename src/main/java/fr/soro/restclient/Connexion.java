package fr.soro.restclient;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.soro.SecurityClient;
import fr.soro.dto.UserDto;
import fr.soro.modele.ResponseToken;
import fr.soro.modele.User;
import fr.soro.wrapper.UsersWrapper;



@RestController
public class Connexion {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	SecurityClient securityClient;
	
	private static final String AUTHENTICATION_URL = "http://localhost:8080/auth/signin";
	private static final String USERS_URL = "http://localhost:8080/user";
	
	
	@GetMapping("/users")
	public ModelAndView getusers (ModelAndView modelAndView){		
		ResponseEntity<UserDto[]> response =restTemplate.getForEntity(USERS_URL, UserDto[].class);
		UserDto[] users = response.getBody();
		List<UserDto> usersDto = Arrays.asList(users);
		modelAndView.addObject("usersForm", usersDto);
		modelAndView.setViewName("listUsers");
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
		ResponseEntity<String> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
				HttpMethod.POST, authenticationEntity, String.class);
		//Test si la requette est ok
		if (authenticationResponse.getStatusCode().equals(HttpStatus.OK))
		{
		//Stockage du token ds le cache avec la key username
		String username = userForm.getUsername();	
		String token = "Bearer " + authenticationResponse.getBody();
		securityClient.storeToken(token, username);	
		modelAndView.setViewName("susses");
		return modelAndView;
		} else 
		{
			modelAndView.setViewName("error");
			return modelAndView;
		}
	}
	

//		if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
//			String token = "Bearer " + authenticationResponse;
//			HttpHeaders headers = getHeaders();
//			headers.set("Authorization", token);
//			HttpEntity<String> jwtEntity = new HttpEntity<String>(headers);
//			// Use Token to get Response
//			ResponseEntity<List<UserDto>> usersResponse = restTemplate.exchange(USERS_URL, HttpMethod.GET, jwtEntity,
//					new ParameterizedTypeReference<List<UserDto>>() {});
//			if (usersResponse.getStatusCode().equals(HttpStatus.OK)) {
//				List<UserDto> users = usersResponse.getBody();
//				modelAndView.addObject("usersHtml", users);
//				modelAndView.setViewName("succes");
//			}
//		return modelAndView;
//	}
//		modelAndView.setViewName("error");
//		return modelAndView;
//	}
//	
//	
	private String getBody(final UserDto userDto) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(userDto);
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	
//	@PostMapping(value = "/login")
//	public ModelAndView login(@Validated @ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult,
//			ModelAndView modelAndView) throws JsonMappingException, JsonProcessingException {
//
//		modelAndView.setViewName("loginForm");
//		if (bindingResult.hasErrors()) {
//			modelAndView.addObject("saveError", "Erreur de saisie");
//			return modelAndView;
//		}
//		try {
//			ResponseEntity<User> userExists = restTemplate
//					.postForEntity(configurationService.getUrl() + "user/users/login", new User(userForm), User.class);
//
//			modelAndView.addObject("currentUser", new UserDto(userExists.getBody()));
//			modelAndView.setViewName("loginSuccess");
//			return modelAndView;
//
//		} catch (HttpClientErrorException | HttpServerErrorException ex) {
//			ObjectMapper objectMapper = new ObjectMapper();
//			RestClientExceptionDTO restClientExceptionDTO = objectMapper.readValue(ex.getResponseBodyAsString(),
//					new TypeReference<RestClientExceptionDTO>() {
//					});
//			modelAndView.setViewName("loginForm");
//			modelAndView.addObject("saveError", restClientExceptionDTO.getErrorMessage());
//			modelAndView.addObject("createAccount", " Créez votre compte !");
//			return modelAndView;
//		} catch (Exception ex) {
//			modelAndView.setViewName("loginForm");
//			modelAndView.addObject("saveError", ex.getMessage());
//			modelAndView.addObject("createAccount", " Créez votre compte !");
//			return modelAndView;
//		}
//
//	}
}
