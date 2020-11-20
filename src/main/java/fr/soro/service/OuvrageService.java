package fr.soro.service;

import org.springframework.stereotype.Service;

import fr.soro.Client.OuvrageClient;

@Service
public class OuvrageService {
	
	private OuvrageClient ouvrageClient;

	
	public OuvrageService(OuvrageClient ouvrageClient) {
		this.ouvrageClient=ouvrageClient;
	}
	

}
