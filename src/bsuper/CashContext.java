package bsuper;

public class CashContext {
	private Cashsuper cashsuper = null;

	public CashContext(String type) {
		switch (type) {
		case "normal":
			cashsuper = new CashNormal();
			break;
		case "rebate":
			cashsuper = new CashRebate("0.8");
			break;
		case "return":
			cashsuper = new CashReturn("300", "100");
		default:
			break;
		}
	}
	public float GetResult(float money) {
		return cashsuper.acceptCash(money);
	}
}
