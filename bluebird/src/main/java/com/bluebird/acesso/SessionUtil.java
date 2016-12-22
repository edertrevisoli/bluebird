package com.bluebird.acesso;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil implements ISessionUtil {
	public static final int SESSION = 1;
	public static final int REQUEST = 2;
	public static final int RESPONSE = 3;
	public static final int EXTERNAL_CONTEXT_JSF = 4;

	public void set(String name, Object object, int to) {
		if (to == 1) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute(name, object);
		} else if (to == 2) {
			getRequest().setAttribute(name, object);
		}
	}

	public Object get(String name, int from) {
		if (from == 1) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			if (session == null)
				return null;
			return session.getAttribute(name);
		}
		if (from == 2) {
			return getRequest().getAttribute(name);
		}
		return null;
	}

	public void remove(String name, int from) {
		if (from == 1) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.removeAttribute(name);
		} else if (from == 2) {
			getRequest().removeAttribute(name);
		}
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request == null) {
			throw new RuntimeException("Requisição inválida.");
		}
		return request;
	}

	public static HttpSession getSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		if (session == null) {
			throw new RuntimeException("Sessão inválida.");
		}
		return session;
	}
}