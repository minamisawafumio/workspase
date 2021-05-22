package jp.co.fm.businessLogic.system;

import java.util.ArrayList;
import java.util.List;

class SystemUtilImpl implements SystemUtil {
	/**
	 * オブジェクトの配列からObjectのリストを作成する
	 * @param objectArray
	 * @return
	 */
    @Override
	public List<Object> makeObjectList(Object []objectArray) {
    	List<Object> list = new ArrayList<>();
    	for(Object object :objectArray){
    		list.add(object);
    	}
    	return list;
    }

	@Override
	public List<Object> getObjArray(Object obj1){
		List<Object> objectList = new ArrayList<>();
		objectList.add(obj1);
		return objectList;
	}

	@Override
	public List<Object> getObjArray(Object obj1, Object obj2){
		List<Object> objectList = new ArrayList<>();
		objectList.add(obj1);
		objectList.add(obj2);
		return objectList;
	}

	/**
	 * 指定したクラスが配備されているプロジェクトのパスを返却する
	 * @param inClass
	 * @return
	 */
	@Override
	public String getProjectDir(Class inClass) {
		String simpleName = inClass.getSimpleName() + ".class";
    	String userDir = inClass.getResource(simpleName).toString();
    	return userDir;
	}
}
