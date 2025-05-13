package com.rezeptor.modules.accountImage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.utils.ImageUtil;

@Service
public class AccountImageService {
  
	private AccountImageRepo accountImageRepo;

	public AccountImageService(AccountImageRepo accountImageRepo) {
		this.accountImageRepo = accountImageRepo;
	}
	
	public AccountImage findById(long id) {
		return accountImageRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Image with id %s not found", id)));
	}
	
	public AccountImage save(MultipartFile file) {
		String fileName = ImageUtil.getFileName(file);
		String type = ImageUtil.getFileExtension(fileName);
		byte[] data = ImageUtil.encode(file);
		AccountImage accountImage = new AccountImage(
										fileName,
										type,
										data);
		return accountImageRepo.save(accountImage);
 	}
	
}
