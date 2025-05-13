package com.rezeptor.modules.itemReview;

public record ItemReviewRequest(
		String star,
		String message,
		long accountId,
		long itemParrentId,
		long itemVariantId
		) {

}
