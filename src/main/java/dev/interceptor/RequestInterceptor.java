package dev.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import dev.config.TenantContext;

@Component
public class RequestInterceptor implements HandlerInterceptor {
	private static Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		String tenantId = request.getHeader("tenantId");
		log.info("Request URI: {}", requestURI);
		log.info("Tenant Id: {}", tenantId);

		if (tenantId == null) {
			response.getWriter().write("Tenant Id is not present in the request header");
			response.setStatus(400);
			return false;
		}
		TenantContext.setCurrentTenant(tenantId);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		log.info("Clear tenant context");
		TenantContext.clear();
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		if (ex != null) {
			ex.printStackTrace();
			log.error("An exception occurred with message: {}", ex.getMessage());
		}

	}
}
