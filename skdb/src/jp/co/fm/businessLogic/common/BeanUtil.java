package jp.co.fm.businessLogic.common;

import java.util.Map;

public interface BeanUtil {

	/**
	 * Beanを作成する
	 * @param obj
	 * @param map
	 * @return
	 */
	public Object makeBean(Object obj, Map<String, Object> map);

	/**
	 * Beanの指定メンバにNullをセットして返す
	 * @param obj
	 * @param mambers
	 * @return
	 */
	public Object beanSetNull(Object obj, String[] mambers);

	/**
	 * Beanをコピーする
	 * @param object1
	 * @return
	 */
	public Object copyBean(Object object1);

	/**
	 * オブジェクトをコンソールに出力する
	 * @param object1
	 */
	public void printBean(Object object1);

	public BeanUtil beanUtil = new BeanUtilImpl();

	public static BeanUtil getInstance() {
		return beanUtil;
	}

}