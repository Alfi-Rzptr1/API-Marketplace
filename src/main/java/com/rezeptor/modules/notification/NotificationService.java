package com.rezeptor.modules.notification;

import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.account.AccountLogicService;

@Service
public class NotificationService {
  
	private NotificationRepo notificationRepo;
	private AccountLogicService accountService;
	
	public NotificationService(NotificationRepo notificationRepo, AccountLogicService accountService) {
		this.notificationRepo = notificationRepo;
		this.accountService = accountService;
	}

	public Notification findByAccountDataId(long accountDataId) {
		return notificationRepo.findByAccountDataId(accountDataId).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Notification for account data id %s not found",accountDataId)));
	}
	
	public Notification findById(long id) {
		return notificationRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Notification with id %s not found",id)));
	}
	
	public Notification save(NotificationRequest request) {
		Notification notification = new Notification(
				request.title(),
				request.message(),
				accountService.findDataById(request.accountDataId()));
		
		return notificationRepo.save(notification);
	}
	
}
