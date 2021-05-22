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
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.system.SystemService;
import jp.co.fm.businessLogic.table.T_1010;
import manga.form.FT001F0733Form;

@Controller
@RequestMapping("FT001F0733")
public class FT001F0733 {
	String thisGamen = "FT001F0733";

//	MangaDb md = MangaDb.getInstacce();
	DbUtil du = DbUtil.getInstance();


	@Autowired(required=false)
    private FT001F0733Form thisForm;

    @ModelAttribute("FT001F0733Form")
	public void init(@ModelAttribute FT001F0733Form form, Model model) {
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
	public String isExistSession(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String userId = (String) httpSession.getAttribute(Const.USER_ID);

		if (userId == null) {
			map.put("result", "NotExist");
		} else {
			map.put("result", "Exist");
		}

		String jsonData = SystemService.getInstance().makeJsonData(map);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String syori_f0733(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName		= (String) map.get("methodName");
		String searchKey		= (String) map.get("searchKey");
		String ijyouKensuuKey	= (String) map.get("ijyouKensuuKey");

		System.out.println("ijyouKensuuKey=" + ijyouKensuuKey);

		System.out.println("syori_f0733 searchKey=" + searchKey + "  methodName=" + methodName);

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String selectCountSql = MangaDb.getInstacce().getSelectCountSql(searchKey, ijyouKensuuKey);

		Long count = du.selectCount(selectCountSql);

		String selectSql = MangaDb.getInstacce().getSelectSql(searchKey, ijyouKensuuKey);

		System.out.println("SystemCommon#syori_f0733 selectSql=" + selectSql);

		SqlSession sqlSession = MangaDb.getInstacce().getSqlSession();

		List<Object> list =  du.select(sqlSession, new T_1010(), selectSql);


		Map<String, String> rtnMap;

		T_1010 t_1010;

		List<Map<String, String>> rtnList = new ArrayList<>();

		for(Integer i = 0; i < list.size(); i++) {

			t_1010 = (T_1010) list.get(i);

	        rtnMap = new HashMap<>();

	        String bookNoSt = StringUtil.getInstance().trimLeftZero(t_1010.getItem01());

	        if(bookNoSt.length() == 0) {
	        	bookNoSt = "0";
	        }

	        rtnMap.put("bookNoSt"	, bookNoSt);
	        rtnMap.put("mangaNo"	, t_1010.getItem02());
	        rtnMap.put("mangaTitle"	, t_1010.getItem03());
	        rtnList.add(rtnMap);
		}

		String jsonRtnList = JsonUtil.getInstance().makeObjectToJsonString(rtnList);

		//-------------------------------------------------------
		String json = SystemService.getInstance().getJson(request, Const.REQUEST_KEY);

		Map<String, List<String>> listMap = SystemService.getInstance().getGamenListA(json);

		String jsonString = JsonUtil.getInstance().makeObjectToJsonString(listMap);

		System.out.println("syori_f0733 jsonString=" + jsonString);
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
	public static String doWindowClose(HttpServletRequest request) {
		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");

		System.out.println(methodName);

		String jsonData = SystemService.getInstance().makeJsonData(map);

		System.out.println(jsonData);

		return jsonData;
	}

	public static String doMangaPopup(HttpServletRequest request) {
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
	public static boolean getOpenJudgment(HttpServletRequest request) {
		boolean isLogin = SystemService.getInstance().isLogin(request);

		if(isLogin == true) {
			return true;//true;false
		}else {
			return false;
		}
	}

}
