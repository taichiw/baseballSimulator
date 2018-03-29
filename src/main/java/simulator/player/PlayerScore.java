package simulator.player;

public class PlayerScore {

	String name = "";
	public int tokuten = 0;
	public int daten = 0;
	public int dasekiCount = 0;

	public int hitCount = 0;
	public int doubleHitCount = 0;
	public int trippleHitCount = 0;
	public int homerunCount = 0;

	public int fourBallCount = 0;

	public String getName() {
		return name;
	}

	public int getTokuten() {
		return tokuten;
	}

	public int getDaten() {
		return daten;
	}

	public int getDasekiCount() {
		return dasekiCount;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getDoubleHitCount() {
		return doubleHitCount;
	}

	public int getTrippleHitCount() {
		return trippleHitCount;
	}

	public int getHomerunCount() {
		return homerunCount;
	}

	public int getFourBallCount() {
		return fourBallCount;
	}

	public int calcDasuu() {
		return dasekiCount - fourBallCount;
	}

	public double calcDaritsu() {
		return (double) hitCount / (double) calcDasuu();
	}

	public double calcShutsuruiritsu() {
		return (double) (hitCount + fourBallCount) / (double) getDasekiCount();
	}

	public int calcSingleHitCount() {
		return hitCount - doubleHitCount - trippleHitCount - homerunCount;
	}

	public double calcChodaritsu() {
		return (double) (calcSingleHitCount() + doubleHitCount * 2 + trippleHitCount * 3 + homerunCount * 4) / (double) calcDasuu();
	}

	public double calcOPS() {
		return calcShutsuruiritsu() + calcChodaritsu();
	}

	public String toString() {
		return name
			   + "," + tokuten
			   + "," + daten
			   + "," + dasekiCount
			   + "," + calcDasuu()
			   + "," + hitCount
			   + "," + doubleHitCount
			   + "," + trippleHitCount
			   + "," + homerunCount
			   + "," + fourBallCount
			   + "," + String.format("%.3f", calcDaritsu())
			   + "," + String.format("%.3f", calcShutsuruiritsu())
			   + "," + String.format("%.3f", calcChodaritsu())
			   + "," + String.format("%.3f", calcOPS())
				;
	}
}
