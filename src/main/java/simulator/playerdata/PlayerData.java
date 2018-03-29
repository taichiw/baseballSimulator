package simulator.playerdata;

public class PlayerData {
	public String name = "";
	public int dasuu = 0;
	public int hit = 0;
	public int twoBaseHit = 0;
	public int threeBaseHit = 0;
	public int homeRun = 0;
	public int fourBall = 0;

	public int calcSingleHit() {
		return hit - twoBaseHit - threeBaseHit - homeRun;
	}

	public double calcChodaritsu() {
		return (double) (calcSingleHit() + twoBaseHit * 2 + threeBaseHit * 3 + homeRun * 4) / (double) dasuu;
	}

	public double calcShutsuruiritsu() {
		return (double) (hit + fourBall) / (double) (dasuu + hit);
	}

	public double calcOps() {
		return calcChodaritsu() + calcShutsuruiritsu();
	}
}
