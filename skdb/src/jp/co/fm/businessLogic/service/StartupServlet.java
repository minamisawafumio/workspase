package jp.co.fm.businessLogic.service;

import java.net.SocketException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.FileUtil;
import jp.co.fm.businessLogic.common.NetUtil;
import jp.co.fm.businessLogic.system.SystemInfo;
import jp.co.fm.businessLogic.system.SystemService;

public class StartupServlet extends HttpServlet {

    /**
     * システム起動時実行メソッド
     */
    @Override
    public void init() throws ServletException {
		String systemPath = FileUtil.getInstance().getSystemPath();
        makeSystemInfo(systemPath);
    }

    public void makeSystemInfo(String systemPath) {

		Map<String,String> countryMap = getCountryMap();
		Map<String,String> codeMap = getCodeMap();

		Map<String,Map> cityMap = getCityMap();

		Set set = makeNoLoginGamenNameSet();

		SystemInfo si = SystemInfo.getInstance();

		si.putValue(Const.NO_LOGIN, set);

        String ip = getIp();

        si.putValue(Const.RENDOU1, countryMap);
        si.putValue(Const.CODE_MAP_MANGA, codeMap);
        si.putValue(Const.RENDOU2_MAP, cityMap);
        si.putValue(Const.PDF_PATH, systemPath + "workspace/skdb/WebContent/WEB-INF/pic/");
        si.putValue(Const.WEB_INF_REAL_PATH, systemPath + "workspace/skdb/WebContent/WEB-INF/");
        si.putValue(Const.URL, "http://" + ip + ":18080/skdb");
        si.putValue(Const.IP, ip);
        //サーバー起動時刻
        si.putValue(Const.SERVER_START_TIME, DateUtil.getInstance().getComDateTime(5));//

        String notLoginImg = SystemService.getInstance().getNotLoginImg();
        //未ログイン時の画像を設定
        si.putValue(Const.NOT_LOGIN_IMG, notLoginImg);

        String data = "{'select':'exe02','exe02Text':'NNNNN','tel':'090-0123-4567'}";

        si.putValue(Const.SYS_JSON, data);
    }

    private String getIp() {
    	String rtn = null;

    	try {
			List<String> list = NetUtil.getInstance().getHostAddressList();
			rtn = list.get(0);
		} catch (SocketException e) {
			e.printStackTrace();
		}

    	return rtn;
    }

    /**
    *
    * @return
    */
   private Set<String> makeNoLoginGamenNameSet() {
   	Set<String> set = new HashSet<>();
   	set.add("FT000001");
   	set.add("FT001F01");
   	set.add("FT091F01");
   	set.add("ERROR001");
   	return set;
   }

    private Map<String,String> getCountryMap() {
        Map<String,String> countryMap = new HashMap<>();
        countryMap.put("アメリカ", "アメリカ");
        countryMap.put("ドイツ", "ドイツ");
        countryMap.put("ブラジル", "ブラジル");
        return countryMap;
    }

    private Map<String,String> getCodeMap() {
		Map<String, String> map = new HashMap<>();

		map.put("2520", " ");
		map.put("2521", "!");
		map.put("2523", "#");
		map.put("2524", "$");
		map.put("2525", "%");
		map.put("2526", "&");
		map.put("2527", "'");
		map.put("2528", "(");
		map.put("2529", ")");
		map.put("252C", ",");
		map.put("253A", ":");
		map.put("253B", ";");
		map.put("253C", "<");
		map.put("253D", "=");
		map.put("253E", ">");
		map.put("253F", "?");
		map.put("255B", "[");
		map.put("255D", "]");
		map.put("255E", "^");
		map.put("257B", "{");
		map.put("257D", "}");
		map.put("257E", "~");
		map.put("2560", "`");
		map.put("2F"  , "/");
		map.put("40"  , "@");
    	return map;
    }

    private Map<String,Map> getCityMap() {
        Map<String,Map> cityMap = new HashMap<>();
        Map<String,String> map = new HashMap<>();
        map.put("ニューヨーク", "ニューヨーク");
        map.put("サンフランシスコ", "サンフランシスコ");
        map.put("デンバー", "デンバー");
        cityMap.put("アメリカ", map);

        map = new HashMap<>();
        map.put("ベルリン", "ベルリン");
        map.put("ミュンヘン", "ミュンヘン");
        map.put("フランクフルト", "フランクフルト");
        cityMap.put("ドイツ", map);
        map = new HashMap<>();
        map.put("サンパウロ", "サンパウロ");
        map.put("リオデジャネイロ", "Rio de リオデジャネイロ");
        map.put("サルバドル", "サルバドル");
        cityMap.put("ブラジル", map);

        return cityMap;
    }


    @Override
    public void destroy() {
    	//漫画のページングに使っている立ち上げっぱなしのSQLセッションを閉じる
    	MangaDb.getInstacce().closeSqlSession();

    	System.out.println("destroy OK");
    }
}
