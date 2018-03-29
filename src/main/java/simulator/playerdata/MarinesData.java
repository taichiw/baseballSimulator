package simulator.playerdata;

import java.util.List;

public class MarinesData implements TeamPlayersDataInterface {

	private final static String CSV = "\"鈴木大地\",\"508\",\"132\",\"27\",\"5\",\"11\",\"55\"\n"
									  + "\"角中勝也\",\"383\",\"103\",\"17\",\"4\",\"8\",\"62\"\n"
									  + "\"荻野貴司\",\"356\",\"94\",\"22\",\"1\",\"5\",\"25\"\n"
									  + "\"田村龍弘\",\"311\",\"77\",\"12\",\"3\",\"3\",\"26\"\n"
									  + "\"中村奨吾\",\"280\",\"77\",\"13\",\"2\",\"9\",\"20\"\n"
									  + "\"加藤翔平\",\"271\",\"72\",\"12\",\"5\",\"5\",\"6\"\n"
									  + "\"パラデス\",\"269\",\"59\",\"9\",\"0\",\"10\",\"16\"\n"
									  + "\"清田育宏\",\"231\",\"47\",\"8\",\"1\",\"3\",\"24\"\n"
									  + "\"ペーニャ\",\"219\",\"53\",\"14\",\"0\",\"15\",\"26\"\n";

	@Override
	public List<PlayerData> getPlayerData() {
		return PlayersDataUtil.getPlayerData(CSV);
	}
}