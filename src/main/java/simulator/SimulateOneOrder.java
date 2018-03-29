package simulator;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import simulator.player.AveScoreOfEachPlayesrs;
import simulator.player.PlayerScore;
import simulator.player.PlayerScores;
import simulator.player.PlayerScoresInMultipleSeasons;
import simulator.playerdata.PlayerData;

public class SimulateOneOrder {
	public static OneOrderResult simulateOneOrder(List<PlayerData> oneOrder, List<Double> expectedDatenBunpu, int numberOfSeasonSimulation) {

		PlayerScoresInMultipleSeasons playerScoresInMultipleSeasons = new PlayerScoresInMultipleSeasons();

		ExecutorService pool = Executors.newFixedThreadPool(Math.min(100, numberOfSeasonSimulation));
		CountDownLatch latch = new CountDownLatch(numberOfSeasonSimulation);

		List<List<PlayerScore>> palayerScoresInMultipleSeason = IntStream.range(0, numberOfSeasonSimulation).parallel()
				.mapToObj(seasonCount -> {
							return SimulateOneSeason.oneSeason(oneOrder);
						}
				).collect(Collectors.toList());

		playerScoresInMultipleSeasons.setListOfBattersInOneSeason(palayerScoresInMultipleSeason);

		AveScoreOfEachPlayesrs aveScoreOfEachPlayesrs = playerScoresInMultipleSeasons.getAveScoreOfEachPlayesrs();
		List<Double> datenBunpu = aveScoreOfEachPlayesrs.getDatenBunpu();
		//		System.out.println(aveScoreOfEachPlayesrs.toString());
		//		System.out.println("Total-Daten : " + aveScoreOfEachPlayesrs.getTotalDaten());
		//		System.out.println("Actual : " + datenBunpu);
		//		System.out.println("Expected : " + expectedDatenBunpu);

		double ruijido = 0;
		for (int i = 0; i < datenBunpu.size(); i++) {
			ruijido += (datenBunpu.get(i) - expectedDatenBunpu.get(i)) * (datenBunpu.get(i) - expectedDatenBunpu.get(i));
		}
		//		System.out.println("Ruijido : " + ruijido);

		OneOrderResult oneOrderResult = new OneOrderResult();
		oneOrderResult.aveScoreOfEachPlayers = aveScoreOfEachPlayesrs.getPlayerScores();
		oneOrderResult.totalDaten = aveScoreOfEachPlayesrs.getTotalDaten();
		oneOrderResult.datenBunpu = aveScoreOfEachPlayesrs.getDatenBunpu();
		oneOrderResult.ruijido = ruijido;

		return oneOrderResult;
	}

	private static PlayerScores callAWS() {

		// ARN
		String functionName = "";

		String inputJSON = "[\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\",\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\",\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\",\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\",\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\",\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\",\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\",\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\",\n"
						   + "  \"simulator.playerdata.eagles.E_Amador\"\n"
						   + "]";

		InvokeRequest lmbRequest = new InvokeRequest()
				.withFunctionName(functionName)
				.withPayload(inputJSON);

		lmbRequest.setInvocationType(InvocationType.RequestResponse);

		AWSLambda lambda = AWSLambdaClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		InvokeResult lmbResult = lambda.invoke(lmbRequest);

		String resultJSON = new String(lmbResult.getPayload().array(), Charset.forName("UTF-8"));
		//		System.out.println(inputJSON + "\n" + resultJSON);

		ObjectMapper mapper = new ObjectMapper();

		PlayerScores playerScores = null;
		try {
			playerScores = mapper.readValue(resultJSON, PlayerScores.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerScores;

	}

}