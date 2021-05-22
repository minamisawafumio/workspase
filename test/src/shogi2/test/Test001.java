package shogi2.test;

import org.junit.Test;

import jp.co.fm.businessLogic.common.DateUtil;
import shogi2.common.ShogiCommon2;
import shogi2.common.ShogiConst2;

public class Test001 {

    private DateUtil dt = DateUtil.getInstance();

	@Test
	public void test_008() {
		new ShogiConst2();

		String sb = new String(
			  "　  　　９　８　７　６　５　４　３　２　１　　　  "
			+ "飛11　１京↓桂↓銀↓金↓王↓金↓銀↓桂↓京↓　　  "
			+ "角12　２□　飛↓□　□　□　□　□　角↓□　　　  "
			+ "金13　３歩↓歩↓歩↓歩↓歩↓歩↓歩↓歩↓歩↓　飛17"
			+ "銀14　４□　□　□　□　□　□　□　□　□　　角16"
			+ "桂15　５□　□　飛↓□　□　□　□　□　□　　金15"
			+ "京16　６□　□　□　□　□　□　□　□　□　　銀14"
			+ "歩17　７歩↑歩↑歩↑歩↑歩↑歩↑歩↑歩↑歩↑　桂13"
			+ "　  　８□　角↑□　□　□　□　□　飛↑□　　京12"
			+ "　  　９京↑桂↑銀↑金↑王↑金↑銀↑桂↑京↑　歩11"
		);

		String shogiString = ShogiCommon2.makePrintDataToShogiString(sb);

		ShogiCommon2.printIdosaki(shogiString, 1, 0);

	}

	@Test
	public void test_007() {

		String startYyyyMMddHHmmssSSS = dt.getComDateTime(5); //"20190203105910238"

		int kkk[] = {0,9,9,0};

		double k = 0;



		StringBuffer hhh = new StringBuffer("00000000001111111111000000000011111111110000000000111111111100000000001111111111000000000011111");
		String ooo = hhh.toString();


		for(int i = 0; i < 9999; i++) {
			for(int j = 0; j < 51111; j++) {

					String hhhhh = new String(ooo);

	//				StringBuffer hhhhh = new StringBuffer(ooo);

	//				hhh.replace(12, 13, "A");
	//				hhh.replace(13, 14, "B");
	//				hhh.replace(14, 15, "C");
				}
			}
	}

	@Test
	public void test_006() {

		new ShogiConst2();

		String sb = new String(
			  "　  　　９　８　７　６　５　４　３　２　１　　　  "
			+ "飛11　１京↓桂↓銀↓金↓王↓金↓銀↓桂↓京↓　　  "
			+ "角12　２□　飛↓□　□　□　□　□　角↓□　　　  "
			+ "金13　３歩↓歩↓歩↓歩↓歩↓歩↓歩↓歩↓歩↓　飛17"
			+ "銀14　４□　□　□　□　□　□　□　□　□　　角16"
			+ "桂15　５□　□　飛↓□　□　□　□　□　□　　金15"
			+ "京16　６□　□　□　□　□　□　□　□　□　　銀14"
			+ "歩17　７歩↑歩↑歩↑歩↑歩↑歩↑歩↑歩↑歩↑　桂13"
			+ "　  　８□　角↑□　□　□　□　□　飛↑□　　京12"
			+ "　  　９京↑桂↑銀↑金↑王↑金↑銀↑桂↑京↑　歩11"
		);

		String shogiString = ShogiCommon2.makePrintDataToShogiString(sb);
		System.out.println(shogiString.toString());
		System.out.println("-----------------------------------------------");
		ShogiCommon2.printFromShogiString(shogiString);

		String hhh2 = ShogiCommon2.komaMove(shogiString, 7, 5, 7, 7);

		System.out.println("----------------------------------------------------");
		ShogiCommon2.printFromShogiString(hhh2);


//		String hhh3 = ShogiCommon2.komaMove(shogiString, 1, 0, 6, 5);
		String hhh3 = ShogiCommon2.komaMove(shogiString, 5, 1, 6, 2);

		System.out.println("****************************************************");
		ShogiCommon2.printFromShogiString(hhh3);
	}

//	@Test
//	public void test_005() {
//
//		new ShogiConst2();
//
//		int []uu = ShogiCommon2.getXy(67);
//
//		int ppp = ShogiCommon2.getPoint(uu[0], uu[1]);
//
//		StringBuffer sb = new StringBuffer(
//			  "　  　　９　８　７　６　５　４　３　２　１　　　  "
//			+ "飛11　１京↓桂↓銀↓金↓王↓金↓銀↓桂↓京↓　　  "
//			+ "角12　２□　飛↓□　□　□　□　□　角↓□　　　  "
//			+ "金13　３歩↓歩↓歩↓歩↓歩↓歩↓歩↓歩↓歩↓　飛17"
//			+ "銀14　４□　□　□　□　□　□　□　□　□　　角16"
//			+ "桂15　５□　□　□　□　□　□　□　□　□　　金15"
//			+ "京16　６□　□　□　□　□　□　□　□　□　　銀14"
//			+ "歩17　７歩↑歩↑歩↑歩↑歩↑歩↑歩↑歩↑歩↑　桂13"
//			+ "　  　８□　角↑□　□　□　□　□　飛↑□　　京12"
//			+ "　  　９京↑桂↑銀↑金↑王↑金↑銀↑桂↑京↑　歩11"
//		);
//
//		ShogiCommon2.printSgogiTaikyoku(sb);
//
//
//
//		StringBuffer shogiString = ShogiCommon2.makePrintDataToShogiString(sb);
//		System.out.println(shogiString.toString());
//		System.out.println("-----------------------------------------------");
//
//
//		System.out.println("-----------------------------------------------");
//
//		ShogiCommon2.printFromShogiString(shogiString);
//
//
//		ShogiCommon2.printIdosaki(shogiString, 8, 2);
//
//	}
}
