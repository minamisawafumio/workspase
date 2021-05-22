package jp.co.fm.businessLogic.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectionUtilImpl implements ReflectionUtil {

	/**
	 * クラス（インスタンス）のフィールドに値を設定する
	 * @param clsInst
	 * @param methodName
	 * @param fieldName
	 * @param value
	 */
	@Override
	public void setValueToField(Object clsInst, String fieldName ,Object value){
		Field []fields = clsInst.getClass().getDeclaredFields();

		for(int i =0; i < fields.length; i++){
			if(fieldName.equals(fields[i].getName())){
				fields[i].setAccessible(true);
				try {
					fields[i].set(clsInst, value);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
     * オブジェクトを取得する
     * @param tableObject
     * @param getterMethod
     * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
     */
    @Override
	public Object doGetterMethod(Object tableObject, String getterMethod) throws
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        Class[] argType = {};
        Object[] args = {};
		Object object = invoke(tableObject, getterMethod, argType, args);
		return object;
    }

	/**
	 * メソッドの型を取得する
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	@Override
	public String getMethodType(Object obj, String fieldName){
		Field []fields = obj.getClass().getDeclaredFields();

		for(int i =0; i < fields.length; i++){
			if(fieldName.equals(fields[i].getName())){
				Class cls = fields[i].getType();
				return cls.getSimpleName();
			}
		}
		return null;
	}

	/**
	 * クラス（インスタンス）のフィールドの値を取得する
	 * @param clsInst
	 * @param fieldName
	 * @param value
	 */
	@Override
	public Object getValueOfField(Object clsInst, String fieldName){
		Field []fields = clsInst.getClass().getDeclaredFields();

		for(int i =0; i < fields.length; i++){
			if(fieldName.equals(fields[i].getName())){
				fields[i].setAccessible(true);
				try {
					return fields[i].get(clsInst);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * クラス（インスタンス）のメソッド（文字列）を実行する
	 * @param clsInst
	 * @param methodName
	 * @param argType
	 * @param args
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Override
	public Object invoke(Object clsInst, String methodName, Class[] argType, Object[] args) throws
										IllegalAccessException, IllegalArgumentException, InvocationTargetException,
										NoSuchMethodException, SecurityException{
		Class cls = clsInst.getClass();
		Method method = cls.getDeclaredMethod(methodName, argType);
		method.setAccessible(true);
		return method.invoke(clsInst, args);
	}

    /**
     * 指定したクラス名が存在するか？
     * @param className
     * @return
     */
	@Override
	public Boolean isExistClass(String className){
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * 指定したクラス内に指定(シンプル)メソッド名が存在するか？
	 * @param obj
	 * @param methodName
	 * @return
	 */
	@Override
	public Boolean isExistMethodName(Object obj, String _methodName){
		Method []methodArray = obj.getClass().getMethods();

		for(Method method: methodArray){
			String methodName = method.getName();
			if(methodName.equals(_methodName)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 文字列よりクラスのインスタンスを作成する
	 * @param className
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object create(String className) throws Exception {

		// 文字列からクラスを取得
		Class<?> clazz = Class.forName(className);

		// 指定されたパラメータでコンストラクタ取得
		// (今回はコンストラクタの引数はない。)
		Constructor<?> constructor = clazz.getConstructor(new Class<?>[0]);

		// インスタンス生成
		Object instance = constructor.newInstance();

		return  instance;
	}

}
