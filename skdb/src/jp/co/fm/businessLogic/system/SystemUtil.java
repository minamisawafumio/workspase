package jp.co.fm.businessLogic.system;

import java.util.List;

public interface SystemUtil {

	/**
	 * オブジェクトの配列からObjectのリストを作成する
	 * @param objectArray
	 * @return
	 */
	List<Object> makeObjectList(Object[] objectArray);

	List<Object> getObjArray(Object obj1);

	List<Object> getObjArray(Object obj1, Object obj2);

	/**
	 * 指定したクラスが配備されているプロジェクトのパスを返却する
	 * @param inClass
	 * @return
	 */
	String getProjectDir(Class inClass);

	SystemUtil systemUtil = new SystemUtilImpl();

	public static SystemUtil getInstance() {
		return systemUtil;
	}
}