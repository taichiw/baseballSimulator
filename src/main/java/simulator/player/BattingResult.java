package simulator.player;

public enum BattingResult {
	SINGLE_HIT(1), TWO_BASE_HIT(2), THREE_BASE_HIT(3), HOME_RUN(4), FOUR_BALL(1), OUT(0);

	private int bases;

	private BattingResult(int bases) {
		this.bases = bases;
	}

	public int getBases() {
		return this.bases;
	}
}
