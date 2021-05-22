package shogi2.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShogiConst2 {

	public static final String BANMOJI = new String(
			  "　  　　９　８　７　６　５　４　３　２　１　　　  "
			+ "飛00　１□　□　□　□　□　□　□　□　□　　　  "
			+ "角00　２□　□　□　□　□　□　□　□　□　　　  "
			+ "金00　３□　□　□　□　□　□　□　□　□　　飛00"
			+ "銀00　４□　□　□　□　□　□　□　□　□　　角00"
			+ "桂00　５□　□　□　□　□　□　□　□　□　　金00"
			+ "京00　６□　□　□　□　□　□　□　□　□　　銀00"
			+ "歩00　７□　□　□　□　□　□　□　□　□　　桂00"
			+ "　  　８□　□　□　□　□　□　□　□　□　　京00"
			+ "　  　９□　□　□　□　□　□　□　□　□　　歩00"
		);

	public static final int TEBAN_SENTE = 1;
	public static final int TEBAN_GOTE  = -1;

	public static final String SENTE_FU     = "A";
	public static final String SENTE_FU_N   = "B";
	public static final String SENTE_KYO    = "C";
	public static final String SENTE_KYO_N  = "D";
	public static final String SENTE_KEI    = "E";
	public static final String SENTE_KEI_N  = "F";
	public static final String SENTE_GIN    = "G";
	public static final String SENTE_GIN_N  = "H";
	public static final String SENTE_KIN    = "I";
	public static final String SENTE_KAKU   = "J";
	public static final String SENTE_KAKU_N = "K";
	public static final String SENTE_SHA    = "L";
	public static final String SENTE_SHA_N  = "M";
	public static final String SENTE_OU     = "N";

	public static final String GOTE_FU      = "a";
	public static final String GOTE_FU_N    = "b";
	public static final String GOTE_KYO     = "c";
	public static final String GOTE_KYO_N   = "d";
	public static final String GOTE_KEI     = "e";
	public static final String GOTE_KEI_N   = "f";
	public static final String GOTE_GIN     = "g";
	public static final String GOTE_GIN_N   = "h";
	public static final String GOTE_KIN     = "i";
	public static final String GOTE_KAKU    = "j";
	public static final String GOTE_KAKU_N  = "k";
	public static final String GOTE_SHA     = "l";
	public static final String GOTE_SHA_N   = "m";
	public static final String GOTE_OU      = "n";

	public static final String KARA         = "o";

	public static final String[] SUU_ARRAY  = {"0","1","2","3","4","5","6","7","8","9",
			                                      "A","B","C","D","E","F","G","H","I"};

	public static final String[] MOJI_ARRAY  = {"飛","角","金","銀","桂","京","歩"};

	public static Map<String, String> HENKAN_MAP = new HashMap<>();

	public static Map<String, List<List<int[]>>> moveMap2 = new HashMap<>();

	public static Map<Integer, Integer> huIchiMap;

	public static Map<String, Integer> PUT_KOMA_ICHI_MAP = new HashMap<>();

	public static Map<String, Integer> GET_KOMA_ICHI_MAP = new HashMap<>();


//	public static Map<String, String> MAX_MIN_MAP = new HashMap<>();


	public ShogiConst2() {
		GET_KOMA_ICHI_MAP.put(SENTE_SHA, 88);
		GET_KOMA_ICHI_MAP.put(SENTE_KAKU,89);
		GET_KOMA_ICHI_MAP.put(SENTE_KIN, 90);
		GET_KOMA_ICHI_MAP.put(SENTE_GIN, 91);
		GET_KOMA_ICHI_MAP.put(SENTE_KEI, 92);
		GET_KOMA_ICHI_MAP.put(SENTE_KYO, 93);
		GET_KOMA_ICHI_MAP.put(SENTE_FU , 94);
		GET_KOMA_ICHI_MAP.put(GOTE_SHA , 81);
		GET_KOMA_ICHI_MAP.put(GOTE_KAKU, 82);
		GET_KOMA_ICHI_MAP.put(GOTE_KIN , 83);
		GET_KOMA_ICHI_MAP.put(GOTE_GIN , 84);
		GET_KOMA_ICHI_MAP.put(GOTE_KEI , 85);
		GET_KOMA_ICHI_MAP.put(GOTE_KYO , 86);
		GET_KOMA_ICHI_MAP.put(GOTE_FU  , 87);

		PUT_KOMA_ICHI_MAP.put(SENTE_SHA, 81);
		PUT_KOMA_ICHI_MAP.put(SENTE_KAKU,82);
		PUT_KOMA_ICHI_MAP.put(SENTE_KIN, 83);
		PUT_KOMA_ICHI_MAP.put(SENTE_GIN, 84);
		PUT_KOMA_ICHI_MAP.put(SENTE_KEI, 85);
		PUT_KOMA_ICHI_MAP.put(SENTE_KYO, 86);
		PUT_KOMA_ICHI_MAP.put(SENTE_FU , 87);
		PUT_KOMA_ICHI_MAP.put(GOTE_SHA , 88);
		PUT_KOMA_ICHI_MAP.put(GOTE_KAKU, 89);
		PUT_KOMA_ICHI_MAP.put(GOTE_KIN , 90);
		PUT_KOMA_ICHI_MAP.put(GOTE_GIN , 91);
		PUT_KOMA_ICHI_MAP.put(GOTE_KEI , 92);
		PUT_KOMA_ICHI_MAP.put(GOTE_KYO , 93);
		PUT_KOMA_ICHI_MAP.put(GOTE_FU  , 94);

		HENKAN_MAP.put("歩↑", SENTE_FU    );
		HENKAN_MAP.put("歩➚", SENTE_FU_N  );
		HENKAN_MAP.put("京↑", SENTE_KYO   );
		HENKAN_MAP.put("京➚", SENTE_KYO_N );
		HENKAN_MAP.put("桂↑", SENTE_KEI   );
		HENKAN_MAP.put("桂➚", SENTE_KEI_N );
		HENKAN_MAP.put("銀↑", SENTE_GIN   );
		HENKAN_MAP.put("銀➚", SENTE_GIN_N );
		HENKAN_MAP.put("金↑", SENTE_KIN   );
		HENKAN_MAP.put("角↑", SENTE_KAKU  );
		HENKAN_MAP.put("角➚", SENTE_KAKU_N);
		HENKAN_MAP.put("飛↑", SENTE_SHA   );
		HENKAN_MAP.put("飛➚", SENTE_SHA_N );
		HENKAN_MAP.put("王↑", SENTE_OU    );

		HENKAN_MAP.put("歩↓", GOTE_FU    );
		HENKAN_MAP.put("歩➘", GOTE_FU_N  );
		HENKAN_MAP.put("京↓", GOTE_KYO   );
		HENKAN_MAP.put("京➘", GOTE_KYO_N );
		HENKAN_MAP.put("桂↓", GOTE_KEI   );
		HENKAN_MAP.put("桂➘", GOTE_KEI_N );
		HENKAN_MAP.put("銀↓", GOTE_GIN   );
		HENKAN_MAP.put("銀➘", GOTE_GIN_N );
		HENKAN_MAP.put("金↓", GOTE_KIN   );
		HENKAN_MAP.put("角↓", GOTE_KAKU  );
		HENKAN_MAP.put("角➘", GOTE_KAKU_N);
		HENKAN_MAP.put("飛↓", GOTE_SHA   );
		HENKAN_MAP.put("飛➘", GOTE_SHA_N );
		HENKAN_MAP.put("王↓", GOTE_OU    );
		HENKAN_MAP.put("□　", KARA       );

		HENKAN_MAP.put(SENTE_FU    ,"歩↑");
		HENKAN_MAP.put(SENTE_FU_N  ,"歩➚");
		HENKAN_MAP.put(SENTE_KYO   ,"京↑");
		HENKAN_MAP.put(SENTE_KYO_N ,"京➚");
		HENKAN_MAP.put(SENTE_KEI   ,"桂↑");
		HENKAN_MAP.put(SENTE_KEI_N ,"桂➚");
		HENKAN_MAP.put(SENTE_GIN   ,"銀↑");
		HENKAN_MAP.put(SENTE_GIN_N ,"銀➚");
		HENKAN_MAP.put(SENTE_KIN   ,"金↑");
		HENKAN_MAP.put(SENTE_KAKU  ,"角↑");
		HENKAN_MAP.put(SENTE_KAKU_N,"角➚");
		HENKAN_MAP.put(SENTE_SHA   ,"飛↑");
		HENKAN_MAP.put(SENTE_SHA_N ,"飛➚");
		HENKAN_MAP.put(SENTE_OU    ,"王↑");

		HENKAN_MAP.put(GOTE_FU     ,"歩↓");
		HENKAN_MAP.put(GOTE_FU_N   ,"歩➘");
		HENKAN_MAP.put(GOTE_KYO    ,"京↓");
		HENKAN_MAP.put(GOTE_KYO_N  ,"京➘");
		HENKAN_MAP.put(GOTE_KEI    ,"桂↓");
		HENKAN_MAP.put(GOTE_KEI_N  ,"桂➘");
		HENKAN_MAP.put(GOTE_GIN    ,"銀↓");
		HENKAN_MAP.put(GOTE_GIN_N  ,"銀➘");
		HENKAN_MAP.put(GOTE_KIN    ,"金↓");
		HENKAN_MAP.put(GOTE_KAKU   ,"角↓");
		HENKAN_MAP.put(GOTE_KAKU_N ,"角➘");
		HENKAN_MAP.put(GOTE_SHA    ,"飛↓");
		HENKAN_MAP.put(GOTE_SHA_N  ,"飛➘");
		HENKAN_MAP.put(GOTE_OU     ,"王↓");
		HENKAN_MAP.put(KARA        ,"□　");

		HENKAN_MAP.put("00", "0");
		HENKAN_MAP.put("01", "1");
		HENKAN_MAP.put("02", "2");
		HENKAN_MAP.put("03", "3");
		HENKAN_MAP.put("04", "4");
		HENKAN_MAP.put("05", "5");
		HENKAN_MAP.put("06", "6");
		HENKAN_MAP.put("07", "7");
		HENKAN_MAP.put("08", "8");
		HENKAN_MAP.put("09", "9");
		HENKAN_MAP.put("10", "R");
		HENKAN_MAP.put("11", "S");
		HENKAN_MAP.put("12", "T");
		HENKAN_MAP.put("13", "U");
		HENKAN_MAP.put("14", "V");
		HENKAN_MAP.put("15", "W");
		HENKAN_MAP.put("16", "X");
		HENKAN_MAP.put("17", "Y");
		HENKAN_MAP.put("18", "Z");

		HENKAN_MAP.put("0", "00");
		HENKAN_MAP.put("1", "01");
		HENKAN_MAP.put("2", "02");
		HENKAN_MAP.put("3", "03");
		HENKAN_MAP.put("4", "04");
		HENKAN_MAP.put("5", "05");
		HENKAN_MAP.put("6", "06");
		HENKAN_MAP.put("7", "07");
		HENKAN_MAP.put("8", "08");
		HENKAN_MAP.put("9", "09");
		HENKAN_MAP.put("R", "10");
		HENKAN_MAP.put("S", "11");
		HENKAN_MAP.put("T", "12");
		HENKAN_MAP.put("U", "13");
		HENKAN_MAP.put("V", "14");
		HENKAN_MAP.put("W", "15");
		HENKAN_MAP.put("X", "16");
		HENKAN_MAP.put("Y", "17");
		HENKAN_MAP.put("Z", "18");

		HENKAN_MAP.put("81", SENTE_SHA);
		HENKAN_MAP.put("82", SENTE_KAKU);
		HENKAN_MAP.put("83", SENTE_KIN);
		HENKAN_MAP.put("84", SENTE_GIN);
		HENKAN_MAP.put("85", SENTE_KEI);
		HENKAN_MAP.put("86", SENTE_KYO);
		HENKAN_MAP.put("87", SENTE_FU);
		HENKAN_MAP.put("88", GOTE_SHA);
		HENKAN_MAP.put("89", GOTE_KAKU);
		HENKAN_MAP.put("90", GOTE_KIN);
		HENKAN_MAP.put("91", GOTE_GIN);
		HENKAN_MAP.put("92", GOTE_KEI);
		HENKAN_MAP.put("93", GOTE_KYO);
		HENKAN_MAP.put("94", GOTE_FU);

		//---------------------------------------------------------
		moveMap2.put(GOTE_OU    , setList2("0,1@0,-1@-1,0@1,0@1,1@-1,-1@-1,1@1,-1"));
		moveMap2.put(GOTE_SHA   , setList2("-1,0:-2,0:-3,0:-4,0:-5,0:-6,0:-7,0:-8,0@1,0:2,0:3,0:4,0:5,0:6,0:7,0:8,0@0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,8@0,-1:0,-2:0,-3:0,-4:0,-5:0,-6:0,-7:0,-8"));
		moveMap2.put(GOTE_SHA_N , setList2("-1,0:-2,0:-3,0:-4,0:-5,0:-6,0:-7,0:-8,0@1,0:2,0:3,0:4,0:5,0:6,0:7,0:8,0@0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,8@0,-1:0,-2:0,-3:0,-4:0,-5:0,-6:0,-7:0,-8@-1,-1@-1,1@1,-1@1,1"));
		moveMap2.put(GOTE_KAKU  , setList2("1,1:2,2:3,3:4,4:5,5:6,6:7,7:8,8@1,-1:2,-2:3,-3:4,-4:5,-5:6,-6:7,-7:8,-8@-1,1:-2,2:-3,3:-4,4:-5,5:-6,6:-7,7:-8,8@-1,-1:-2,-2:-3,-3:-4,-4:-5,-5:-6,-6:-7,-7:-8,-8"));
		moveMap2.put(GOTE_KAKU_N, setList2("1,1:2,2:3,3:4,4:5,5:6,6:7,7:8,8@1,-1:2,-2:3,-3:4,-4:5,-5:6,-6:7,-7:8,-8@-1,1:-2,2:-3,3:-4,4:-5,5:-6,6:-7,7:-8,8@-1,-1:-2,-2:-3,-3:-4,-4:-5,-5:-6,-6:-7,-7:-8,-8@-1,0@1,0@0,1@0,-1"));
		moveMap2.put(GOTE_KIN   , setList2("0,1@0,-1@-1,0@1,0@1,1@-1,1"));
		moveMap2.put(GOTE_GIN   , setList2("0,1@0,-1@1,1@-1,-1@-1,1@1,-1"));
		moveMap2.put(GOTE_GIN_N , setList2("0,1@0,-1@-1,0@1,0@1,1@-1,1"));
		moveMap2.put(GOTE_KEI   , setList2("-1,2@1,2"));
		moveMap2.put(GOTE_KEI_N , setList2("0,1@0,-1@-1,0@1,0@1,1@-1,1"));
		moveMap2.put(GOTE_KYO   , setList2("0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,8"));
		moveMap2.put(GOTE_KYO_N , setList2("0,1@0,-1@-1,0@1,0@1,1@-1,1"));
		moveMap2.put(GOTE_FU    , setList2("0,1"));
		moveMap2.put(GOTE_FU_N  , setList2("0,1@0,-1@-1,0@1,0@1,1@-1,1"));

		moveMap2.put(SENTE_OU   ,  setList2("0,1@0,-1@-1,0@1,0@1,1@-1,-1@-1,1@1,-1"));
		moveMap2.put(SENTE_SHA  ,  setList2("-1,0:-2,0:-3,0:-4,0:-5,0:-6,0:-7,0:-8,0@1,0:2,0:3,0:4,0:5,0:6,0:7,0:8,0@0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,8@0,-1:0,-2:0,-3:0,-4:0,-5:0,-6:0,-7:0,-8"));
		moveMap2.put(SENTE_SHA_N,  setList2("-1,0:-2,0:-3,0:-4,0:-5,0:-6,0:-7,0:-8,0@1,0:2,0:3,0:4,0:5,0:6,0:7,0:8,0@0,1:0,2:0,3:0,4:0,5:0,6:0,7:0,8@0,-1:0,-2:0,-3:0,-4:0,-5:0,-6:0,-7:0,-8@-1,-1@-1,1@1,-1@1,1"));
		moveMap2.put(SENTE_KAKU ,  setList2("1,1:2,2:3,3:4,4:5,5:6,6:7,7:8,8@1,-1:2,-2:3,-3:4,-4:5,-5:6,-6:7,-7:8,-8@-1,1:-2,2:-3,3:-4,4:-5,5:-6,6:-7,7:-8,8@-1,-1:-2,-2:-3,-3:-4,-4:-5,-5:-6,-6:-7,-7:-8,-8"));
		moveMap2.put(SENTE_KAKU_N, setList2("1,1:2,2:3,3:4,4:5,5:6,6:7,7:8,8@1,-1:2,-2:3,-3:4,-4:5,-5:6,-6:7,-7:8,-8@-1,1:-2,2:-3,3:-4,4:-5,5:-6,6:-7,7:-8,8@-1,-1:-2,-2:-3,-3:-4,-4:-5,-5:-6,-6:-7,-7:-8,-8@-1,0@1,0@0,1@0,-1"));
		moveMap2.put(SENTE_KIN  , setList2("0,1@0,-1@-1,0@1,0@-1,-1@1,-1"));
		moveMap2.put(SENTE_GIN  , setList2("0,1@0,-1@1,1@-1,-1@-1,1@1,-1"));
		moveMap2.put(SENTE_GIN_N, setList2("0,1@0,-1@-1,0@1,0@-1,-1@1,-1"));
		moveMap2.put(SENTE_KEI  , setList2("-1,-2@1,-2"));
		moveMap2.put(SENTE_KEI_N, setList2("0,1@0,-1@-1,0@1,0@-1,-1@1,-1"));
		moveMap2.put(SENTE_KYO  , setList2("0,-1:0,-2:0,-3:0,-4:0,-5:0,-6:0,-7:0,-8"));
		moveMap2.put(SENTE_KYO_N, setList2("0,1@0,-1@-1,0@1,0@-1,-1@1,-1"));
		moveMap2.put(SENTE_FU   , setList2("0,-1"));
		moveMap2.put(SENTE_FU_N , setList2("0,1@0,-1@-1,0@1,0@-1,-1@1,-1"));

		//---------------------------------------------------------------------------------------------------
		huIchiMap = getHuIchiMap();
	}

	/**
	 *
	 * @param moji
	 * @return
	 */
	private List<List<int[]>> setList2(String moji) {
		List<List<int[]>> list = new ArrayList<>();
		String []array = moji.split("@");
		for(String data: array) {
			String []array2 = data.split(":");
			List<int[]> list2 = new ArrayList<>();
			for(String data2: array2) {
				String []array3 = data2.split(",");
				int []intData = {Integer.parseInt(array3[0]), Integer.parseInt(array3[1])};
				list2.add(intData);

			}
			list.add(list2);
		}
		return list;
	}

	private Map<Integer, Integer> getHuIchiMap(){
		Map<Integer, Integer> rtnMap = new HashMap<>();

		for(int i = 0; i < 9; i ++) {
			rtnMap.put(i     , i);
			rtnMap.put(i +  9, i);
			rtnMap.put(i + 18, i);
			rtnMap.put(i + 27, i);
			rtnMap.put(i + 36, i);
			rtnMap.put(i + 45, i);
			rtnMap.put(i + 54, i);
			rtnMap.put(i + 63, i);
			rtnMap.put(i + 72, i);
		}

		return rtnMap;
	}
}
