package com.rezeptor.utils.email;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String>{

	@Override
	public boolean test(String t) {
		//create email validation later
		if(!t.contains("@")) {
			return false; 
		}
		
		return true;
	}
	
}
