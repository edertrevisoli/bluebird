package com.bluebird.config;


import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;


/**
 * This class serves as a custom Spring scope that decorates the map of view-scoped managed-beans behind the JSF2 {@link
 * ViewScoped} annotation.
 *
 * @author  Neil Griffin
 */
public class ViewScope implements Scope {

	public Object get(String name, ObjectFactory<?> objectFactory) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, Object> viewMap = facesContext.getViewRoot().getViewMap();
		Object viewScopedBean = viewMap.get(name);

		if (viewScopedBean == null) {
			viewScopedBean = objectFactory.getObject();
			viewMap.put(name, viewScopedBean);
		}

		return viewScopedBean;
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		// Unsupported feature
	}

	public Object remove(String name) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map<String, Object> viewMap = facesContext.getViewRoot().getViewMap();

		return viewMap.remove(name);
	}

	public Object resolveContextualObject(String key) {

		// Unsupported feature
		return null;
	}

	public String getConversationId() {

		// Unsupported feature
		return null;
	}

}