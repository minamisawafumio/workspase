package test;

import jp.co.fm.businessLogic.common.DateUtil;
import shogi.bean.Node;
import shogi2.common.ShogiConst2;

public class TestCommon {
	private static int arrayPointa = 0;
//	private static int array[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
//	private static int array[] = {14,13,12,11,10,9,8,7,6,5,4,3,2,1};
//	private static int array[] = {1,2,3,11,8,12,7,8,14,13,15,12,13,14};
	private static int array[] = {10,9,8,10,11,10,7,8,9,1,1,1,1,1};

	private static int nodeNoPointa = 0;
	private static int arrayNodeNo[] = {101,102,103,104,105,106,107,108,109,110,111,112,113,114};


	public int makePoint() {
		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String pointSt = comDateTime.substring(comDateTime.length() - 3, comDateTime.length());

		return Integer.parseInt(pointSt);
	}


	public static int makeArrayPoint() {
		int rtnInt = array[arrayPointa];

		arrayPointa ++;

		return rtnInt;
	}


	public static int getNodeNo() {
		int rtnInt = arrayNodeNo[nodeNoPointa];

		nodeNoPointa ++;

		return rtnInt;
	}

	/**
	 *
	 * @param depth
	 * @param teban
	 * @return
	 */
	public static Node makeNode(int depth, int teban) {
		Node node3 = new Node();

		node3.setDepth(depth);

		node3.setTeban(teban);

		if(teban == ShogiConst2.TEBAN_SENTE) {
			node3.setPoint(Integer.MIN_VALUE);
		}else {
			node3.setPoint(Integer.MAX_VALUE);
		}

		return node3;
	}
}
