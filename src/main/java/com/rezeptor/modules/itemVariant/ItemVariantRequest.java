package com.rezeptor.modules.itemVariant;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public record ItemVariantRequest(
		long itemParrentId,
		MultipartFile imageFile,
		String name,
		String SKU,
		Map<String, Object> variantCombinations,
		String price,
		String itemDesc,
		int quantity
		) {

}
