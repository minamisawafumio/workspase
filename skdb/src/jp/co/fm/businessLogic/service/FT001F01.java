package jp.co.fm.businessLogic.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.common.JsonUtil;
import jp.co.fm.businessLogic.common.NetUtil;
import jp.co.fm.businessLogic.common.Service;
import jp.co.fm.businessLogic.common.StringUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.system.SystemInfo;
import jp.co.fm.businessLogic.table.T_0160;
import jp.co.fm.presentation.form.FT001F01Form;
import jp.co.fm.presentation.form.FT001F02Form;
import jp.co.fm.presentation.form.FT091F01Form;


@Controller
@RequestMapping("FT001F01")
public class FT001F01 {

	private static final Logger logger = LoggerFactory.getLogger(FT001F01.class);

	String thisGamen = "FT001F01";

	@Autowired(required=false)
    private FT001F01Form thisForm;


    @ModelAttribute("FT001F01Form")
	public void init(@ModelAttribute FT001F01Form form) {
    	thisForm = form;

       	System.out.println("FT001F01#init" );
	}

    /**
     * ログイン処理
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "login", method = RequestMethod.POST)
    public String goto083(Model model, HttpServletRequest request) {

    	HttpSession httpSession = request.getSession();

    	Map<String, List<String>> headerInfoMap = NetUtil.getInstance().getHeaderInfo(request);

    	JsonUtil.getInstance().print(headerInfoMap);

    	FT001F01Form fT001F01Form = (FT001F01Form) httpSession.getAttribute("fT001F01Form");

    	String userId	 = thisForm.getId();
    	String password = thisForm.getPassword();

		//パスワードハッシュ化
		String hashPassword = StringUtil.getInstance().encryption(password, Const.CODE_SHA_512);

    	String msg = validate(userId, hashPassword);

    	//入力にエラーがある場合
    	if(!"".equals(msg)){
    		fT001F01Form.setMessage(msg);
    		return thisGamen;
    	}

    	httpSession.setAttribute(Const.USER_ID, userId);
    	httpSession.setAttribute(Const.USER_PASSWORD, hashPassword);
    	httpSession.setAttribute(Const.SERVER_START_TIME, SystemInfo.getInstance().getValue(Const.SERVER_START_TIME));

    	return gotoFirstGamen(model, request);
    }

    public String gotoFirstGamen(Model model, HttpServletRequest request) {

    	HttpSession httpSession = request.getSession();

    	FT001F02Form form = new FT001F02Form();

        //----------------------------------------------------
        form.setSampleCheck1(true);
        form.setSampleCheck2(false);
        form.setSampleCheck3(true);

        //----------------------------------------------------
        String[] optionValues = {"1","",""};
        form.setOptionValues(optionValues);


        form.setSelectedIsbn("123");


        httpSession.setAttribute("FT001F02Form", form);

        return Service.getInstance().gotoNextGamen(request, thisForm, form, model);
    }


    /**
     * 入力内容チェック処理
     * @param thisForm
     * @return
     */
    public String validate(String userId, String iHashPassword){
    	String rtnStr = "";

    	T_0160 t_0160 = new T_0160();
    	t_0160.setCorpCd("01");
    	t_0160.setUserId(userId);
    	t_0160.setDelFlg("0");

    	String sql = DbUtil.getInstance().getSelectSql(t_0160, SystemConst.PM_KEY_T_0160);

    	t_0160 = (T_0160) DbUtil.getInstance().selectFirstOneRec(t_0160, sql);

		if(t_0160 == null || iHashPassword == null){
			rtnStr = "ＩＤまたはパスワードに誤りがあります";
		}else {
			if(! iHashPassword.equals(t_0160.getPswd())){
				rtnStr = "ＩＤまたはパスワードに誤りがあります";
			}
		}

		return rtnStr;
    }

    /**
     * ユーザ登録画面
     * @param model
     * @return
     */
    @RequestMapping(value = "form", params = "userAdd", method = RequestMethod.POST)
    public String userAdd(Model model, HttpServletRequest request) {
    	FT091F01Form form = new FT091F01Form();

        return Service.getInstance().gotoNextGamen(request, thisForm, form, model);

    }

    @RequestMapping(value = "FT001F01", params = "abcde", method = RequestMethod.POST)
    public String abcde(Model model, HttpServletRequest httpServletRequest) {

    	System.out.println("----------- abcde -----------------------------");

    	return thisGamen;
    }

//------------------------------------------------------------------------------------

    @RequestMapping(value = "form", params = "test", method = RequestMethod.POST)
    public String test(Model model, HttpServletRequest httpServletRequest) {

    	String testNo = thisForm.getTestNo();

    	System.out.println("testNo = " + testNo);

    	return thisGamen;
    }
}
