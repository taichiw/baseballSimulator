package simulator.playerdata;

import java.util.List;

public class BayStarsData implements TeamPlayersDataInterface {

	private final static String CSV = "\"桑原将志\",\"598\",\"161\",\"38\",\"5\",\"13\",\"45\"\n"
									  + "\"ロペス\",\"569\",\"171\",\"42\",\"0\",\"30\",\"27\"\n"
									  + "\"筒香嘉智\",\"503\",\"143\",\"31\",\"0\",\"28\",\"93\"\n"
									  + "\"梶谷隆幸\",\"511\",\"124\",\"27\",\"2\",\"21\",\"62\"\n"
									  + "\"倉本寿彦\",\"507\",\"133\",\"27\",\"1\",\"2\",\"18\"\n"
									  + "\"宮﨑敏郎\",\"480\",\"155\",\"28\",\"1\",\"15\",\"38\"\n"
									  + "\"戸柱恭孝\",\"336\",\"72\",\"13\",\"0\",\"9\",\"19\"\n"
									  + "\"柴田竜拓\",\"215\",\"50\",\"8\",\"0\",\"1\",\"17\"\n";

	@Override
	public List<PlayerData> getPlayerData() {
		return PlayersDataUtil.getPlayerDataWithPitcher(CSV);
	}
}