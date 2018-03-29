package simulator.playerdata;

import java.util.List;

public class DragonsData implements TeamPlayersDataInterface {

	private final static String CSV = "\"京田陽太\",\"564\",\"149\",\"23\",\"8\",\"4\",\"18\"\n"
									  + "\"大島洋平\",\"476\",\"149\",\"20\",\"3\",\"3\",\"37\"\n"
									  + "\"ゲレーロ\",\"469\",\"131\",\"22\",\"3\",\"35\",\"24\"\n"
									  + "\"藤井淳志\",\"374\",\"99\",\"18\",\"3\",\"6\",\"24\"\n"
									  + "\"ビシエド\",\"332\",\"83\",\"11\",\"1\",\"18\",\"29\"\n"
									  + "\"福田永将\",\"299\",\"81\",\"19\",\"0\",\"18\",\"21\"\n"
									  + "\"亀澤恭平\",\"254\",\"73\",\"5\",\"0\",\"2\",\"13\"\n"
									  + "\"平田良介\",\"238\",\"58\",\"14\",\"2\",\"6\",\"29\"\n";

	@Override
	public List<PlayerData> getPlayerData() {
		return PlayersDataUtil.getPlayerDataWithPitcher(CSV);
	}
}