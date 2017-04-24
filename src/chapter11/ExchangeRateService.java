package chapter11;

public class ExchangeRateService {
	
	public enum Money {
		EUR, USD;
	}
	
	public double getRate(Money cur1, Money cur2) {
		DelayHelper.randomDelay(Thread.currentThread(), 1000);
		if (cur1 == Money.EUR && cur2 == Money.USD) {
			return 1.28;
		}
		else {
			return 1 / 1.28;
		}
	}
}
