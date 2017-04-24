package chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chapter11.ExchangeRateService.Money;

public class Main {

	public static List<Shop> shops = Arrays.asList(new Shop("Lidl"), new Shop("ReWe"), new Shop("Aldi"), new Shop("Penny"),
			 new Shop("Kaufland"),  new Shop("Netto"));
	
	public static void main(String[] args) {
		boolean discount = true;
		
		if (!discount) {
			Shop shop = new Shop("Mu");
			long init = System.nanoTime();
			Future<Double> price = shop.getPriceAsync("My Test");
			long callTime = System.nanoTime();
			System.out.println("One Shop synch call took: " + (callTime - init) / 1000000);
			//Do something
			try {
				double priceValue = price.get();
				System.out.println("Price: " + priceValue);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}		
			long callBackTime = System.nanoTime();
			System.out.println("Actual call back time: " + (callBackTime - init) / 1000000);
			
			long start = System.nanoTime();
			System.out.println("Call to find prices: " + findPricesFuture("MyPhone7s", shops));
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Done in " + duration + " milliseconds");
		}
		else {
			/********** WITH DISCOUNT STUFF ***************/
			List<String> prices = shops.stream()
				.map(s -> s.getPriceWithDiscount("Meh"))
				.collect(Collectors.toList());
			System.out.println(prices);
			
			long start = System.nanoTime();
			System.out.println("Call to find prices with discount");
			List<String> discountedPrices = findPricesWithDiscountAsync("Meh", shops); //findPricesWithDiscount("Meh", shops); 
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Done in " + duration + " milliseconds");
			
			System.out.println(discountedPrices);
			
			ExchangeRateService exchangeService = new ExchangeRateService();
			CompletableFuture<Double> futurePriceUSD = 
					CompletableFuture.supplyAsync(() -> shops.get(0).getPrice("meh"))
					.thenCombine(CompletableFuture.supplyAsync(() -> exchangeService.getRate(Money.EUR, Money.USD) ), 
							(price, rate) -> price * rate);
			
			CompletableFuture[] futures = findPricesStream("myPhone")
					.map(f -> f.thenAccept(System.out::println))
					.toArray(CompletableFuture[]::new);
			CompletableFuture.allOf(futures).join();
			// If don't want to wait for all Futures to finish --> CompletableFuture.anyOf(futures);
		}	
	}
	
	public static Stream<CompletableFuture<String>> findPricesStream(String product) {
		return shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscount(product)))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(quote -> 
					CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))
					)
				);
	}
	
	public static List<String> findPricesWithDiscount(String product, List<Shop> shops) {
		return shops.stream()
				.map(shop -> shop.getPriceWithDiscount(product))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.collect(Collectors.toList());
	}
	
	public static List<String> findPricesWithDiscountAsync(String product, List<Shop> shops) {
		List<CompletableFuture<Object>> futures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscount(product)))
				.map(future -> future.thenApply(Quote::parse)) // has to wait for the first future to finish
				.map(future -> future.thenCompose(
						quote -> CompletableFuture.supplyAsync(
								() -> Discount.applyDiscount(quote)
								)
							)
					)
				.collect(Collectors.toList());
		
		return futures.stream()
				.map(future -> future.join().toString())
				.collect(Collectors.toList());
	}
	
	public static List<String> findPrices(String product,List<Shop> shops) {
		return shops.parallelStream()
				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
				.collect(Collectors.toList());
	}
	
	public static List<String> findPricesFuture(String product,List<Shop> shops) {
		final Executor executor = 
				Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						t.setDaemon(true);
						return t;
					}
				});
		
		List<CompletableFuture<String>> futurePrices = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), 
						shop.getPrice(product)), executor))
				.collect(Collectors.toList());
		// need seperation in two streams, because of lazy nature of intermediate stream operations
		return futurePrices.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}
}
