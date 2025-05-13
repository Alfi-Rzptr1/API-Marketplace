package com.rezeptor.modules.address;

public record AddressRequest(
		String roadName,
		String village,
		String subDistrict,
		String regency,
		String province,
		String detail,
		long accountDataId
		) {

}
