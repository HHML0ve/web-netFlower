package bsuper;

public class CashReturn extends Cashsuper {
	private float moneyCondition = 0.0f;
	private float moneyReturn = 0.0f;

	public CashReturn(String moneyCondition, String moneyReturn) {
		this.moneyCondition = Float.parseFloat(moneyCondition);
		this.moneyReturn = Float.parseFloat(moneyReturn);
	}

	@Override
	public float acceptCash(float money) {
		if (money >= moneyCondition) {
			money = (float) (money - Math.floor(money / moneyCondition) * moneyReturn);
		}
		return money;
	}

}
