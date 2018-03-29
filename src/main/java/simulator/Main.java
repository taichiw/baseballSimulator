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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
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

	private static void invokeLambdaFunction() {
		ExecutorService pool = Executors.newFixedThreadPool(12);

		DatenBunpuMap.getDatenBunpuMap().keySet().parallelStream().forEach(datenBunpuKey -> {
			pool.submit(() -> {
				callAWS(datenBunpuKey);
			});
		});

		pool.shutdown();
	}

	private static void callAWS(String datenBunpuKey) {

		// ARN
		String functionName = "";

		String inputJSON = "{\"datenBunpuKey\" : \"" + datenBunpuKey + "\", \"numberOfSeasonSimulation\" : 10}";

		InvokeRequest lmbRequest = new InvokeRequest()
				.withFunctionName(functionName)
				.withPayload(inputJSON);

		lmbRequest.setInvocationType(InvocationType.RequestResponse);

		AWSLambda lambda = AWSLambdaClientBuilder.standard()
				.withRegion(Regions.US_EAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		System.out.println("call : " + datenBunpuKey);
		InvokeResult lmbResult = lambda.invoke(lmbRequest);

		String resultJSON = new String(lmbResult.getPayload().array(), Charset.forName("UTF-8"));

		System.out.println(inputJSON + "\n" + resultJSON);
	}
}
