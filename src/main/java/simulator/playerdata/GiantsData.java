package simulator.playerdata;

import java.util.List;

public class GiantsData implements TeamPlayersDataInterface {

	private final static String CSV = "\"坂本勇人\",\"539\",\"157\",\"30\",\"0\",\"15\",\"68\"\n"
									  + "\"マギー\",\"523\",\"165\",\"48\",\"1\",\"18\",\"58\"\n"
									  + "\"長野久義\",\"463\",\"121\",\"20\",\"3\",\"16\",\"46\"\n"
									  + "\"阿部慎之助\",\"455\",\"119\",\"13\",\"0\",\"15\",\"41\"\n"
									  + "\"小林誠司\",\"378\",\"78\",\"11\",\"1\",\"2\",\"41\"\n"
									  + "\"村田修一\",\"381\",\"100\",\"19\",\"0\",\"14\",\"34\"\n"
									  + "\"陽岱鋼\",\"330\",\"87\",\"18\",\"1\",\"9\",\"41\"\n"
									  + "\"亀井善行\",\"247\",\"62\",\"16\",\"0\",\"6\",\"27\"\n";

	@Override
	public List<PlayerData> getPlayerData() {
		return PlayersDataUtil.getPlayerDataWithPitcher(CSV);
	}
}