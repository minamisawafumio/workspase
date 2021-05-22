package jp.co.fm.businessLogic.common;

import java.lang.reflect.InvocationTargetException;

public interface ReflectionUtil {

	/**
	 * クラス（インスタンス）のフィールドに値を設定する
	 * @param clsInst
	 * @param methodName
	 * @param fieldName
	 * @param value
	 */
	void setValueToField(Object clsInst, String fieldName, Object value);

	/**
	 * オブジェクトを取得する
	 * @param tableObject
	 * @param getterMethod
	 * @return
	 */
	Object doGetterMethod(Object tableObject, String getterMethod) throws
				IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;

	/**
	 * メソッドの型を取得する
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	String getMethodType(Object obj, String fieldName);

	/**
	 * クラス（インスタンス）のフィールドの値を取得する
	 * @param clsInst
	 * @param fieldName
	 * @param value
	 */
	Object getValueOfField(Object clsInst, String fieldName);

	/**
	 * クラス（インスタンス）のメソッド（文字列）を実行する
	 * @param clsInst
	 * @param methodName
	 * @param argType
	 * @param args
	 * @return
	 */
	Object invoke(Object clsInst, String methodName, Class[] argType, Object[] args) throws
	IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	NoSuchMethodException, SecurityException;

	/**
	 * 指定したクラス名が存在するか？
	 * @param className
	 * @return
	 */
	Boolean isExistClass(String className);

	/**
	 * 指定したクラス内に指定(シンプル)メソッド名が存在するか？
	 * @param obj
	 * @param methodName
	 * @return
	 */
	Boolean isExistMethodName(Object obj, String _methodName);

	/**
	 * 文字列よりクラスのインスタンスを作成する
	 * @param className
	 * @return
	 * @throws Exception
	 */
	Object create(String className) throws Exception;

	ReflectionUtil reflectionUtil = new ReflectionUtilImpl();

	public static ReflectionUtil getInstance() {
		return reflectionUtil;
	}

}