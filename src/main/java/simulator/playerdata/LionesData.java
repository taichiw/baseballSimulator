package simulator.playerdata;

import java.util.List;

public class LionesData implements TeamPlayersDataInterface {

	private final static String CSV = "\"秋山翔吾\",\"575\",\"185\",\"38\",\"5\",\"25\",\"72\"\n"
									  + "\"源田壮亮\",\"575\",\"155\",\"18\",\"10\",\"3\",\"36\"\n"
									  + "\"浅村栄斗\",\"574\",\"167\",\"34\",\"1\",\"19\",\"44\"\n"
									  + "\"外崎修汰\",\"438\",\"113\",\"22\",\"3\",\"10\",\"33\"\n"
									  + "\"中村剛也\",\"415\",\"90\",\"14\",\"0\",\"27\",\"61\"\n"
									  + "\"メヒア\",\"345\",\"83\",\"18\",\"0\",\"19\",\"37\"\n"
									  + "\"栗山巧\",\"333\",\"84\",\"13\",\"0\",\"9\",\"27\"\n"
									  + "\"金子侑司\",\"283\",\"77\",\"17\",\"2\",\"5\",\"27\"\n"
									  + "\"炭谷銀仁朗\",\"267\",\"67\",\"11\",\"0\",\"5\",\"8\"\n";

	@Override
	public List<PlayerData> getPlayerData() {
		return PlayersDataUtil.getPlayerData(CSV);
	}
}