package com.rezeptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rezeptor.auth.token.ConfirmationTokenRepo;
import com.rezeptor.modules.ItemParrentVariant.ItemParrentVariantRepo;
import com.rezeptor.modules.account.AccountRepo;
import com.rezeptor.modules.accountData.AccountDataRepo;
import com.rezeptor.modules.accountImage.AccountImageRepo;
import com.rezeptor.modules.address.AddressRepo;
import com.rezeptor.modules.cart.CartRepo;
import com.rezeptor.modules.cartItem.CartItemRepo;
import com.rezeptor.modules.item.ItemRepo;
import com.rezeptor.modules.itemCategory.ItemCategoryRepo;
import com.rezeptor.modules.itemImage.ItemImageRepo;
import com.rezeptor.modules.itemReview.ItemReviewRepo;
import com.rezeptor.modules.itemVariant.ItemVariantRepo;
import com.rezeptor.modules.merchantData.MerchantDataRepo;
import com.rezeptor.modules.notification.NotificationRepo;
import com.rezeptor.modules.order.OrderRepo;
import com.rezeptor.modules.orderItem.OrderItemRepo;

@EnableJpaRepositories(basePackageClasses = {
		ConfirmationTokenRepo.class,
		AccountRepo.class,
		AccountDataRepo.class,
		AccountImageRepo.class,
		AddressRepo.class,
		CartRepo.class,
		CartItemRepo.class,
		ItemRepo.class,
		ItemCategoryRepo.class,
		ItemImageRepo.class,
		ItemReviewRepo.class,
		ItemParrentVariantRepo.class,
		ItemVariantRepo.class,
		MerchantDataRepo.class,
		NotificationRepo.class,
		OrderRepo.class,
		OrderItemRepo.class,
		
})
@EntityScan(basePackages = {"com.rezeptor.modules","com.rezeptor.auth.token"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

