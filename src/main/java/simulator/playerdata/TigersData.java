package simulator.playerdata;

import java.util.List;

public class TigersData implements TeamPlayersDataInterface {

	private final static String CSV = "\"鳥谷敬\",\"488\",\"143\",\"23\",\"3\",\"4\",\"77\"\n"
									  + "\"福留孝介\",\"441\",\"116\",\"20\",\"3\",\"18\",\"77\"\n"
									  + "\"糸井嘉男\",\"427\",\"124\",\"16\",\"0\",\"17\",\"59\"\n"
									  + "\"上本博紀\",\"409\",\"116\",\"20\",\"1\",\"9\",\"50\"\n"
									  + "\"中谷将大\",\"411\",\"99\",\"21\",\"1\",\"20\",\"36\"\n"
									  + "\"髙山俊\",\"328\",\"82\",\"15\",\"3\",\"6\",\"21\"\n"
									  + "\"梅野隆太郎\",\"282\",\"58\",\"9\",\"2\",\"2\",\"24\"\n"
									  + "\"大和\",\"232\",\"65\",\"6\",\"0\",\"1\",\"18\"\n";

	@Override
	public List<PlayerData> getPlayerData() {
		return PlayersDataUtil.getPlayerDataWithPitcher(CSV);
	}
}