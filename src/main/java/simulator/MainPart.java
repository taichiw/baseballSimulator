package simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import simulator.playerdata.BayStarsData;
import simulator.playerdata.BuffalosData;
import simulator.playerdata.DragonsData;
import simulator.playerdata.EaglesData;
import simulator.playerdata.FightersData;
import simulator.playerdata.GiantsData;
import simulator.playerdata.HawksData;
import simulator.playerdata.LionesData;
import simulator.playerdata.MarinesData;
import simulator.playerdata.PlayerData;
import simulator.playerdata.CarpData;
import simulator.playerdata.SwallowsData;
import simulator.playerdata.TeamPlayersDataInterface;
import simulator.playerdata.TigersData;

public class MainPart {

	public static Response simulateOneDatenbunpu(Request request, List<PlayerData> nineBatters) {
		String datenBunpuKey = request.datenBunpuKey;
		List<Double> expectedDatenBunpu = DatenBunpuMap.getDatenBunpuMap().get(datenBunpuKey);

		Map<Integer, Integer> mappingBetweenDajunRank_And_BattingOrder = new HashMap<>();
		List<Double> sortedExpectedDatenBunpu = new ArrayList<>();
		sortedExpectedDatenBunpu.addAll(expectedDatenBunpu);
		sortedExpectedDatenBunpu.sort(Comparator.comparing(Double::doubleValue).reversed());
		for (int i = 0; i < expectedDatenBunpu.size(); i++) {
			Double datenRitsu = expectedDatenBunpu.get(i);
			int rank = sortedExpectedDatenBunpu.indexOf(datenRitsu); //お手本打点分布のi番バッターの打点は、打順内で第○位の高さ
			mappingBetweenDajunRank_And_BattingOrder.put(i, rank);
		}

		nineBatters.sort(Comparator.comparing(PlayerData::calcChodaritsu).reversed());
		debugSout("Sorted order" + nineBatters);

		//Generate all combination of order
		List<List<PlayerData>> permulationOfNineBatters = Permutation.make(nineBatters);

		List<List<PlayerData>> fillteredOrders = permulationOfNineBatters
				.stream()
				.filter(eachPlayerInterfaces -> dajunCheck(eachPlayerInterfaces, nineBatters, mappingBetweenDajunRank_And_BattingOrder))
				.collect(Collectors.toList());

		OneOrderResult bestOrderResult = fillteredOrders.stream()
				.map(oneOrder -> SimulateOneOrder.simulateOneOrder(oneOrder, expectedDatenBunpu, request.numberOfSeasonSimulation))
				.sorted(Comparator.comparing(OneOrderResult::getRuijido))
				.findFirst().orElse(null);

		Response response = new Response();
		response.bestOrderResult = bestOrderResult.aveScoreOfEachPlayers;
		response.totalDaten = bestOrderResult.totalDaten;
		response.actual = bestOrderResult.datenBunpu;
		response.expected = expectedDatenBunpu;
		response.ruijido = bestOrderResult.ruijido;

		return response;
	}

	/**
	 * @param permulationOfNineBatter                  : 今から検証する打順
	 * @param sortedOrder                              : 選手を長打率の高い順に並べたリスト
	 * @param mappingBetweenDajunRank_And_BattingOrder 　：　お手本打率分布内の、打順ごとの打点順位を表したマップ。打順→チーム内序列
	 *
	 * @return
	 */
	private static boolean dajunCheck(List<PlayerData> permulationOfNineBatter, List<PlayerData> sortedOrder, Map<Integer, Integer> mappingBetweenDajunRank_And_BattingOrder) {
		//チーム内長打率１番手　→　打点1 or 2の打順にのみ入れる
		//チーム内長打率２番手　→　打点1, 2, 3 の打順にのみ入れる
		//チーム内長打率３番手　→　打点2, 3, 4 の打順にのみ入れる
		// …
		for (int battingOrder = 0; battingOrder < permulationOfNineBatter.size(); battingOrder++) {
			int datenRankFromExpectedDatenBunpu = mappingBetweenDajunRank_And_BattingOrder.get(battingOrder);    //例：目標得点分布では、１番バッターの打点はチーム内３位
			PlayerData player = permulationOfNineBatter.get(battingOrder);
			int chodaritsuRankInTheTeam = sortedOrder.indexOf(player);        //例：今回検証している打順の、１番バッターの長打率は、チーム内２位
			if (chodaritsuRankInTheTeam < datenRankFromExpectedDatenBunpu - 1 || datenRankFromExpectedDatenBunpu + 1 < chodaritsuRankInTheTeam) {
				return false;
			} else {
				debugSout("battingOrder" + battingOrder
						  + ", datenRankFromExpectedDatenBunpu" + datenRankFromExpectedDatenBunpu
						  + ", chodaritsuRankInTheTeam" + chodaritsuRankInTheTeam
						  + ", player" + player.name);
			}
		}
		return true;
	}

	private static void debugSout(String str) {
//						System.out.println(str);
	}
}
