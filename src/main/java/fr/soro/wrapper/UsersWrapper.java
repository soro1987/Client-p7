package fr.soro.wrapper;

import java.util.ArrayList;
import java.util.List;

import fr.soro.dto.UserDto;

public class UsersWrapper {
	private List<UserDto> users;
	
	public UsersWrapper() {
		users = new ArrayList<>();
    }

	
	
	
	public UsersWrapper(List<UserDto> users) {
		super();
		this.users = users;
	}




	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}	
	
	
}

 
    