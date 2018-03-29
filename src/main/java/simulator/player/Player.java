package simulator.player;

import java.security.SecureRandom;
import java.util.Random;
import simulator.playerdata.PlayerData;

public class Player {

	private String name;
	private int dasuu;
	private int hit;
	private int twoBaseHit;
	private int threeBaseHit;
	private int homeRun;
	private int fourBall;

	private int singleHit;
	private int dasekiSuu;

	private int probabilityOfSingleHit;
	private int probabilityOfTwoBaseHit;
	private int probabilityOfThreeBaseHit;
	private int probabilityOfHomeRun;
	private int probabilityOfFourBall;

	private PlayerScore score;

	public Player(PlayerData eachPlayer, String name) {
		this.name = name + " " + eachPlayer.name;
		this.dasuu = eachPlayer.dasuu;
		this.hit = eachPlayer.hit;
		this.twoBaseHit = eachPlayer.twoBaseHit;
		this.threeBaseHit = eachPlayer.threeBaseHit;
		this.homeRun = eachPlayer.homeRun;
		this.fourBall = eachPlayer.fourBall;

		singleHit = hit - (twoBaseHit + threeBaseHit + homeRun);
		dasekiSuu = dasuu + fourBall;

		probabilityOfSingleHit = 1000 * singleHit / dasekiSuu;
		probabilityOfTwoBaseHit = 1000 * twoBaseHit / dasekiSuu;
		probabilityOfThreeBaseHit = 1000 * threeBaseHit / dasekiSuu;
		probabilityOfHomeRun = 1000 * homeRun / dasekiSuu;
		probabilityOfFourBall = 1000 * fourBall / dasekiSuu;

		score = new PlayerScore();
		score.name = this.name;
	}

	public BattingResult batting() {
		++score.dasekiCount;

		Random rnd = new SecureRandom();

		Integer ran = rnd.nextInt(1000);

		if (ran < probabilityOfSingleHit) {
			++score.hitCount;
			return BattingResult.SINGLE_HIT;
		} else if (ran < probabilityOfSingleHit + probabilityOfTwoBaseHit) {
			++score.hitCount;
			++score.doubleHitCount;
			return BattingResult.TWO_BASE_HIT;
		} else if (ran < probabilityOfSingleHit + probabilityOfTwoBaseHit + probabilityOfThreeBaseHit) {
			++score.hitCount;
			++score.trippleHitCount;
			return BattingResult.THREE_BASE_HIT;
		} else if (ran < probabilityOfSingleHit + probabilityOfTwoBaseHit + probabilityOfThreeBaseHit + probabilityOfHomeRun) {
			++score.hitCount;
			++score.homerunCount;
			return BattingResult.HOME_RUN;
		} else if (ran < probabilityOfSingleHit + probabilityOfTwoBaseHit + probabilityOfThreeBaseHit + probabilityOfHomeRun + probabilityOfFourBall) {
			++score.fourBallCount;
			return BattingResult.FOUR_BALL;
		} else {
			return BattingResult.OUT;
		}
	}

	public String getName() {
		return name;
	}

	public void addTokuten() {
		++score.tokuten;
	}

	public void addDaten(int score) {
		this.score.daten += score;
	}

	public String toString() {
		return score.toString();
	}

	public PlayerScore getScore() {
		return score;
	}
}
