package com.utility;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPojo;

public class FakeAddressUtil {

	public static void main(String[] args) {
		getFakeAddress();
	}

	public static AddressPojo getFakeAddress() {
		Faker faker = new Faker(Locale.US);
		return new AddressPojo(faker.company().name(), faker.address().buildingNumber(), faker.address().fullAddress(), faker.address().city(), 
				faker.address().zipCode(), faker.phoneNumber().cellPhone(), faker.phoneNumber().cellPhone(), "Other", "Home Address", faker.address().state());
	}

}
