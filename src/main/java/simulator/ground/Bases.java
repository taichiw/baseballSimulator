package simulator.ground;

import java.util.ArrayList;
import java.util.List;
import simulator.player.Runner;

public class Bases {
	List<Base> bases = new ArrayList<>();

	private static final int NUMBER_OF_BASES = 4;

	public Bases() {
		Base homeBase = new HomeBase();
		Base thirdBase = new Base("ThirdBase", homeBase);
		Base secondBase = new Base("SecondBase", thirdBase);
		Base firstBase = new Base("FirstBase", secondBase);

		bases.add(firstBase);
		bases.add(secondBase);
		bases.add(thirdBase);
		bases.add(homeBase);
	}

	public void hit(Runner butterRunner, int numberOfRunning) {
		//Run each runner
		for (int i = NUMBER_OF_BASES - 1; i >= 0; i--) {
			int nextBaseIndex = i + numberOfRunning;
			if (nextBaseIndex >= NUMBER_OF_BASES - 1) {
				nextBaseIndex = NUMBER_OF_BASES - 1;
			}
			Base nextBase = bases.get(nextBaseIndex);

			bases.get(i).runRunner(nextBase);
		}

		bases.get(numberOfRunning - 1).comeRunner(butterRunner);
	}

	public void fourBall(Runner butterRunner) {
		bases.get(0).comeRunner(butterRunner);
	}

	public String getRunnersString() {
		return bases.get(0).isRunnerOn()
						 + ", " + bases.get(1).isRunnerOn()
						 + ", " + bases.get(2).isRunnerOn();
	}

	public HomeBase getHomeBase() {
		return (HomeBase) bases.get(3);
	}
}
