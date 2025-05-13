package com.rezeptor.modules.merchantData;

import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.account.Account;
import com.rezeptor.modules.account.AccountRequest;

@Service
public class MerchantDataService {
  
	private MerchantDataRepo merchantDataRepo;
	//private AccountService accountService;
	
	public MerchantDataService(MerchantDataRepo merchantDataRepo) {
		this.merchantDataRepo = merchantDataRepo;
		//this.accountService = accountService;
	}
	
	public MerchantData findByAccountId(long accountId) {
//		Account account = accountService.findById(accountId);
//		long merchantDataId = account.getMerchantData().getId();
//		if(merchantDataRepo.findById(merchantDataId).isEmpty()) {
//			throw new ResourceNotFoundException(String.format("Merchant data with id %s not found", merchantDataId));
//		}
//		return merchantDataRepo.findById(merchantDataId).get();
		return merchantDataRepo.findByAccountId(accountId).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Merchant data for account with id %s not found", accountId)));
	}
	
	public MerchantData findById(long id) {
		return merchantDataRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Merchant data with id %s not found", id)));
	}
	
	public MerchantData save(AccountRequest request, Account account) {
		MerchantData merchantData = new MerchantData(
				request.shopName(), 
				request.domain(), 
				request.description(), 
				account);
		
		return merchantDataRepo.save(merchantData);	
	}
	
}
