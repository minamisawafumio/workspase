package jp.co.fm.businessLogic.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.CommonUtil;
import jp.co.fm.businessLogic.common.DateUtil;
import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.system.SystemService;
import jp.co.fm.presentation.form.FT00ShogiForm;

@Controller
@RequestMapping("FT00Shogi")
public class FT00Shogi {

	public static String thisGamen = "FT00Shogi";

	@Autowired(required=false)
    private FT00ShogiForm thisForm;

    @ModelAttribute("FT00ShogiForm")
	public void init(@ModelAttribute FT00ShogiForm form, Model model) {
    	thisForm = form;
	}

    /**
     * 決定
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "form", params = "doExecute", method = RequestMethod.POST)
	public String doExecute(Model model, HttpServletRequest request) throws IOException {

    	return thisGamen;
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

    @RequestMapping(value = "fm", params = "button_66", method = RequestMethod.POST)
    public Object button_66(Model model, HttpServletRequest request) {
       	return Service.getInstance().backGamen(request, model, thisForm);
    }

    @RequestMapping(value = "fm", params = "button_22", method = RequestMethod.POST)
    public Object button_22(Model model, HttpServletRequest request) {
       	return Service.getInstance().backGamen(request, model, thisForm);
    }

    /**
     * 将棋リセット
     * @param model
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "form", params = "shogiBtn01", method = RequestMethod.POST)
    public String shogiBtn01(Model model, HttpServletRequest httpServletRequest) {
    	HttpSession httpSession = httpServletRequest.getSession();
    	FT00ShogiForm fT001F01Form = (FT00ShogiForm) httpSession.getAttribute(FT00ShogiForm.class.getSimpleName());

       	httpSession.setAttribute("btnCount", 0);
       	httpSession.setAttribute("tesuuCount", 0);

    	setShogiMsg(httpServletRequest);

    	return thisGamen;
    }

    @RequestMapping(value = "form", params = "shogiBtn02", method = RequestMethod.POST)
    public String shogiBtn02(Model model, HttpServletRequest httpServletRequest) {
    	HttpSession httpSession = httpServletRequest.getSession();
    	FT00ShogiForm fT001F01Form = (FT00ShogiForm) httpSession.getAttribute(FT00ShogiForm.class.getSimpleName());

    	Integer tesuuCount = (Integer) httpSession.getAttribute("tesuuCount");

    	List<int[]> list = (List<int[]>) httpSession.getAttribute("shogiBtn02list");

    	if(list != null) {
        	int[]teInfo =  list.get(tesuuCount);

        	System.out.println(teInfo[0] + " : " + teInfo[1] + " : " + teInfo[2] + " : " + teInfo[3] + " : " + teInfo[4] +
        			" : " + teInfo[5]+ " : " + teInfo[6]);
    	}

    	String printInfo = "ああああああああああ";  //ShogiCommon.getPrintInfo(data);

    	tesuuCount ++;

    	httpSession.setAttribute("tesuuCount", tesuuCount);

    	fT001F01Form.setShogiBan(printInfo);
    	fT001F01Form.setShogiBan2(printInfo);

    	setShogiMsg(httpServletRequest);

    	return thisGamen;
    }

    @RequestMapping(value = "form", params = "shogiBtn02_2", method = RequestMethod.POST)
    public String shogiBtn02_2(Model model, HttpServletRequest httpServletRequest) {
    	HttpSession httpSession = httpServletRequest.getSession();
    	FT00ShogiForm fT001F01Form = (FT00ShogiForm) httpSession.getAttribute(FT00ShogiForm.class.getSimpleName());

    	return thisGamen;
    }



    @RequestMapping(value = "form", params = "shogiBtn03", method = RequestMethod.POST)
    public String shogiBtn03(Model model, HttpServletRequest httpServletRequest) {
    	HttpSession httpSession = httpServletRequest.getSession();
    	FT00ShogiForm fT001F01Form = (FT00ShogiForm) httpSession.getAttribute(FT00ShogiForm.class.getSimpleName());

    	fT001F01Form.setShogiBan("shogiBtn03");

    	return thisGamen;
    }

    @RequestMapping(value = "form", params = "shogiBtn04", method = RequestMethod.POST)
    public String shogiBtn04(Model model, HttpServletRequest httpServletRequest) {
    	HttpSession httpSession = httpServletRequest.getSession();
    	FT00ShogiForm fT001F01Form = (FT00ShogiForm) httpSession.getAttribute(FT00ShogiForm.class.getSimpleName());

    	fT001F01Form.setShogiBan("shogiBtn04");

    	return thisGamen;
    }

    private void setShogiMsg(HttpServletRequest httpServletRequest) {
    	HttpSession httpSession = httpServletRequest.getSession();
    	FT00ShogiForm fT001F01Form = (FT00ShogiForm) httpSession.getAttribute(FT00ShogiForm.class.getSimpleName());

    	Integer count = (Integer) httpSession.getAttribute("btnCount");

    	String shogiMsg = "カウンタ：" + count;

    	fT001F01Form.setShogiMsg(shogiMsg);
    }

	public String shogi1(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String jsonData = CommonUtil.getInstance().makeJsonSt(
				"methodName" , "ret" + StringUtil.getInstance().headUpperCase(methodName),
				"comDateTime", comDateTime
		);

		return jsonData;
	}

	public String shogi2(HttpServletRequest request) {

		Map<String, Object> map = SystemService.getInstance().getGamenMap2(request);

		String methodName	= (String) map.get("methodName");

		String comDateTime = DateUtil.getInstance().getComDateTime(5);

		String jsonData = CommonUtil.getInstance().makeJsonSt(
				"methodName" , "ret" + StringUtil.getInstance().headUpperCase(methodName),
				"comDateTime", comDateTime
		);

		return jsonData;
	}
}
