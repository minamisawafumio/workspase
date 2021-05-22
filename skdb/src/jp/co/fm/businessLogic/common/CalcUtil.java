package jp.co.fm.businessLogic.common;

import java.util.Map;

public interface CalcUtil {

	/**
	 * �?���?怜�励?��險育?��励�?繧?��
	 * @param calc
	 * @return
	 */
	double calc(String calc);

	/**
	 * �?���?怜�励?��?���??��螳?��繧堤?��?���?帙�?縺?��險育?��励�?繧?��
	 * @param calc
	 * @param keyMap
	 * @return
	 */
	double calc(String calc, Map<String, String> keyMap);

	CalcUtil calcUtil = new CalcUtilImpl();

	public static CalcUtil getInstance() {
		return calcUtil;
	}
}