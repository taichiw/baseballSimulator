package simulator.ground;

import simulator.player.Runner;

public class Base {
	private String name;
	private Runner runner;
	private Base nextBase;

	public Base (String name, Base nextBase) {
		this.name = name;
		this.nextBase = nextBase;
	}

	// Runner comes to this base
	public void comeRunner(Runner newRunner) {
		Runner currentRunner = this.runner;
		this.runner = newRunner;

		if (currentRunner != null) {
			nextBase.comeRunner(currentRunner);
		}
	}

	public void runRunner(Base nextBase) {
		if (runner == null) {
			return;
		}
		nextBase.comeRunner(this.runner);
		this.runner = null;
	}

	public String isRunnerOn() {
		if (runner != null) {
			return runner.getName();
		}
		return "-";
	}
}
