package fr.soro;



import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import fr.soro.SecurityClient;
import fr.soro.utils.UserContext;



@Component
public class CustomInterceptor implements ClientHttpRequestInterceptor {
	
	private UserContext userContext;	
	private SecurityClient securityClient;
	
	public CustomInterceptor(SecurityClient securityClient,UserContext userContext) {
		this.securityClient =securityClient;
		this.userContext = userContext;
	}
	
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		    String token = this.securityClient.getToken(this.userContext.getUsername());
		    request.getHeaders().add("Authorization", token);		    
		return execution.execute(request, body);
	}

}
