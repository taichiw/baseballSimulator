package simulator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import simulator.playerdata.BayStarsData;
import simulator.playerdata.BuffalosData;
import simulator.playerdata.CarpData;
import simulator.playerdata.DragonsData;
import simulator.playerdata.EaglesData;
import simulator.playerdata.FightersData;
import simulator.playerdata.GiantsData;
import simulator.playerdata.HawksData;
import simulator.playerdata.LionesData;
import simulator.playerdata.MarinesData;
import simulator.playerdata.SwallowsData;
import simulator.playerdata.TeamPlayersDataInterface;
import simulator.playerdata.TigersData;

public class Main {

	public static void main(String[] args) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Request request = new Request();
		request.numberOfSeasonSimulation = 300;

		List<TeamPlayersDataInterface> playersDatas = Arrays.asList(
				new GiantsData(),
				new SwallowsData(),
				new BayStarsData(),
				new DragonsData(),
				new TigersData(),
				new CarpData(),
				new FightersData(),
				new EaglesData(),
				new LionesData(),
				new MarinesData(),
				new BuffalosData(),
				new HawksData()
		);

		SimpleDateFormat d1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		for (TeamPlayersDataInterface playersData : playersDatas) {
			System.out.println(playersData.getClass().getName());

			DatenBunpuMap.getDatenBunpuMap().keySet().stream().forEach(datenBunpuKey -> {

				String startTime = d1.format(new Date());
				System.out.println(datenBunpuKey + " start " + startTime);
				request.datenBunpuKey = datenBunpuKey;

				Response response = MainPart.simulateOneDatenbunpu(request, playersData.getPlayerData());
				System.out.println("Result\n" + gson.toJson(response));
			});
		}
	}
}
