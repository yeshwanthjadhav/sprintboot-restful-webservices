package com.yash.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")
	public PersonV1 personv1(){
		return new PersonV1("Yash");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personv2(){
		return new PersonV2(new Name("Yash", "Jadhav"));
	}
	
}
