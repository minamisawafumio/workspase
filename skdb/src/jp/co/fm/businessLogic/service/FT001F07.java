package jp.co.fm.businessLogic.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.CommonUtil;
import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.system.SystemInfo;
import jp.co.fm.businessLogic.system.SystemService;
import jp.co.fm.presentation.form.FT001F07Form;

@Controller
@RequestMapping("FT001F07")
public class FT001F07 {

	String thisGamen = "FT001F07";

	@Autowired(required=false)
    private FT001F07Form thisForm;

    @ModelAttribute("FT001F07Form")
	public void init(@ModelAttribute FT001F07Form form, Model model) {
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
	public String ft001f07_btn001_1(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");
		String iData	= (String) map.get("iData");

		System.out.println("ft001f07_btn001_1 iData=" + iData);

		map.put("serverStartTime", (String)  SystemInfo.getInstance().getValue(Const.SERVER_START_TIME));

		String jsonStringMap = JsonUtil.getInstance().makeObjectToJsonString(map);

		String jsonData = CommonUtil.getInstance().makeJsonSt(
				"methodName"	, "ret" + StringUtil.getInstance().headUpperCase(methodName),
				"jsonStringMap"	, jsonStringMap
		);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String FT001F07_2_date(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String methodName	= (String) map.get("methodName");
		String date			= (String) map.get("date");


		map.put("comDateTime", comDateTime);

		String jsonMapData = JsonUtil.getInstance().makeObjectToJsonString(map);

		String jsonData = CommonUtil.getInstance().makeJsonSt(
			"methodName"	, "ret" + StringUtil.getInstance().headUpperCase(methodName),
			Const.JSON_STRING_MAP	, jsonMapData
		);

		System.out.println("date=" + date);

		return jsonData;
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public String FT001F07_2_outputSelectedValueAndText(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String methodName	= (String) map.get("methodName");
		String value		= (String) map.get("value");
		String text			= (String) map.get("text");

		map.put("comDateTime", comDateTime);

		String jsonMapData = JsonUtil.getInstance().makeObjectToJsonString(map);

		String jsonData = CommonUtil.getInstance().makeJsonSt(
			"methodName"	, "ret" + StringUtil.getInstance().headUpperCase(methodName),
			Const.JSON_STRING_MAP	, jsonMapData
		);

		return jsonData;
	}


	public String FT001F07_range007(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String methodName	= (String) map.get("methodName");
		String value		= (String) map.get("value");
		String text			= (String) map.get("text");

		map.put("comDateTime", comDateTime);

		String jsonMapData = JsonUtil.getInstance().makeObjectToJsonString(map);

		String jsonData = CommonUtil.getInstance().makeJsonSt(
			"methodName"	, "ret" + StringUtil.getInstance().headUpperCase(methodName),
			Const.JSON_STRING_MAP	, jsonMapData
		);

		return jsonData;
	}
}
