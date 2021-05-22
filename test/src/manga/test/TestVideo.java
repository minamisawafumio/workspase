package manga.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import jp.co.fm.businessLogic.common.BeanUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.table.DbToolMapper;
import jp.co.fm.businessLogic.table.T_1010;
import manga.common.VideoSystemCommon;

public class TestVideo {

	private DbUtil du = DbUtil.getInstance();

	private BeanUtil bu = BeanUtil.getInstance();

	private FileUtil fu = FileUtil.getInstance();

	private VideoSystemCommon vsc = VideoSystemCommon.getinstance();

	@Test
    public void testMapToJsonToMap2() {
		List<String[]> list = vsc.makeVideoInfoList();



	}

	/**
	 * ビデオ検索キー作成
	 */
	@Test
	public void makeT1010() {

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		List<String[]> list = vsc.makeVideoInfoList();

		String[] PM_KEY_T_1010 = {"corpCd", "delFlg", "recCd", "startYmd", "item01"};

		Map<String, Object> map = new HashMap<>();


		Map videoMap = makeVideoFileMap();

		T_1010 t_1010 = null;

		try {
			map.put("corpCd"	, "01"					);
			map.put("delFlg"	, "0"					);
			map.put("recCd"		, "016"					);
			map.put("startYmd"	, "00010101"			);
			map.put("endYmd"	, "99991231"			);
			map.put("recKbn"	, "4"					);

			for(String []array: list) {
				map.put("item01"	, array[0]);

				String name = (String) videoMap.get(array[0]);

				if(name == null) {
					map.put("item02"	, array[1]);
				} else {
					map.put("item02"	, name);
				}

				t_1010 = (T_1010) BeanUtil.getInstance().makeBean(new T_1010(), map);
				du.upsert(sqlSession, t_1010, PM_KEY_T_1010);
			}

			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}


	@Test
	/**
	 * パスをコンソールに表示する
	 */
	public void test00011() {

		String inPath = "C:/pleiades/pleiades-2019-12-java-win-64bit-jre_20200322/workspace/skdb/WebContent/resources/video/data/";

		List<String> pathFilelist = fu.getPathFileNameList(inPath);

		for(String pathName: pathFilelist) {

			String path =  pathName.replace("\\", "/");

			System.out.println(path.replaceAll(inPath, ""));
		}
	}

	@Test
	/**
	 * パスをコンソールに表示する（Mapに無いもののみ）
	 */
	public void test00012() {

		String inPath = "C:/pleiades/pleiades-2019-12-java-win-64bit-jre_20200322/workspace/skdb/WebContent/resources/video/data/";

		List<String> pathFilelist = fu.getPathFileNameList(inPath);

		Map map = makeVideoFileMap();

		for(String pathName: pathFilelist) {

			String path =  pathName.replace("\\", "/");

			String pathName2 = path.replaceAll(inPath, "");

			if(map.get(pathName2) == null) {
				System.out.println(pathName2);
			}
		}
	}

	@Test
	public void test_Video() {

		Map<String, Object> map = new HashMap<>();

		map.put("corpCd", "01");
		map.put("delFlg", "0");
		map.put("recCd", "016");

		T_1010 t_0160 = (T_1010) bu.makeBean(new T_1010(), map);

    	String []primaryKey = {"corpCd","delFlg", "recCd"};

    	String sql = du.getSelectSql(t_0160, primaryKey);

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

        DbToolMapper db = sqlSession.getMapper(DbToolMapper.class);

        Map videoFileMap = makeVideoFileMap();

        T_1010 t_1010 = null;
        Map<String, Object> m = null;

        try {
            List<Map<String, Object>> rset = db.select(sql);

            for(int i = 0; i < rset.size(); i ++) {
            	m = rset.get(i);
            	t_1010 = (T_1010) du.mekeObject(new T_1010(), m);
            	String name = (String) videoFileMap.get(t_1010.getItem01());
            	t_1010.setItem02(name);
            	du.upsert(sqlSession, t_1010, SystemConst.PM_KEY_T_1010);
            }

            sqlSession.commit();
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	sqlSession.close();
        }
	}

	/**
	 * ビデオタイトル変換マップ
	 * @return
	 */
	public Map makeVideoFileMap() {
		Map rtnMap = new HashMap();
		rtnMap.put("2/1080P_4000K_264389812.mp4"                                                    , "あ8");
		rtnMap.put("2/480P_600K_233077901.mp4"                                                      , "あ62");
		rtnMap.put("2/480P_600K_259937222.mp4"                                                      , "あ65");
		rtnMap.put("2/480P_600K_262335552.mp4"                                                      , "あ66");
		rtnMap.put("2/720P_1500K_269160431.mp4"                                                     , "あ109");
		rtnMap.put("2/720P_1500K_272660411.mp4"                                                     , "あ113");
		rtnMap.put("2/Erika_Momotani 032.mp4"                                                       , "あ183");
		rtnMap.put("2/岡崎えみりJav .mp4"                                                           , "岡崎えみり");
		rtnMap.put("2/佐山愛31.mp4"                                                                 , "あ336");
		rtnMap.put("2/初音みのり 52.mp4"                                                            , "初音みのり");
		rtnMap.put("2/小倉ゆず02.mp4"                                                               , "小倉ゆず");
		rtnMap.put("2/本田莉子21.mp4"                                                               , "本田莉子21");
		rtnMap.put("2/里美ゆりあ912.mp4"                                                            , "里美ゆりあ9");
		rtnMap.put("F/1080P_4000K_188459311.mp4"                                                    , "あ1");
		rtnMap.put("F/1080P_4000K_191214821.mp4"                                                    , "あ2");
		rtnMap.put("F/1080P_4000K_239602001.mp4"                                                    , "あ3");
		rtnMap.put("F/1229733.mp4"                                                                  , "あ10");
		rtnMap.put("F/1474602.mp4"                                                                  , "あ11");
		rtnMap.put("F/1529649_hq.mp4"                                                               , "あ12");
		rtnMap.put("F/171020_2108_480P_600K_66130711.mp4"                                           , "あ14");
		rtnMap.put("F/180419_2107_480P_600K_160820822.mp4"                                          , "あ15");
		rtnMap.put("F/1970983.mp4"                                                                  , "あ21");
		rtnMap.put("F/2032366_hq.mp4"                                                               , "あ22");
		rtnMap.put("F/2296239.mp4"                                                                  , "あ24");
		rtnMap.put("F/240p.h264.mp4"                                                                , "あ25");
		rtnMap.put("F/2494860.mp4"                                                                  , "あ27");
		rtnMap.put("F/2596892_hq.mp4"                                                               , "あ28");
		rtnMap.put("F/2655947_hd.mp4"                                                               , "あ29");
		rtnMap.put("F/2861350.mp4"                                                                  , "あ30");
		rtnMap.put("F/3066614_hd.mp4"                                                               , "あ33");
		rtnMap.put("F/3146323_hd.mp4"                                                               , "あ34");
		rtnMap.put("F/3395266_hd.mp4"                                                               , "あ35");
		rtnMap.put("F/3417755.mp4"                                                                  , "あ37");
		rtnMap.put("F/3476113_hd.mp4"                                                               , "あ38");
		rtnMap.put("F/3522094.mp4"                                                                  , "あ39");
		rtnMap.put("F/3776491_hd.mp4"                                                               , "あ41");
		rtnMap.put("F/3778251.mp4"                                                                  , "あ42");
		rtnMap.put("F/3855109_hq.mp4"                                                               , "あ43");
		rtnMap.put("F/3935531_hq.mp4"                                                               , "あ44");
		rtnMap.put("F/3936494.mp4"                                                                  , "あ45");
		rtnMap.put("F/4128847.mp4"                                                                  , "あ48");
		rtnMap.put("F/42282_hq.mp4"                                                                 , "あ49");
		rtnMap.put("F/4347355.mp4"                                                                  , "あ54");
		rtnMap.put("F/480P_576K_3129161[1].mp4"                                                     , "あ57");
		rtnMap.put("F/480p_600k_703042.mp4"                                                         , "あ71");
		rtnMap.put("F/4853246_480p.mp4"                                                             , "あ72");
		rtnMap.put("F/48982b8547 CS P.flv"                                                          , "あ74");
		rtnMap.put("F/4977660_hd.mp4"                                                               , "あ301");
		rtnMap.put("F/5484413.mp4"                                                                  , "あ75");
		rtnMap.put("F/5742085_hd.mp4"                                                               , "あ78");
		rtnMap.put("F/5821476.mp4"                                                                  , "あ79");
		rtnMap.put("F/5963422_hd.mp4"                                                               , "あ81");
		rtnMap.put("F/5991136_hd.mp4"                                                               , "あ82");
		rtnMap.put("F/6401487_hd.mp4"                                                               , "あ84");
		rtnMap.put("F/654855_hq.mp4"                                                                , "あ85");
		rtnMap.put("F/6588804_hd.mp4"                                                               , "あ86");
		rtnMap.put("F/6618422_hd.mp4"                                                               , "あ87");
		rtnMap.put("F/6761625_hd.mp4"                                                               , "あ88");
		rtnMap.put("F/6778408_hq.mp4"                                                               , "あ89");
		rtnMap.put("F/7048461_hd.mp4"                                                               , "あ90");
		rtnMap.put("F/720p.h264(9).mp4"                                                             , "あ97");
		rtnMap.put("F/720p.h264.mp4"                                                                , "あ92");
		rtnMap.put("F/720p.h264-3.mp4"                                                              , "あ94");
		rtnMap.put("F/720p.ha264.mp4"                                                               , "あ98");
		rtnMap.put("F/720P_1500K_123357421.mp4"                                                     , "あ99");
		rtnMap.put("F/720P_1500K_133759051.mp4"                                                     , "あ100");
		rtnMap.put("F/720P_1500K_141158822.mp4"                                                     , "あ101");
		rtnMap.put("F/720P_1500K_181969541.mp4"                                                     , "あ102");
		rtnMap.put("F/720P_1500K_230167042.mp4"                                                     , "あ105");
		rtnMap.put("F/720P_1500K_241877041.mp4"                                                     , "あ107");
		rtnMap.put("F/720P_1500K_255087311.mp4"                                                     , "あ108");
		rtnMap.put("F/720P_1500K_272526271.mp4"                                                     , "あ111");
		rtnMap.put("F/720P_1500K_8893981.mp4"                                                       , "あ115");
		rtnMap.put("F/720P_4000K_251184922.mp4"                                                     , "あ116");
		rtnMap.put("F/720Pもあちゃん181.mp4"                                                        , "もあちゃん");
		rtnMap.put("F/7529869_hd.mp4"                                                               , "あ119");
		rtnMap.put("F/7545283_hd.mp4"                                                               , "あ120");
		rtnMap.put("F/7642060_hd.mp4"                                                               , "あ121");
		rtnMap.put("F/7851517_480p(1).mp4"                                                          , "あ122");
		rtnMap.put("F/845217.mp4"                                                                   , "あ124");
		rtnMap.put("F/865877.mp4"                                                                   , "あ125");
		rtnMap.put("F/A_20902[1].mp4"                                                               , "あ126");
		rtnMap.put("F/A_29_nice_big_tit93c0.mp4"                                                    , "あ127");
		rtnMap.put("F/A_HJ_1].mp4"                                                                  , "あ128");
		rtnMap.put("F/A_Kendra Sunderland.mp4"                                                      , "あ129");
		rtnMap.put("F/A00[2].mp4"                                                                   , "あ130");
		rtnMap.put("F/A00p.h264(7).mp4"                                                             , "あ131");
		rtnMap.put("F/A00p.h264(8).mp4"                                                             , "あ132");
		rtnMap.put("F/Alex_Chance HJ.mp4"                                                           , "Alex_Chance 133");
		rtnMap.put("F/Alexis_Adams.mp4"                                                             , "Alexis_Adams 134");
		rtnMap.put("F/Alice_Romain.mp4"                                                             , "Alice_Romain 135");
		rtnMap.put("F/Alison_Tylere.mp4"                                                            , "Alison_Tylere 136");
		rtnMap.put("F/Allison_Evers/Allison_Evers0.mp4"                                             , "あ137");
		rtnMap.put("F/Allison_Evers/Allison_Evers59.mp4"                                            , "あ138");
		rtnMap.put("F/Angel_DeLuca.mp4"                                                             , "あ140");
		rtnMap.put("F/Aoi_tsukasa_Uncensored_Leaked_551.mp4"                                        , "Aoi_tsukasa_Uncensored_Leaked_551 141");
		rtnMap.put("F/B_264.mp4"                                                                    , "あ151");
		rtnMap.put("F/B_284.mp4"                                                                    , "あ152");
		rtnMap.put("F/B_5292_hd.mp4"                                                                , "あ153");
		rtnMap.put("F/B_53.mp4"                                                                     , "あ154");
		rtnMap.put("F/B_53011_hd.mp4"                                                               , "あ155");
		rtnMap.put("F/B_560_hd.mp4"                                                                 , "あ156");
		rtnMap.put("F/B_7f.mp4"                                                                     , "あ157");
		rtnMap.put("F/B_Eva_Notty.mp4"                                                              , "Eva_Notty 158");
		rtnMap.put("F/B_Eva_Notty3_hq.mp4"                                                          , "Eva_Notty 159");
		rtnMap.put("F/B_Eva_Nottyhq.mp4"                                                            , "Eva_Notty 160");
		rtnMap.put("F/B_Eva_Nottyq.mp4"                                                             , "Eva_Notty 161");
		rtnMap.put("F/B_N_3320.mp4"                                                                 , "あ162");
		rtnMap.put("F/B_T_395.mp4"                                                                  , "あ163");
		rtnMap.put("F/B11.mp4"                                                                      , "あ164");
		rtnMap.put("F/B51_hd.mp4"                                                                   , "あ165");
		rtnMap.put("F/B998_hd.mp4"                                                                  , "あ166");
		rtnMap.put("F/BC_45966b153bb70650e57429a.mp4"                                               , "あ168");
		rtnMap.put("F/BC_a859.mp4"                                                                  , "あ169");
		rtnMap.put("F/Brooklyn_Chase/Brooklyn_Chase 2.mp4"                                          , "Brooklyn_Chase170");
		rtnMap.put("F/Brooklyn_Chase/Brooklyn_Chase 5_hd.mp4"                                       , "Brooklyn_Chase171");
		rtnMap.put("F/Brooklyn_Chase/Brooklyn_Chase２.mp4"                                          , "Brooklyn_Chase172");
		rtnMap.put("F/Brooklyn_Chase/mandigo_07481.mp4"                                             , "Brooklyn_Chase236");
		rtnMap.put("F/BS_hq.mp4"                                                                    , "あ173");
		rtnMap.put("F/BT  d_her_amazing_tits_by_eliman.mp4"                                         , "BT 174");
		rtnMap.put("F/BT 1.mp4"                                                                     , "BT 175");
		rtnMap.put("F/BT 4_hd.mp4"                                                                  , "BT 176");
		rtnMap.put("F/BT 5_hd(1).mp4"                                                               , "BT 177");
		rtnMap.put("F/F 011.mp4"                                                                    , "あ184");
		rtnMap.put("F/F 0p.h264-1.mp4"                                                              , "あ185");
		rtnMap.put("F/F 0p.h264-4.mp4"                                                              , "あ186");
		rtnMap.put("F/F 21.mp4"                                                                     , "あ187");
		rtnMap.put("F/F 264-7.mp4"                                                                  , "あ188");
		rtnMap.put("F/F 37698041.mp4"                                                               , "あ189");
		rtnMap.put("F/F 612.mp4"                                                                    , "あ190");
		rtnMap.put("F/f 691.mp4"                                                                    , "あ191");
		rtnMap.put("F/F 787_hq.mp4"                                                                 , "あ192");
		rtnMap.put("F/F 989_hq.mp4"                                                                 , "あ193");
		rtnMap.put("F/F301_hq.mp4"                                                                  , "あ194");
		rtnMap.put("F/FT 241.mp4"                                                                   , "FT 195");
		rtnMap.put("F/FT 331.mp4"                                                                   , "FT 196");
		rtnMap.put("F/FT 4611.mp4"                                                                  , "FT 197");
		rtnMap.put("F/FT 54837571.mp4"                                                              , "FT 198");
		rtnMap.put("F/FT 60522.mp4"                                                                 , "FT 199");
		rtnMap.put("F/FT 6091.mp4"                                                                  , "FT 200");
		rtnMap.put("F/FUJIKO5_hq.mp4"                                                               , "FUJIKO5_hq");
		rtnMap.put("F/hj .h264(1).mp4"                                                              , "hj_h264_A.mp4");
		rtnMap.put("F/hj .mp4"                                                                      , "hjAA.mp4");
		rtnMap.put("F/hj 13.mp4"                                                                    , "あ203");
		rtnMap.put("F/hj 3.mp4"                                                                     , "あ204");
		rtnMap.put("F/hj 6_hd.mp4"                                                                  , "あ205");
		rtnMap.put("F/HJ 69.mp4"                                                                    , "HJ 207");
		rtnMap.put("F/hj 70311.mp4"                                                                 , "HJ 208");
		rtnMap.put("F/HJ 7625_hd.mp4"                                                               , "HJ 209");
		rtnMap.put("F/hj 80p.mp4"                                                                   , "HJ 210");
		rtnMap.put("F/HJ 834.mp4"                                                                   , "HJ 211");
		rtnMap.put("F/HJ 86_hd.mp4"                                                                 , "HJ 212");
		rtnMap.put("F/HJ 8d04993918af0bac4.mp4"                                                     , "HJ 213");
		rtnMap.put("F/hj 9031_hd.mp4"                                                               , "HJ 214");
		rtnMap.put("F/hj 976.mp4"                                                                   , "HJ 215");
		rtnMap.put("F/Lucie_Wilde/3 2).mp4"                                                         , "Lucie_Wilde 31");
		rtnMap.put("F/Lucie_Wilde/3(3).mp4"                                                         , "Lucie_Wilde 32");
		rtnMap.put("F/Lucie_Wilde/３4(2).mp4"                                                       , "Lucie_Wilde 36");
		rtnMap.put("F/Lucie_Wilde/720p.h264(10).mp4"                                                , "Lucie_Wilde 95");
		rtnMap.put("F/Lucie_Wilde/720p.h264(4).mp4"                                                 , "Lucie_Wilde 96");
		rtnMap.put("F/Lucie_Wilde/720p.h264.mp4"                                                    , "Lucie_Wilde 93");
		rtnMap.put("F/Lucie_Wilde/BUSTY4805347b621584ae6831.mp4"                                    , "Lucie_Wilde 178");
		rtnMap.put("F/Lucie_Wilde/BUSTY480p.mp4"                                                    , "Lucie_Wilde 179");
		rtnMap.put("F/Lucie_Wilde/BUSTYp.mp4"                                                       , "Lucie_Wilde 180");
		rtnMap.put("F/Lucie_Wilde/ｌプール4a411.mp4"                                                , "あ228");
		rtnMap.put("F/Lucie_Wilde/vl_720P_789.0k_33599922.mp4"                                      , "Lucie_Wild276");
		rtnMap.put("F/Lucie_Wilde/vl_720P_912.0k_36967121.mp4"                                      , "Lucie_Wild277");
		rtnMap.put("F/Lucie_Wilde/waisyatu1).mp4"                                                   , "Lucie_Wild278");
		rtnMap.put("F/Lucie_Wilde/YouPorn_bigorgasm.mp4"                                            , "あ293");
		rtnMap.put("F/Lucie_Wilde/YouPorn_big-tits-in-red.mp4"                                      , "あ292");
		rtnMap.put("F/Lucie_Wilde/サウナ264.mp4"                                                    , "あ309");
		rtnMap.put("F/Lucie_Wilde/べッド_hd.mp4"                                                    , "あ327");
		rtnMap.put("F/Lucie_Wilde/ワインed-1.mp4"                                                   , "あ332");
		rtnMap.put("F/Lucie_Wilde/黄緑7.mp4"                                                        , "あ333");
		rtnMap.put("F/Lucie_Wilde/黒4c2a-1.mp4"                                                     , "Lucie_Wilde 335");
		rtnMap.put("F/Lucie_Wilde/縞模様571.mp4"                                                    , "あ337");
		rtnMap.put("F/Lucie_Wilde/女医68-1.mp4"                                                     , "Lucie_Wilde 女医");
		rtnMap.put("F/Lucie_Wilde/青d.mp4"                                                          , "Lucie_Wilde/青");
		rtnMap.put("F/mandigo 5491.mp4"                                                             , "mandigo233");
		rtnMap.put("F/mandigo engela Whit 251.mp4"                                                  , "mandigo234");
		rtnMap.put("F/mandigo/mandigo_035.mp4"                                                      , "mandigo235");
		rtnMap.put("F/mandigo/mandigo_15014.mp4"                                                    , "mandigo237");
		rtnMap.put("F/mandigo/mandigo_34.mp4"                                                       , "mandigo238");
		rtnMap.put("F/mandigo/mandigo79.mp4"                                                        , "mandigo240");
		rtnMap.put("F/mandigo12.mp4"                                                                , "mandigo239");
		rtnMap.put("F/Mia_Khalifa/Mia_Khalifa 0p.mp4"                                               , "Mia_Khalifa243");
		rtnMap.put("F/Mia_Khalifa/Mia_Khalifa q.mp4"                                                , "Mia_Khalifa244");
		rtnMap.put("F/Mia_Khalifa/Mia_Khalifa(1).mp4"                                               , "Mia_Khalifa245");
		rtnMap.put("F/Mia_Khalifa/Mia_Khalifa.mp4"                                                  , "Mia_Khalifa242");
		rtnMap.put("F/Mia_Khalifa/Mia_Khalifa_hq.mp4"                                               , "Mia_Khalifa246");
		rtnMap.put("F/Mikami Yua 1.mp4"                                                             , "Mikami Yua");
		rtnMap.put("F/Miriam Jorge 2.mp4"                                                           , "あ248");
		rtnMap.put("F/Mya Nichole.mp4"                                                              , "あ249");
		rtnMap.put("F/Nekane n[1].mp4"                                                              , "あ250");
		rtnMap.put("F/Noelle_Easton/4476166.mp4"                                                    , "Noelle_Easton 56");
		rtnMap.put("F/Noelle_Easton/Noelle_Easton 2.mp4"                                            , "Noelle_Easton252");
		rtnMap.put("F/Noelle_Easton/Noelle_Easton 7_hd.mp4"                                         , "Noelle_Easton253");
		rtnMap.put("F/Noelle_Easton/Noelle_Easton o_hq.mp4"                                         , "Noelle_Easton254");
		rtnMap.put("F/Noelle_Easton/Noelle_Easton.mp4"                                              , "Noelle_Easton251");
		rtnMap.put("F/Noelle_Easton/Noelle_Easton_hq.mp4"                                           , "Noelle_Easton255");
		rtnMap.put("F/Noelle_Easton/Noelle_Easton3.mp4"                                             , "Noelle_Easton256");
		rtnMap.put("F/Noelle_Easton/Noelle_Easton72.mp4"                                            , "Noelle_Easton257");
		rtnMap.put("F/paris milan.mp4"                                                              , "paris milan258");
		rtnMap.put("F/RR22.mp4"                                                                     , "あ259");
		rtnMap.put("F/rtg001_49511851.mp4"                                                          , "あ260");
		rtnMap.put("F/rtg001_517111.mp4"                                                            , "あ261");
		rtnMap.put("F/Shione_Cooper/4043203_hq.mp4"                                                 , "あ47");
		rtnMap.put("F/Shione_Cooper/4457831_hq.mp4"                                                 , "Shione_Cooper 55");
		rtnMap.put("F/Shione_Cooper/Shione_Cooper q.mp4"                                            , "Shione_Cooper264");
		rtnMap.put("F/Shione_Cooper/Shione_Cooper.flv"                                              , "Shione_Cooper263");
		rtnMap.put("F/Stella d.mp4"                                                                 , "Stella 265");
		rtnMap.put("F/Tracy_Lords/4334093_hq.mp4"                                                   , "あ53");
		rtnMap.put("F/Tracy_Lords/480P_600K_15471081.mp4"                                           , "あ58");
		rtnMap.put("F/Tracy_Lords/4858007_hq.mp4"                                                   , "Tracy_Lords 73");
		rtnMap.put("F/Tracy_Lords/5714557_hq.mp4"                                                   , "Tracy_Lords 76");
		rtnMap.put("F/Tracy_Lords/5714572_hq(1).mp4"                                                , "Tracy_Lords 77");
		rtnMap.put("F/Tracy_Lords/5839654_hq.mp4"                                                   , "Tracy_Lords 80");
		rtnMap.put("F/Tracy_Lords/amazing_fucking_adventure.mp4"                                    , "あ139");
		rtnMap.put("F/Tracy_Lords/bad_girls3.mp4"                                                   , "Tracy_Lords 167");
		rtnMap.put("F/Tracy_Lords/Sex_Goddess1.mp4"                                                 , "Tracy_Lords262");
		rtnMap.put("F/Tracy_Lords/tr5e2.mp4"                                                 		, "Tracy_Lords_tr5e2.mp4");
		rtnMap.put("F/Tracy_Lords/TR_8.mp4"                                                         , "Tracy_Lords267");
		rtnMap.put("F/Tracy_Lords/TR_LOVELY_YANG_TRACY.mp4"                                         , "Tracy_Lords268");
		rtnMap.put("F/Tracy_Lords/TR_StepSister_Dearest_1984.mp4"                                   , "Tracy_Lords269");
		rtnMap.put("F/Tracy_Lords/Traci_Lords_Dirty_Pics.wmv[1].flv"                                , "Tracy_Lords270");
		rtnMap.put("F/Tracy_Lords/traci_lust_in_the_fast_lane.mp4"                                  , "Tracy_Lords271");
		rtnMap.put("F/Tracy_Lords/Tracis_Seduction.mp4"                                             , "Tracy_Lords272");
		rtnMap.put("F/Tracy_Lords/Tracy_Lords.flv"                                                  , "Tracy_Lords273");
		rtnMap.put("F/vl_480P_525.0k_59919891.mp4"                                                  , "あ274");
		rtnMap.put("F/vl_720_1383k_57370161.mp4"                                                    , "あ275");
		rtnMap.put("F/xvideos.com_1e62f57bf4c-1.mp4"                                                , "あ280");
		rtnMap.put("F/xvideos.com_2ea35526df.mp4"                                                   , "あ281");
		rtnMap.put("F/xvideos.com_54c9d37d671.mp4"                                                  , "あ282");
		rtnMap.put("F/xvideos.com_5ad550ea9.mp4"                                                    , "あ283");
		rtnMap.put("F/xvideos.com_9d4e4d8ede.mp4"                                                   , "あ284");
		rtnMap.put("F/xvideos.com_a37c9f33380.mp4"                                                  , "あ285");
		rtnMap.put("F/xvideos.com_b2e80f47bb90-1.mp4"                                               , "あ286");
		rtnMap.put("F/xvideos.com_c6a325884508.mp4"                                                 , "あ287");
		rtnMap.put("F/xvideos.com_d0060.mp4"                                                        , "あ288");
		rtnMap.put("F/xvideos.com_db5bc38af0bac4.mp4"                                               , "あ289");
		rtnMap.put("F/YouPorn_-_big-tits.mp4"                                                       , "あ290");
		rtnMap.put("F/YouPorn_-_mom-tit.mp4"                                                        , "あ291");
		rtnMap.put("F/あｆｄふぁｓｄｆ.mp4"                                                         , "あ300");
		rtnMap.put("F/がんばれ日本32.mp4"                                                           , "あ305");
		rtnMap.put("J1/1576678_hq.mp4"                                                              , "あ13");
		rtnMap.put("J1/190213_0637_720P748241.mp4"                                                  , "あ18");
		rtnMap.put("J1/B _237461771.mp4"                                                            , "あ142");
		rtnMap.put("J1/B JKda9bac7e81659.mp4"                                                       , "あ147");
		rtnMap.put("J1/jav 1665591.mp4"                                                             , "あ219");
		rtnMap.put("J1/ア　あやみ旬果02.mp4"                                                        , "あやみ旬果");
		rtnMap.put("J1/ア　葵美夕ｙ_hq.mp4"                                                         , "葵美夕");
		rtnMap.put("J1/ア　有奈めぐみ.mp4"                                                          , "有奈めぐみ");
		rtnMap.put("J1/イ　伊藤かほ.mp4"                                                            , "伊藤かほ");
		rtnMap.put("J1/コ　小柳まりん3_hq.mp4"                                                      , "小柳まりん306");
		rtnMap.put("J1/サ　さくらほのか.mp4"                                                        , "さくらほのか307");
		rtnMap.put("J1/サ　真田春香.mp4"                                                            , "真田春香308");
		rtnMap.put("J1/ジ　ＪＵＬＩＡ　532.mp4"                                                     , "ＪＵＬＩＡ310");
		rtnMap.put("J1/ジ　JULIA22.mp4"                                                             , "ＪＵＬＩＡ311");
		rtnMap.put("J1/ジ　ＪＵＲＩＡ1.mp4"                                                         , "ＪＵＬＩＡ312");
		rtnMap.put("J1/シ　篠田ゆう .mp4"                                                           , "篠田ゆう313");
		rtnMap.put("J1/しろハメ　素人　サキ 821.mp4"                                                , "素人　サキ");
		rtnMap.put("J1/ス　杉崎千春.mp4"                                                            , "杉崎千春");
		rtnMap.put("J1/タ　武井麻希.mp4"                                                            , "武井麻希");
		rtnMap.put("J1/タ　立川理恵.mp4"                                                            , "立川理恵");
		rtnMap.put("J1/タ　立川理恵5.mp4"                                                           , "立川理恵");
		rtnMap.put("J1/タ　立川理恵9.mp4"                                                           , "立川理恵あ319");
		rtnMap.put("J1/ツ　椿みゅう.mp4"                                                            , "椿みゅう");
		rtnMap.put("J1/ﾅ　仲村ろみひ.mp4"                                                           , "仲村ろみひ321");
		rtnMap.put("J1/ハ　花井メイサ２.mp4"                                                        , "花井メイサ322");
		rtnMap.put("J1/ハ　春菜はな.mp4"                                                            , "春菜はな323");
		rtnMap.put("J1/ハ　早川瀬里奈.mp4"                                                          , "早川瀬里奈324");
		rtnMap.put("J1/ひじり.mp4"                                                                  , "ひじり");
		rtnMap.put("J1/フ　深美せりな.mp4"                                                          , "深美せりな");
		rtnMap.put("J1/ホ　星咲優菜.mp4"                                                            , "星咲優菜.m");
		rtnMap.put("J1/ヤ　山下裕子.mp4"                                                            , "山下裕子.m");
		rtnMap.put("J1/リ　リナ.mp4"                                                                , "あ331");
		rtnMap.put("J2/1080P_4000K_259481762.mp4"                                                   , "あ4");
		rtnMap.put("J2/1080P_4000K_262451392.mp4"                                                   , "あ5");
		rtnMap.put("J2/1080P_4000K_264113972.mp4"                                                   , "あ6");
		rtnMap.put("J2/1080P_4000K_264250832.mp4"                                                   , "あ7");
		rtnMap.put("J2/1080P_4000K_283755962.mp4"                                                   , "あ9");
		rtnMap.put("J2/1896685_hq.mp4"                                                              , "あ17");
		rtnMap.put("J2/373787_hq(1).mp4"                                                            , "あ40");
		rtnMap.put("J2/425962_hq.mp4"                                                               , "あ50");
		rtnMap.put("J2/480P_600K_207808311.mp4"                                                     , "あ61");
		rtnMap.put("J2/480P_600K_237768961.mp4"                                                     , "あ63");
		rtnMap.put("J2/480P_600K_247181431.mp4"                                                     , "あ64");
		rtnMap.put("J2/480P_600K_274804591.mp4"                                                     , "あ67");
		rtnMap.put("J2/480P_600K_275416991.mp4"                                                     , "あ68");
		rtnMap.put("J2/480P_600K_275424161.mp4"                                                     , "あ69");
		rtnMap.put("J2/480P_600K_275425541.mp4"                                                     , "あ70");
		rtnMap.put("J2/627799.mp4"                                                                  , "あ83");
		rtnMap.put("J2/7055410.mp4"                                                                 , "あ91");
		rtnMap.put("J2/720P_1500K_204227781.mp4"                                                    , "あ103");
		rtnMap.put("J2/720P_1500K_217034562.mp4"                                                    , "あ104");
		rtnMap.put("J2/720P_1500K_238148481.mp4"                                                    , "あ106");
		rtnMap.put("J2/720P_1500K_272016501.mp4"                                                    , "あ110");
		rtnMap.put("J2/720P_1500K_272660411.mp4"                                                    , "あ112");
		rtnMap.put("J2/720P_1500K_5839501.mp4"                                                      , "あ114");
		rtnMap.put("J2/720PMitake Suzu2.mp4"                                                        , "あ117");
		rtnMap.put("J2/8111446_hq.mp4"                                                              , "あ123");
		rtnMap.put("J2/b 227be.mp4"                                                                 , "あ143");
		rtnMap.put("J2/B 37_hq.mp4"                                                                 , "あ144");
		rtnMap.put("J2/B 553.mp4"                                                                   , "あ145");
		rtnMap.put("J2/B e5bd4712f.mp4"                                                             , "あ146");
		rtnMap.put("J2/B Kaoru Hirayama q.mp4"                                                      , "Kaoru Hirayama 148");
		rtnMap.put("J2/B Kei Megumi.mp4"                                                            , "Kei Megumi 149");
		rtnMap.put("J2/b r 0p.mp4"                                                                  , "あ150");
		rtnMap.put("J2/g 837.mp4"                                                                   , "あ202");
		rtnMap.put("J2/hj 610.mp4"                                                                  , "あ206");
		rtnMap.put("J2/JAV   橋本ありな361.mp4"                                                     , "橋本ありな6");
		rtnMap.put("J2/JAV  吉川まなみ3491.mp4"                                                     , "吉川まなみ");
		rtnMap.put("J2/jav _4000K_266556922.mp4"                                                    , "あ218");
		rtnMap.put("J2/JAV 高橋しょう子1.mp4"                                                       , "高橋しょう子");
		rtnMap.put("J2/ア　葵つかさ.mp4"                                                            , "葵つかさ");
		rtnMap.put("J2/ア　蒼井そら/xvideos.com_065.mp4"                                            , "蒼井そら279");
		rtnMap.put("J2/ア　蒼井そら/蒼井そら13_hq.mp4"                                              , "蒼井そら13_");
		rtnMap.put("J2/ア　蒼井そら/蒼井そら7_hq.mp4"                                               , "蒼井そら7_h");
		rtnMap.put("J2/ア　麻美ゆま/mayu 9_hq.mp4"                                                  , "麻美ゆま41");
		rtnMap.put("J2/ア　麻美ゆま/ア　麻美ゆま1.mp4"                                              , "麻美ゆま1");
		rtnMap.put("J2/ア　麻美ゆま/ア　麻美ゆま3.mp4"                                              , "麻美ゆま2");
		rtnMap.put("J2/あきよしひな.mp4"                                                            , "あ302");
		rtnMap.put("J2/ウ_宇都宮しをん/jav_238283171.mp4"                                           , "宇都宮しをん21");
		rtnMap.put("J2/ウ_宇都宮しをん/jav_sion_2.mp4"                                              , "宇都宮しをん2");
		rtnMap.put("J2/ウ_宇都宮しをん/jav_宇都宮しをん_おまん○くぱぁ.mp4"                         , "宇都宮しをん_おまん○くぱ");
		rtnMap.put("J2/ウ_宇都宮しをん/jav_宇都宮しをん_黄金比ボディ2.mp4"                          , "宇都宮しをん_黄金比ボディ");
		rtnMap.put("J2/ウ_宇都宮しをん/jav_宇都宮しをん_巨根ズボズボ1.mp4"                          , "宇都宮しをん_巨根ズボズボ");
		rtnMap.put("J2/ウ_宇都宮しをん/jav_宇都宮しをん97_ shion62.mp4"                             , "宇都宮しをん97");
		rtnMap.put("J2/ウ_宇都宮しをん/jv_P_4000K_264477962.mp4"                                    , "宇都宮しをん227");
		rtnMap.put("J2/かすみりさhq.mp4"                                                            , "あ304");
		rtnMap.put("J2/キ　きみの奈津/初中出し子宮で感じる温かい生ザーメン.mp4"                     , "きみの奈津 初中出し子宮で感じる温かい生ザーメン");
		rtnMap.put("J2/キ　きみの奈津/逝きたいのに逝かせてもらえない.mp4"                           , "きみの奈津 逝きたいのに逝かせてもらえない");
		rtnMap.put("J2/キ　北乃ちか/1879795_hq.mp4"                                                 , "あ16");
		rtnMap.put("J2/キ　北乃ちか/397955_hq.mp4"                                                  , "北乃ちか 46");
		rtnMap.put("J2/キ　北乃ちか/426318_hq.mp4"                                                  , "北乃ちか1");
		rtnMap.put("J2/キ　北乃ちか/430201_hq.mp4"                                                  , "北乃ちか2");
		rtnMap.put("J2/キ　北乃ちか/480P_600K_162593342.mp4"                                        , "北乃ちか59");
		rtnMap.put("J2/キ　北乃ちか/480P_600K_162593632.mp4"                                        , "北乃ちか60");
		rtnMap.put("J2/キ　北乃ちか/Chika Kitano.mp4"                                               , "北乃ちか181");
		rtnMap.put("J2/キ　北乃ちか/Chika Kitano4_hd.mp4"                                           , "北乃ちか182");
		rtnMap.put("J2/ナ　長瀬麻美/1966945_hq.mp4"                                                 , "長瀬麻美1");
		rtnMap.put("J2/ナ　長瀬麻美/1966948_hq.mp4"                                                 , "長瀬麻美2");
		rtnMap.put("J2/ナ　長瀬麻美/2276756_hq.mp4"                                                 , "長瀬麻美3");
		rtnMap.put("J2/ナ　長瀬麻美/Mami Nagase 53_hq.mp4"                                          , "長瀬麻美230");
		rtnMap.put("J2/ナ　長瀬麻美/Mami Nagase 81_hq.mp4"                                          , "長瀬麻美231");
		rtnMap.put("J2/ナ　長瀬麻美/Mami Nagase.87_hq.mp4"                                          , "長瀬麻美232");
		rtnMap.put("J2/ナ　長瀬麻美/Mami Nagase.mp4"                                                , "長瀬麻美229");
		rtnMap.put("J2/ナ　長瀬麻美/長瀬麻美 Ｈカップ爆乳グラドルがAV女優に.URL"                    , "長瀬麻美 Ｈ");
		rtnMap.put("J2/ヨ　吉永あかねq.mp4"                                                         , "吉永あかね");
		rtnMap.put("J2/西村ニーナ2.mp4"                                                             , "西村ニーナ2.");
		rtnMap.put("J2/西村ニーナ51.mp4"                                                            , "西村ニーナ51");
		rtnMap.put("J2/西村ニーナ61.mp4"                                                            , "西村ニーナ61");
		rtnMap.put("J2/椎名ゆなをイカセ続ける841.mp4"                                               , "あ350");
		rtnMap.put("J2/灘坂舞/Thumbs.db"                                                            , "灘坂舞266");
		rtnMap.put("J2/灘坂舞/灘坂舞001 .wmv"                                                       , "灘坂舞 351");
		rtnMap.put("J2/北乃はるか.mp4"                                                              , "北乃はるか");
		rtnMap.put("J2/夢乃あいか9_hq.mp4"                                                          , "夢乃あいか");
		rtnMap.put("J2/夢乃あいか921.mp4"                                                           , "夢乃あいか");
		rtnMap.put("J2/夢乃あいかq.mp4"                                                             , "夢乃あいか");
		rtnMap.put("J2/澁谷果歩 d.mp4"                                                              , "澁谷果歩 d");
		rtnMap.put("J2/澁谷果歩.mp4"                                                                , "澁谷果歩.m");
		rtnMap.put("J2/濱口えな.mp4"                                                                , "濱口えな.m");
		rtnMap.put("web/240p.vp9.webm"                                                              , "あ26");

		return rtnMap;
	}
}
