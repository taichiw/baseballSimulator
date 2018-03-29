package simulator.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AveScoreOfEachPlayesrs {
	List<PlayerScore> listOfaveScoreOfEachPlayer = new ArrayList<>();

	public AveScoreOfEachPlayesrs(List<PlayerScore> listOfaveScoreOfEachPlayer) {
		this.listOfaveScoreOfEachPlayer = listOfaveScoreOfEachPlayer;
	}

	public String toString() {
		return listOfaveScoreOfEachPlayer.stream()
				.map(PlayerScore::toString)
				.collect(Collectors.joining("\n"));
	}

	public List<String> getPlayerScores() {
		return listOfaveScoreOfEachPlayer.stream()
				.map(PlayerScore::toString)
				.collect(Collectors.toList());
	}

	public int getTotalDaten() {
		return listOfaveScoreOfEachPlayer.stream()
				.mapToInt(PlayerScore::getDaten)
				.sum();
	}

	public List<Double> getDatenBunpu() {
		int totalDaten = getTotalDaten();
		return listOfaveScoreOfEachPlayer.stream()
				.mapToInt(PlayerScore::getDaten)
				.mapToObj(daten -> 100l * (double) daten / (double) totalDaten)
				.collect(Collectors.toList());
	}
}
