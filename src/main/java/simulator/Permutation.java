package simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import simulator.playerdata.PlayerData;

public class Permutation {
	public static List<List<PlayerData>> make(List<PlayerData> candidate) {
		// 候補がないなら、順列は空リストを返す
		if (candidate.size() == 0) {
			List<List<PlayerData>> empty = new ArrayList<>();
			empty.add(new ArrayList<>());
			return empty;
		}

		// candidateから1文字ずつピックアップする
		// flatMapを使ってるので1文字はiに格納されるはず
		return candidate.stream().flatMap(i -> {
			// 残りの配列はcandidateからiを省いたものである
			List<PlayerData> rest = new ArrayList<>(candidate);
			rest.remove(i);
			// 残りの配列で順列のリストを作り、
			// 先頭にピックアップした文字を結合
			return make(rest).stream().map(list -> {
				list.add(0, i);
				return list;
			});
		}).collect(Collectors.toList());
		// flatMapを使っているので、1文字ずつピックアップして
		// 全文字の操作が終わったら、全ての操作で得られたリストを
		// 結合して返しているはず
	}
}
