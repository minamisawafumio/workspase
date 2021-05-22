package jp.co.fm.businessLogic.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.springframework.ui.Model;

import jp.co.fm.businessLogic.system.SystemService;

class ServiceImpl implements Service {

//	private SystemService ss = SystemService.getInstance();

	@Override
	public String getSimpleName(Class inClass) {
		return inClass.getSimpleName();
	}

	@Override
	public String gotoNextGamen( HttpServletRequest request, Object motoForm, Object sakiForm, Model model) {
       	HttpSession httpSession = request.getSession();
    	String mofoFormId = motoForm.getClass().getSimpleName().replaceAll("Form", "");
    	String sakiFormId = sakiForm.getClass().getSimpleName().replaceAll("Form", "");
    	httpSession.setAttribute(mofoFormId + "FORM", motoForm);
    	httpSession.setAttribute(sakiFormId + "FORM", sakiForm);
    	httpSession.setAttribute(sakiFormId + "BACK", mofoFormId);
    	model.addAttribute(sakiFormId + "Form", sakiForm);
    	return sakiFormId;
    }

    @Override
	public String backGamen(HttpServletRequest request, Model model, Object thisForm) {
       	HttpSession httpSession = request.getSession();
    	String thisGamenId = thisForm.getClass().getSimpleName().replaceAll("Form", "");
    	String motoGamenId = (String) httpSession.getAttribute(thisGamenId + "BACK");
    	Object form = httpSession.getAttribute(motoGamenId + "FORM");
    	//セッションタイムアウトの場合、メニュー画面に戻る
    	if(form == null) {
    		return SystemService.getInstance().goMenu(model, request);
    	}
    	model.addAttribute(motoGamenId + "Form", form);
        return motoGamenId;
    }

    /**
     * ログに出力する情報をセッションに出力する
     * @param level
     * @param value
     */
    @Override
	public void log(Level level, String value, HttpSession httpSession) {
    	Object []array = {level, value};
    	httpSession.setAttribute(Const.LOG, array);
    }
}
