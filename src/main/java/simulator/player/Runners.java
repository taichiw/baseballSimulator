package simulator.player;

import java.util.ArrayList;
import java.util.List;

public class Runners {
	List<Runner> runnerSet = new ArrayList<>();
	int numberOfRunnerBackedToHome = 0;

	public int getScore() {
		return numberOfRunnerBackedToHome;
	}
}
