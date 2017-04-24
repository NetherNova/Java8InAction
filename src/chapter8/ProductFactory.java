package chapter8;

import java.util.HashMap;
import java.util.Map;

public class ProductFactory {
	
	private Map<String, TriFunction<Integer, Integer, String, Product>> map;
	private static ProductFactory instance;
	private static int counter = 0;
	
	private ProductFactory() {
		counter++;
		map = new HashMap<String, TriFunction<Integer, Integer, String, Product>>();
		map.put("test", TestProduct::new);
		map.put("normal", Product::new);
	}
	
	public Product createProduct(String type, int c1, int c2, String name) {
		TriFunction<Integer, Integer, String, Product> constructor = map.get(type);
		return constructor.apply(c1, c2, name);
	}
	
	public static ProductFactory getInstance() {
		if (instance == null) {
			instance = new ProductFactory();
		}
		System.out.println("Instances of Factory: " + counter);
		return instance;
	}
}
