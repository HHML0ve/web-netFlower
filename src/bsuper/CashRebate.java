package bsuper;

public class CashRebate extends Cashsuper {
	private float moneyRebate = 0.0f;

	public CashRebate(String moneyRebate) {
		this.moneyRebate = Float.parseFloat(moneyRebate);
	}

	@Override
	public float acceptCash(float money) {

		return money * moneyRebate;
	}

}
