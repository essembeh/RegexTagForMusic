package org.essembeh.rtfm.app.utils;

public class InstanceUtils {
	/**
	 * 
	 * @param classname
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws RuntimeInstanceException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(String classname) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		T out = null;
		Class<?> clazz = Class.forName(classname);
		Object object = clazz.newInstance();
		out = (T) object;
		return out;
	}
}
