package simulator.ground;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import simulator.player.Runner;

public class HomeBase extends Base {

	private Set<Runner> runnersBackToHome = new HashSet<>();

	public HomeBase() {
		super("HomeBase", null);
	}

	@Override
	public void comeRunner(Runner newRunner) {
		runnersBackToHome.add(newRunner);
	}

	public int homeIn(){
		runnersBackToHome.stream()
				.forEach(runner -> runner.getBatter().addTokuten());

		int score = runnersBackToHome.size();

		runnersBackToHome.clear();

		return score;
	}

	public String getStrOfHomeInRunners() {
		return runnersBackToHome.stream()
					.map(Runner::getName)
					.collect(Collectors.joining("\n"));
	}
}
