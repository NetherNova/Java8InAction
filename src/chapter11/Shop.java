package chapter11;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
	
	private String name;
	
	public Shop(String name) {
		this.name = name;
	}
	
	public String getPriceWithDiscount(String product) {
		double price = calculatePrize(product); // price server call
		Random random = new Random();
		Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)]; // add random discount
		return String.format(Locale.US, "%s:%.2f:%s", name, price, code);
	}
	
	public double getPrice(String product) {
		double price = calculatePrize(product);
		return price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> future = new CompletableFuture<>(); 
		new Thread( () -> {
			try {
				double price = calculatePrize(product);
				future.complete(price);
			} catch(Exception ex) {
				future.completeExceptionally(ex);
			}
		}).start();
		return future;
	}
	
	public Future<Double> getPriceAsyncSupply(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrize(product));
	}
	
	private double calculatePrize(String product) {
		//DelayHelper.fixedDelay(Thread.currentThread(), 1000);
		DelayHelper.randomDelay(Thread.currentThread(), 1000);
		return Math.random() * product.charAt(0) + product.charAt(1);
	}
}
