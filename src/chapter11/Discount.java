package chapter11;

public class Discount {
	
	public enum Code {
		NONE(0), SLIVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
		
		private final int percentage;
		
		Code(int percentage) {
			this.percentage = percentage;
		}
	}
	
	public static String applyDiscount(Quote quote) {
		return "Shop: " + quote.getShopName() + 
				Discount.apply(quote.getPrice() , quote.getDiscountCode());
	}
	
	private static double apply(double price, Code code) {
		DelayHelper.randomDelay(Thread.currentThread(), 500);
		return price * (100 - code.percentage) / 100;
	}
}
