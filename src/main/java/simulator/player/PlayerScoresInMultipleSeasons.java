package simulator.player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class PlayerScoresInMultipleSeasons {
	// 0: 1番、2番、3番、、、、
	// 1: 1番、2番、3番、、、、
	// 2: 1番、2番、3番、、、、
	private List<List<PlayerScore>> playerScoresInMultipleSeasons = new ArrayList<>();

	public void setListOfBattersInOneSeason(List<List<PlayerScore>> playerScoresInMultipleSeasons) {
		this.playerScoresInMultipleSeasons = playerScoresInMultipleSeasons;
	}

	public AveScoreOfEachPlayesrs getAveScoreOfEachPlayesrs() {
		List<PlayerScore> listOfaveScoreOfEachPlayer = new ArrayList<>();
		for (int battingOrder = 0; battingOrder <= 8; battingOrder++) {
			listOfaveScoreOfEachPlayer.add(getAveScoreOfEachPlayer(battingOrder));
		}
		return new AveScoreOfEachPlayesrs(listOfaveScoreOfEachPlayer);
	}

	private PlayerScore getAveScoreOfEachPlayer(int battingOrder) {
		List<List<PlayerScore>> tmpList = new ArrayList<>(playerScoresInMultipleSeasons);
		String name = tmpList.get(0).get(battingOrder).getName();
		List<PlayerScore> playerScoreListForEachSeason = tmpList.stream()
				.map(playerScoresInOneSeason -> playerScoresInOneSeason.get(battingOrder))    //シーズン成績の中から特定の（打順の）バッターを取り出す
				.collect(Collectors.toList());

		PlayerScore aveScoreOfThePlayer = new PlayerScore();
		aveScoreOfThePlayer.name = name;
		aveScoreOfThePlayer.tokuten = getAverage(playerScoreListForEachSeason, PlayerScore::getTokuten);
		aveScoreOfThePlayer.daten = getAverage(playerScoreListForEachSeason, PlayerScore::getDaten);
		aveScoreOfThePlayer.dasekiCount = getAverage(playerScoreListForEachSeason, PlayerScore::getDasekiCount);
		aveScoreOfThePlayer.hitCount = getAverage(playerScoreListForEachSeason, PlayerScore::getHitCount);
		aveScoreOfThePlayer.doubleHitCount = getAverage(playerScoreListForEachSeason, PlayerScore::getDoubleHitCount);
		aveScoreOfThePlayer.trippleHitCount = getAverage(playerScoreListForEachSeason, PlayerScore::getTrippleHitCount);
		aveScoreOfThePlayer.homerunCount = getAverage(playerScoreListForEachSeason, PlayerScore::getHomerunCount);
		aveScoreOfThePlayer.fourBallCount = getAverage(playerScoreListForEachSeason, PlayerScore::getFourBallCount);

		return aveScoreOfThePlayer;

	}

	private int getAverage(List<PlayerScore> playerScoreListForEachSeason, ToIntFunction<PlayerScore> func) {
		return (int) playerScoreListForEachSeason.stream()
				.mapToInt(func)
				.average()
				.orElse(0d);
	}
}
