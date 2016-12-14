package com.bluebird.comum.util;

import java.lang.reflect.Method;

/**
 * Reflection utils.
 * 
 */
public final class ReflectionUtils {

	private static final String GETTER_PREFIX = "get";

	private static final String SETTER_PREFIX = "set";

	/**
	 * Private constructor to prevent instantiation.
	 */
	private ReflectionUtils() {
	}

	/**
	 * Returns the getter method for the property, or <code>null</code> if not
	 * found.
	 * 
	 * @param propertyName
	 *            the property name
	 * @return the getter method for the property, or <code>null</code> if not
	 *         found.
	 */
	public static Method findGetter(final Object bean, final String propertyName) {
		Method getter = null;
		final String methodNameSuffix = capitalize(propertyName);
		final String getterName = new StringBuffer().append(GETTER_PREFIX).append(methodNameSuffix).toString();

		if (getter == null) {
			for (Method method : bean.getClass().getMethods()) {
				if (getterName.equals(method.getName())) {
					getter = method;
					break;
				}
			}
		}
		return getter;
	}

	/**
	 * Returns the setter method for the property, or <code>null</code> if not
	 * found.
	 * 
	 * @param propertyName
	 *            the property name
	 * @return the setter method for the property, or <code>null</code> if not
	 *         found.
	 */
	public static Method findSetter(final Object bean, final String propertyName) {
		Method setter = null;
		final String methodNameSuffix = capitalize(propertyName);
		final String setterName = new StringBuffer().append(SETTER_PREFIX).append(methodNameSuffix).toString();

		if (setter == null) {
			for (Method method : bean.getClass().getMethods()) {
				if (setterName.equals(method.getName())) {
					setter = method;
					break;
				}
			}
		}

		return setter;
	}

	private static String capitalize(String str) {
		if ((str == null) || ((str.length()) == 0))
			return str;

		return Character.toTitleCase(str.charAt(0)) + str.substring(1);
	}
}
