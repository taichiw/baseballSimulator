package simulator.playerdata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersDataUtil {

	public static List<PlayerData> getPlayerDataWithPitcher(String string) {
		List<PlayerData> playerData = getPlayerData(string);
		playerData.add(new Pitcher());

		return playerData;
	}


	public static List<PlayerData> getPlayerData(String string) {
		List<PlayerData> playerDatas = Arrays.stream(string.split("\\n"))
				.map(
						player -> {
							String[] data = player.split(",");
							PlayerData playerData = new PlayerData();
							playerData.name = data[0].replaceAll("\"", "");
							playerData.dasuu = convertToInt(data[1]);
							playerData.hit = convertToInt(data[2]);
							playerData.twoBaseHit = convertToInt(data[3]);
							playerData.threeBaseHit = convertToInt(data[4]);
							playerData.homeRun = convertToInt(data[5]);
							playerData.fourBall = convertToInt(data[6]);

							return playerData;
						}
				).collect(Collectors.toList());
		return playerDatas;
	}

	private static int convertToInt(String str) {
		return Integer.valueOf(str.replaceAll("\"", ""));
	}

}
