package simulator.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import simulator.playerdata.PlayerData;

public class Batters implements Serializable {
	public static final int ZERO = 0;
	public static final int NINE = 9;

	public List<Player> batters;
	private int currentBattingOrder = 0;

	public Batters(List<PlayerData> oneOrder) {

		batters = new ArrayList<>();
		for (PlayerData playerData : oneOrder) {
			batters.add(new Player(playerData, ""));
		}
	}

	public void resetBattingOrder() {
		currentBattingOrder = 0;
	}

	public Player getNextBatter() {
		Player nextBatter = batters.get(currentBattingOrder);

		++currentBattingOrder;
		if (currentBattingOrder >= NINE) {
			currentBattingOrder = ZERO;
		}

		return nextBatter;
	}

	public List<PlayerScore> getScores() {
		return batters.stream()
				.map(Player::getScore)
				.collect(Collectors.toList());
	}

	public String toString() {
		return batters.stream()
				.map(Player::toString)
				.collect(Collectors.joining("\n"));
	}
}
