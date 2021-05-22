package jp.co.fm.businessLogic.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import jp.co.fm.businessLogic.annotation.MyResource;



public class Container {
	public Object getInsrance(Class clazz) {

		Object obj = null;

		try {
			obj = clazz.getConstructor().newInstance();

			Field[] fields = clazz.getDeclaredFields();

			for(Field f : fields) {
				Annotation[] annotatins = f.getAnnotations();
				for(Annotation an : annotatins) {
					if(an instanceof MyResource) {
						MyResource resource = (MyResource) an;
						Class target = resource.value();
						f.setAccessible(true);
						f.set(obj, target.getConstructor().newInstance());
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return obj;
	}
}
