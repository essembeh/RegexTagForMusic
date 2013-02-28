package org.essembeh.rtfm.core.utils.runtime;

public class RuntimeInstanceFactory {

	/**
	 * 
	 * @param classname
	 * @return
	 * @throws RuntimeInstanceException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(String classname) throws RuntimeInstanceException {
		T out = null;
		try {
			Class<?> clazz = Class.forName(classname);
			Object object = clazz.newInstance();
			out = (T) object;
		} catch (Exception e) {
			throw new RuntimeInstanceException(e);
		}
		return out;
	}
}
