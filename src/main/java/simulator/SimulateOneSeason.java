package simulator;

import java.util.List;
import simulator.ground.Bases;
import simulator.player.Batters;
import simulator.player.BattingResult;
import simulator.player.Player;
import simulator.player.PlayerScore;
import simulator.player.Runner;
import simulator.player.Runners;
import simulator.playerdata.PlayerData;

public class SimulateOneSeason {

	private static final int NUMBER_OF_GAME_IN_ONE_SEASON = 143;
	private static final int NUMBER_OF_INNINGS_IN_ONE_GAME = 9;
	private static final int NUMBER_OF_OUTS_IN_ONE_INNING = 3;

	public static List<PlayerScore> oneSeason(List<PlayerData> oneOrder) {
		Batters batters = new Batters(oneOrder);

		int score = 0;

		for (int i = 1; i <= NUMBER_OF_GAME_IN_ONE_SEASON; i++) {
			debugSout("Game " + i + " Start!!");
			score += oneGame(batters);
		}
		debugSout("Game set! : Season Total Score : " + score);

		return batters.getScores();
	}

	public static int oneGame(Batters batters) {
		int score = 0;
		batters.resetBattingOrder();

		for (int inning = 1; inning <= NUMBER_OF_INNINGS_IN_ONE_GAME; inning++) {
			debugSout(inning + "回 Start!");

			Runners runners = new Runners();
			Bases bases = new Bases();

			int outCount = 0;
			int thisInningsScore = 0;
			Player batter = null;

			score = oneInning(score, batters, bases, outCount);
			thisInningsScore = runners.getScore();
			score += thisInningsScore;
			debugSout(inning + "回 End! : " + thisInningsScore);
//			debugSout(batters.toString());
		}

		debugSout("Game set! : Score : " + score);

		return score;
	}

	public static int oneInning(int score, Batters batters, Bases bases, int outCount) {
		Player batter;
		while (true) {
			debugSout(bases.getRunnersString());
			batter = batters.getNextBatter();
			debugSout("Batter : " + batter.getName());

			BattingResult battingResult = batter.batting();
			debugSout(battingResult.toString());

			if (battingResult == BattingResult.OUT) {
				outCount++;
				debugSout("OUT : " + outCount);
				if (outCount >= NUMBER_OF_OUTS_IN_ONE_INNING) {
					break;
				}
			} else if (battingResult == BattingResult.FOUR_BALL) {
				bases.fourBall(new Runner(batter));
			} else {
				bases.hit(new Runner(batter), battingResult.getBases());
			}
			debugSout(bases.getHomeBase().getStrOfHomeInRunners());
			int homeIn = bases.getHomeBase().homeIn();
			if (homeIn > 0) {
				debugSout("HomeIn : " + homeIn);
				batter.addDaten(homeIn);
				score += homeIn;
			}
		}
		return score;
	}

	private static void debugSout(String str) {
						System.out.println(str);
	}
}
