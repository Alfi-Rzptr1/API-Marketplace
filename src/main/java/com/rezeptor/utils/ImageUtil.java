package com.rezeptor.utils;

import java.util.Base64;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rezeptor.exceptions.Base64EncodingException;
import com.rezeptor.exceptions.MaxDataSizeException;
import com.rezeptor.exceptions.RequestValidationException;

import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class ImageUtil {

	public static byte[] encode(MultipartFile multipartFile){
		final long maxSize = 5 * 1024 * 1024;
		String fileName = getFileName(multipartFile);
		String extension = getFileExtension(fileName);
		try {
		    if (multipartFile.getSize() > maxSize) {
		        throw new MaxDataSizeException(
		            String.format(
		                "File size exceeds the maximum limit of %d bytes. Current size: %d bytes",
		                maxSize,
		                multipartFile.getSize()
		            )
		        );
		    }

		    DataSource dataSource = new ByteArrayDataSource(multipartFile.getInputStream(),getMimeType(extension));
		    byte[] fileBytes = dataSource.getInputStream().readAllBytes();
		    return Base64.getEncoder().encode(fileBytes);
		} catch (MaxDataSizeException e) {
		    throw new Base64EncodingException("File size exceeds the maximum limit. " + e.getMessage(), e);
		} catch (Exception e) {
		    throw new Base64EncodingException("Cannot process requested file. Cause: " + e.getMessage(), e);
		}
	}
	
	public static String getFileName(MultipartFile file) {
		return file.getOriginalFilename();
	}
	
	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
	}
	
	private static String getMimeType(String fileExtension){
		switch (fileExtension) {
		case "JPEG","JPG": {
			return "image/jpeg";
		}
		case "PNG": {
			return "image/png";
		}
		case "HEIC": {
			return "image/heic";
		}
		case "HEIF": {
			return "image/heif";
		}
		default:
			throw new RequestValidationException("File type must be jpeg/png/heic/heif");
		}
	}
	
}
