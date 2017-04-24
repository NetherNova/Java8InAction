package chapter11;

import chapter11.Discount.Code;

public class Quote {
	
	private final String shopName;
	private final double price;
	private final Discount.Code discountCode;
	
	public Quote(String shopName, double price, Code discountCode) {
		super();
		this.shopName = shopName;
		this.price = price;
		this.discountCode = discountCode;
	}
	
	public static Quote parse(String s) {
		String[] components = s.split(":");
		String shopName = components[0];
		double price = Double.parseDouble(components[1]);
		// Value von Enum ist der Integer zu dem String
		Discount.Code discountCode = Discount.Code.valueOf(components[2]);
		return new Quote(shopName, price, discountCode);
	}

	public String getShopName() {
		return shopName;
	}

	public double getPrice() {
		return price;
	}

	public Discount.Code getDiscountCode() {
		return discountCode;
	}
	
	

}
