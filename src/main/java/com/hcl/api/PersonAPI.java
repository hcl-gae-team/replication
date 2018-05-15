package com.hcl.api;

import java.util.*;

import com.hcl.model.*;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

@Api(
	name = "personAPI",
	version = "v1",
	namespace = @ApiNamespace(
		ownerDomain = "hcl.com",
		ownerName = "hcl.com",
		packagePath = ""
	)
)
public class PersonAPI {
	
	List<Person> list = new ArrayList<Person>();
	Person person = new Person();
	
	public PersonAPI() {
		person.setId(1);
		person.setFirstName("Juan");
		person.setLastName("Dela Cruz");
		person.setAge(20);
		
		list.add(person);
	}
	
	@ApiMethod(
		name = "person.list",
		path = "person/list",
		httpMethod = ApiMethod.HttpMethod.GET
	)
	public List<Person> getPersons() {
		return list;
	}
	
	@ApiMethod(
		name = "person.firstname.get",
		path = "person/firstname/get",
		httpMethod = ApiMethod.HttpMethod.GET
	)
	public Person getPersonByFirstName(@Named("firstname") String firstname) {
		Person person = new Person();
		person.setId(0);
		person.setFirstName("No firstname found.");
		person.setLastName("No lastname found.");
		person.setAge(0);
		
		for (Person p : list) {
			if (p.getFirstName().equals(firstname)) {
				person = p;
			}
		}
		return person;
	}
}
