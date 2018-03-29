package simulator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.List;
import simulator.player.PlayerScore;
import simulator.player.PlayerScores;
import simulator.playerdata.PlayerData;

public class MyLambdaOneSeason implements RequestHandler<List<PlayerData>, PlayerScores> {

	@Override
	public PlayerScores handleRequest(List<PlayerData> request, Context context) {
		List<PlayerScore> playerScores = SimulateOneSeason.oneSeason(request);
		return new PlayerScores(playerScores);
	}
}
