package com.rezeptor.modules.itemImage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.utils.ImageUtil;

@Service
public class ItemImageService {
  
	private ItemImageRepo itemImageRepo;

	public ItemImageService(ItemImageRepo itemImageRepo) {
		this.itemImageRepo = itemImageRepo;
	}

//	public ItemImage create(File file,ItemVariant itemVariant) throws IOException, MaxDataSizeException {
//		byte[] data = ImageUtil.encode(file);
//		ItemImage itemImage = new ItemImage();
//		itemImage.setFilename(file.getName());
//		itemImage.setType(file.getName().split(".")[1]);
//		itemImage.setData(data);
//		itemImage.setItemVariant(itemVariant);
//		return itemImageRepo.save(itemImage);
//	}
	
	// public ItemImage findByItemVariantId(long itemVariantId) {
	// 	return itemImageRepo.findByItemVariantId(itemVariantId).orElseThrow(
	// 			() -> new ResourceNotFoundException(
	// 					String.format("Image for item variant with id %s not found", itemVariantId)));
	// }
	
	public ItemImage findById(long id) {
		return itemImageRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Image with id %s not found", id)));
	}
	
	public ItemImage save(ItemImage itemImage) {
		return itemImageRepo.save(itemImage);
	}

	public ItemImage create(MultipartFile file) {
		String fileName = ImageUtil.getFileName(file);
		String fileExtension = ImageUtil.getFileExtension(fileName);
		
		ItemImage itemImage = new ItemImage(
				fileName,
				fileExtension,
				ImageUtil.encode(file));
		
		return itemImage;
	}
}
