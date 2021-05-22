package test.annotation2.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import test.annotation2.annotation.Resource;

public class Container {
	public Object getInsrance(Class clazz) {

		Object obj = null;

		try {
			obj = clazz.getConstructor().newInstance();

			Field[] fields = clazz.getDeclaredFields();

			for(Field f : fields) {
				Annotation[] annotatins = f.getAnnotations();
				for(Annotation an : annotatins) {
					if(an instanceof Resource) {
						Resource resource = (Resource) an;
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
