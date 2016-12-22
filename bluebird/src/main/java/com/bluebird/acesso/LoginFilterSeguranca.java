package com.bluebird.acesso;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD }, urlPatterns = { "*" })
public class LoginFilterSeguranca extends LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter) throws IOException, ServletException {
		try {	
					
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
									
			if (!hasUserOnSession(request))
				if (request.getUserPrincipal() != null) {
					putAuthorizationsOnSession(request);
					response.sendRedirect(getPgInicial(getUserOnSession(request)));
				}

			if (hasUserOnSession(request))
				if (!hasPermission(request))
					response.sendRedirect("../inicio/index.jsf");//tem login, mas nao tem nenhuma permissao, joga para pagina inicial
						
			filter.doFilter(req, resp);

		} catch (SecurityException se) {
			System.err.println("LoginContext cannot be created. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
