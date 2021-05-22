package jp.co.fm.businessLogic.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.springframework.ui.Model;

public interface Service {

	String getSimpleName(Class inClass);

	String gotoNextGamen(HttpServletRequest request, Object motoForm, Object sakiForm, Model model);

	String backGamen(HttpServletRequest request, Model model, Object form);

	/**
	 * ログに出力する情報をセッションに出力する
	 * @param level
	 * @param value
	 */
	void log(Level level, String value, HttpSession httpSession);

	Service service = new ServiceImpl();

	public static Service getInstance() {
		return service;
	}
}