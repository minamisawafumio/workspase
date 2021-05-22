package jp.co.fm.businessLogic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.fm.businessLogic.common.Const;
import jp.co.fm.businessLogic.common.DbUtil;
import jp.co.fm.businessLogic.system.SystemConst;
import jp.co.fm.businessLogic.table.T_0160;
import jp.co.fm.presentation.form.FT001F01Form;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FT000001 {

	private static final Logger logger = LoggerFactory.getLogger(FT000001.class);

//	private DbUtil du = DbUtil.getInstance();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest httpServletRequest) {

    	HttpSession httpSession = httpServletRequest.getSession();

    	String userId		 = (String) httpSession.getAttribute(Const.USER_ID);
    	String hashPassword = (String) httpSession.getAttribute(Const.USER_PASSWORD);

       	System.out.println("userId=" + userId);
       	System.out.println("password=" + hashPassword);

       	String msg = validate(userId, hashPassword);

       	if("".equals(msg)) {
       		return gotoMenuPage(model, httpServletRequest);
       	} else {
    		FT001F01Form fT001F01Form = new FT001F01Form();

    		fT001F01Form.setMessage("");

            model.addAttribute("FT001F01Form", fT001F01Form);

           	httpSession.setAttribute("fT001F01Form", fT001F01Form);

    		return "FT001F01";
       	}
	}

    /**
     * 入力内容チェック処理
     * @param thisForm
     * @return
     */
	private String validate(String userId, String iHashPassword){
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

	private String gotoMenuPage(Model model, HttpServletRequest httpServletRequest) {
   		FT001F01Form FT001F01Form = new FT001F01Form();
   		FT001F01 fT001F01 = new FT001F01();
		fT001F01.init(FT001F01Form);
   		return fT001F01.gotoFirstGamen(model, httpServletRequest);
	}
}
