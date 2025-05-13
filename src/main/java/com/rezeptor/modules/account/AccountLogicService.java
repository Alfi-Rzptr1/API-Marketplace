package com.rezeptor.modules.account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rezeptor.auth.registration.RegistrationRequest;
import com.rezeptor.auth.token.ConfirmationToken;
import com.rezeptor.auth.token.ConfirmationTokenService;
import com.rezeptor.exceptions.DuplicateResourceException;
import com.rezeptor.exceptions.RequestValidationException;
import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.accountData.AccountData;
import com.rezeptor.modules.accountData.AccountDataRepo;
import com.rezeptor.modules.accountImage.AccountImageService;
import com.rezeptor.modules.cart.Cart;
import com.rezeptor.modules.merchantData.MerchantDataService;
import com.rezeptor.modules.order.Order;
import com.rezeptor.utils.email.EmailValidator;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AccountLogicService implements UserDetailsService{

	private AccountRepo accountRepo;
	private MerchantDataService merchantDataService;
    private AccountDataRepo accountDataRepo;
	private AccountImageService accountImageService;
	private PasswordEncoder passwordEncoder;
	private EmailValidator emailValidator;
	private ConfirmationTokenService confirmationTokenService;
	//rest
	public AccountLogicService(AccountRepo accountRepo,MerchantDataService merchantDataService, AccountDataRepo accountDataRepo,
			AccountImageService accountImageService,PasswordEncoder passwordEncoder, EmailValidator emailValidator,
			ConfirmationTokenService confirmationTokenService) {
		this.accountRepo = accountRepo;
		this.merchantDataService = merchantDataService;
        this.accountDataRepo = accountDataRepo;
		this.accountImageService = accountImageService;
		this.passwordEncoder = passwordEncoder;
		this.emailValidator = emailValidator;
		this.confirmationTokenService = confirmationTokenService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepo.findByUsername(username).orElseThrow(() -> 
			new UsernameNotFoundException(String.format("Account with username %s not found", username))); 
	}
	
	public Account findById(long id) {
		if(accountRepo.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(String.format("Account with id %s not found", id));
		}
		return accountRepo.findById(id).get();
	}
	
	public Optional<Account> findByEmail(String email){
		return accountRepo.findByEmail(email);
	}
	
	public Optional<Account> findByUsername(String username){
		return accountRepo.findByUsername(username);
	}
  
	public int enableAccount(String email) {
		return accountRepo.enableAccount(email);
	}
	
	public String registerAccount(RegistrationRequest request) {
		Account account = new Account();
		String email = request.email();
		String username = request.username();
		 
		if(findByUsername(username).isPresent()) {
			throw new DuplicateResourceException("username already exist");
		}

		boolean validEmail = checkEmail(email);
		if(validEmail) {
			String encodedPassword = passwordEncoder.encode(account.getPassword());
			account.setPassword(encodedPassword);
			account.setRole(AccessRole.BASIC);
			accountRepo.save(account);
			
			String token = UUID.randomUUID().toString();
			
			ConfirmationToken confirmationToken = new ConfirmationToken();
			confirmationToken.setToken(token);
			confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(30));
			confirmationToken.setAccount(account);
			
			confirmationTokenService.createToken(confirmationToken);
			
			return token;
		}
		return null;
		
	}
	
	public void updateAccount(long accountId,AccountUpdateRequest updateRequest) {
		Account account = accountRepo.findById(accountId).get();
		AccountData accountData = findByAccountId(account.getId());
		
		boolean changes = false;
		
		if(updateRequest.firstName() != null && !updateRequest.firstName().equals(accountData.getFirstName())) {
			updateFirstName(updateRequest.firstName(), accountData.getId());
			changes = true;
		}
		if(updateRequest.lastName() != null && !updateRequest.lastName().equals(accountData.getLastName())) {
			updateLastName(updateRequest.lastName(), accountData.getId());
			changes = true;
		}
		
		if(!changes) {
			throw new RequestValidationException("no data changes found");
		}
	}
	
	public void deleteAccount(long accountId) {
		Account account = accountRepo.getReferenceById(accountId);
		accountRepo.deleteAccount(account.getId());
	}
	
	public boolean checkEmail(String email) {
		boolean validEmail = emailValidator.test(email);
		if(!validEmail) {
			throw new RequestValidationException("email is not valid");
		}
		if(findByEmail(email).isPresent()) {
			throw new DuplicateResourceException("email already exist");
		}
		return true;
	}

    public AccountData findByAccountId(long accountId) {
		Account account = findById(accountId);
		long accountDataId = account.getAccountData().getId();
		if(accountDataRepo.findById(accountDataId).isEmpty()) {
			throw new ResourceNotFoundException(String.format("Accout data with id %s not found", accountDataId));
		}
		return accountDataRepo.findById(accountDataId).get(); 
	}
	
	public AccountData findDataById(long id) {
		if(accountDataRepo.findById(id).isEmpty()) {
			throw new ResourceNotFoundException(String.format("Accout data with id %s not found", id));
		}
		
		return accountDataRepo.findById(id).get();
	}
	
	public AccountData save(AccountRequest request,Account account) {
		AccountData accountData = new AccountData(
				request.firstName(), 
				request.lastName(),
				getDate(request.birthDate()), 
				request.gender(), 
				request.addresses().isEmpty() ? null : request.addresses().stream().toList(), 
				null, 
				null, 
				account, 
				request.accountImage().isEmpty() ? null : accountImageService.save(request.accountImage()),
				new Cart(), 
				new Order());
		return accountDataRepo.save(accountData);
	}
	
	public void updateFirstName(String firstName,long id) {
		accountDataRepo.updateFirstName(firstName,id);
	}
	
	public void updateLastName(String lastName,long id) {
		accountDataRepo.updateLastName(lastName,id);
	}
	
	private LocalDate getDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}

}
