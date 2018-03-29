package simulator.player;

public class Runner {
	private String name;
	private int base = 0;
	private Player batter;

	public Runner() {
		name = "";
	}

	public Runner(Player batter) {
		this.batter = batter;
		name = batter.getName();
	}

	public void progress(int bases) {
		this.base += bases;
	}

	public int getBase() {
		return base;
	}

	public String getName() {
		return name;
	}

	public Player getBatter() {
		return batter;
	}
}
