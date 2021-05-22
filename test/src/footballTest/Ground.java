package footballTest;

import java.util.ArrayList;
import java.util.List;

public class Ground {
	private List<List<GroundInfo>> xList;

	public Ground() {
		int xLength = 1000; //グラウンドの横幅
		int yLength = 1000; //グラウンドの縦幅

		ballIchiX = xLength / 2;
		ballIchiY = yLength / 2;

		xList = new ArrayList<>();

		for(int x = 0; x < xLength; x++) {

			List<GroundInfo> yList = new ArrayList<>();

			for(int y = 0; y < yLength; y++) {
				GroundInfo groundInfo = new GroundInfo();
				groundInfo.setObj(FootBallConst.KARA);
				yList.add(groundInfo);
				yList.add(groundInfo);
			}
			xList.add(yList);
		}
	}

	/** ボール座標X */
	private int ballIchiX;

	/** ボール座標X */
	private int ballIchiY;

	public int getBallIchiX() {
		return ballIchiX;
	}

	public void setBallIchiX(int ballIchiX) {
		this.ballIchiX = ballIchiX;
	}

	public int getBallIchiY() {
		return ballIchiY;
	}

	public void setBallIchiY(int ballIchiY) {
		this.ballIchiY = ballIchiY;
	}

	public List<List<GroundInfo>> getxList() {
		return xList;
	}

	public void setxList(List<List<GroundInfo>> xList) {
		this.xList = xList;
	}
}
