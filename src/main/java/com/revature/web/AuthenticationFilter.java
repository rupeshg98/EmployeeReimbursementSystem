package com.revature.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */

/*
 * This class is a filter; it's just a special servlet implementation
 * that intercepts requests before they make it to their destination.
 */
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/*
	 * We place our logic for filtering requests inside of the doFilter 
	 * method.
	 */
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/*
		 * Note that filters can be used with any generic servlet. As
		 * a result, the parameters are generic ServletRequest and
		 * ServletResponse(s). As a result, we must downcast our parameters
		 * because ServletRequest doesn't have access to the getSession method
		 * which returns an HttpSession.
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//Get HttpSession information because we are checking for existence of a session
		
		HttpSession session = req.getSession(false);
		
		if(session == null) {
			res.getWriter().write("Get out of here! Please login first.");
		}

		// pass the request along the filter chain
		else chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
