package cn.gxkjdx.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PowerInterceptor implements HandlerInterceptor {
	
	private final static Logger logger=LogManager.getLogger(PowerInterceptor.class);
	@SuppressWarnings({"unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		logger.debug("路径："+requestURI);
		session.setAttribute("path", requestURI);
		Map<String, Object> admin = (Map<String, Object>) session.getAttribute("admin");
		Map<String, Object> role = (Map<String, Object>)admin.get("role");
		if(null!=role&&!"".equals(role)){
			List<Map<String, Object>> powers =(List<Map<String, Object>>) role.get("powers");
			if(null!=powers&&!"".equals(powers)){
				for (Map<String, Object> power : powers) {
					if(requestURI.contains((String)power.get("power_action"))) {
						return true;
					}
				}
			}
		}
		request.setAttribute("admin_login_msg", "你没有此权限,请先获取权限");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
