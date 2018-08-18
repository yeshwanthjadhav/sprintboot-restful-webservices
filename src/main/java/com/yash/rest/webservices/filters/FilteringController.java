package com.yash.rest.webservices.filters;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean(){
		return new SomeBean("value1","value2","value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean(){
		return Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value12","value22","value32"));
	}
	
	
	@GetMapping("/dynamicfiltering")
	public MappingJacksonValue retrieveDSomeBean(){
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/dynamicfiltering-list")
	public MappingJacksonValue retrieveDListOfSomeBean(){
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value12","value22","value32"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
	
}
