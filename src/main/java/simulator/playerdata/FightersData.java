package simulator.playerdata;

import java.util.List;

public class FightersData implements TeamPlayersDataInterface {

	private final static String CSV = "\"西川遥輝\",\"541\",\"160\",\"26\",\"6\",\"9\",\"69\"\n"
									  + "\"レアード\",\"503\",\"115\",\"18\",\"1\",\"32\",\"54\"\n"
									  + "\"中田翔\",\"472\",\"102\",\"23\",\"0\",\"16\",\"61\"\n"
									  + "\"大田泰示\",\"427\",\"110\",\"21\",\"1\",\"15\",\"28\"\n"
									  + "\"松本剛\",\"402\",\"110\",\"17\",\"0\",\"5\",\"21\"\n"
									  + "\"石井一成\",\"317\",\"65\",\"8\",\"2\",\"3\",\"28\"\n"
									  + "\"田中賢介\",\"314\",\"79\",\"6\",\"1\",\"2\",\"35\"\n"
									  + "\"中島卓也\",\"283\",\"59\",\"1\",\"2\",\"1\",\"23\"\n"
									  + "\"大谷翔平\",\"202\",\"67\",\"16\",\"1\",\"8\",\"24\"\n";

	@Override
	public List<PlayerData> getPlayerData() {
		return PlayersDataUtil.getPlayerData(CSV);
	}
}