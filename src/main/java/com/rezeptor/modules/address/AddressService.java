package com.rezeptor.modules.address;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.account.AccountLogicService;

@Service
public class AddressService {
  
	private AddressRepo addressRepo;
	private AccountLogicService accountService;
	
	public AddressService(AddressRepo addressRepo, AccountLogicService accountService) {
		this.addressRepo = addressRepo;
		this.accountService = accountService;
	}
	
	public Address findById(long id) {
		return addressRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Address with id %s not found", id)));
	}
	
	public List<Address> findAllByAccountId(long accountDataId){
		accountService.findById(accountDataId);
		List<Address> addresses = addressRepo.findAllByAccountDataId(accountDataId);
		if(!addresses.isEmpty()) {
			return addresses;
		}
		throw new ResourceNotFoundException(String.format("Account data with id %s doesn't have any addresses", accountDataId));
	}
	
	public Address save(AddressRequest request) {
		Address address = new Address(request.roadName(),
									  request.village(), 
									  request.subDistrict(), 
									  request.regency(), 
									  request.province(), 
									  request.detail(), 
									  accountService.findDataById(request.accountDataId()));
		return addressRepo.save(address);
	}
	
}
