package simulator.playerdata;

import java.util.List;

public class SwallowsData implements TeamPlayersDataInterface {

	private final static String CSV = "\"山田哲人\",\"526\",\"130\",\"25\",\"1\",\"24\",\"91\"\n"
									  + "\"坂口智隆\",\"535\",\"155\",\"16\",\"2\",\"4\",\"59\"\n"
									  + "\"バレンティン\",\"445\",\"113\",\"14\",\"1\",\"32\",\"70\"\n"
									  + "\"中村悠平\",\"419\",\"102\",\"14\",\"4\",\"4\",\"42\"\n"
									  + "\"大引啓次\",\"273\",\"62\",\"11\",\"0\",\"5\",\"27\"\n"
									  + "\"藤井亮太\",\"292\",\"75\",\"3\",\"0\",\"2\",\"9\"\n"
									  + "\"雄平\",\"281\",\"86\",\"21\",\"0\",\"2\",\"12\"\n"
									  + "\"山崎晃大朗\",\"219\",\"53\",\"10\",\"2\",\"1\",\"18\"\n";

	@Override
	public List<PlayerData> getPlayerData() {
		return PlayersDataUtil.getPlayerDataWithPitcher(CSV);
	}
}