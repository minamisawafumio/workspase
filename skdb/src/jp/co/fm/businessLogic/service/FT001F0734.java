package jp.co.fm.businessLogic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.businessLogic.system.SystemService;
import jp.co.fm.businessLogic.table.T_1010;
import manga.common.VideoDb;
import manga.form.FT001F0734Form;

@Controller
@RequestMapping("FT001F0734")
public class FT001F0734 {

	String thisGamen = "FT001F0734";

	@Autowired(required=false)
    private FT001F0734Form thisForm;

    @ModelAttribute("FT001F0734Form")
	public void init(@ModelAttribute FT001F0734Form form, Model model) {
    	thisForm = form;
	}

	/**
	 * 戻り処理
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "form", params = "back", method = RequestMethod.POST)
    public Object back2(Model model, HttpServletRequest request) {
    	return Service.getInstance().backGamen(request, model, thisForm);
    }

	/**
	 *
	 * @param request
	 * @return
	 */
	public String syori_f0734(HttpServletRequest request) {


		HttpSession httpSession = request.getSession();

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName		= (String) map.get("methodName");
		String searchKey		= (String) map.get("searchKey");
		String ijyouKensuuKey	= (String) map.get("ijyouKensuuKey");
		String array000		= (String) map.get("array000");
		String array123		= (String) map.get("array123");
		String mapData01		= (String) map.get("mapData01");
		String mapData02		= (String) map.get("mapData02");
		String mapData03		= (String) map.get("mapData03");
		String mapData04		= (String) map.get("mapData04");

		System.out.println("array000=" + array000);
		System.out.println("array123=" + array123);
		System.out.println("mapData01=" + mapData01);
		System.out.println("mapData02=" + mapData02);
		System.out.println("mapData03=" + mapData03);
		System.out.println("mapData04=" + mapData04);

		System.out.println("ijyouKensuuKey=" + ijyouKensuuKey);

		System.out.println("syori_f0734 searchKey=" + searchKey + "  methodName=" + methodName);

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String selectCountSql = VideoDb.getInstance().getSelectCountSql(searchKey, ijyouKensuuKey);

		Long count = DbUtil.getInstance().selectCount(selectCountSql);

		String selectSql = VideoDb.getInstance().getSelectSql(searchKey, ijyouKensuuKey);

		System.out.println("SystemCommon#syori_f0734 selectSql=" + selectSql);

		List<Object> list = VideoDb.getInstance().getMangaList(selectSql);



		List<Map<String, String>> rtnList = new ArrayList<>();

        SqlSession sqlSession = DbUtil.getInstance().getNewSqlSession();

		T_1010 t_1010 = new T_1010();
		t_1010.setCorpCd("01");
		t_1010.setDelFlg("0");
		t_1010.setRecCd("016");

		String []primaryKey = {"corpCd", "delFlg", "recCd"};
		String sortKey = " order by Item02";

		List<Object> objList = DbUtil.getInstance().select(sqlSession, t_1010, primaryKey, sortKey);

		sqlSession.close();

		Map<String, String> rtnMap;

		for(Object obj: objList) {
			t_1010 = (T_1010) obj;
			rtnMap = new HashMap<>();
			rtnMap.put("pathFile"	, t_1010.getItem01());
			rtnMap.put("videoTitle"	, t_1010.getItem02());
			rtnList.add(rtnMap);
		}

		String jsonRtnList = JsonUtil.getInstance().makeObjectToJsonString(rtnList);

		//-------------------------------------------------------
		String json = SystemService.getInstance().getJson(request, Const.REQUEST_KEY);

		Map<String, List<String>> listMap = SystemService.getInstance().getGamenListA(json);

		String jsonString = JsonUtil.getInstance().makeObjectToJsonString(listMap);

		System.out.println("syori_f0734 jsonString=" + jsonString);
		//--------------------------------------------------------

		String jsonData = "";

		map.put("sessionId"	, httpSession.getId());
		map.put("count"		, count.toString());
		map.put("jsonRtnList"	, jsonRtnList);
		map.put("jsonString"	, jsonString);
		map.put("comDateTime"	, comDateTime);

		jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String doWindowClose(HttpServletRequest request) {
		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");

		System.out.println(methodName);

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	public String doVideoPopup(HttpServletRequest request) {
		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String className = (String) map.get("className");

		String methodName	= (String) map.get("methodName");

		System.out.println(className + "#" + methodName);

		boolean openJudgment = getOpenJudgment(request);

		if(openJudgment == true) {
			map.put("openJudgment", "TRUE");
		}else {
			map.put("openJudgment", "FALSE");
		}

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 * マンガ子画面を開く判定
	 * @param request
	 * @return
	 */
	public boolean getOpenJudgment(HttpServletRequest request) {
		boolean isLogin = SystemService.getInstance().isLogin(request);

		if(isLogin == true) {
			return true;//true;false
		}else {
			return false;
		}
	}

}
