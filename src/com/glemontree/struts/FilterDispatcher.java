package com.glemontree.struts;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FilterDispatcher
 */
public class FilterDispatcher implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//1. ªÒ»°ServletPath
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		String path = null;
		if ("/product-input.action".equals(servletPath)) {
			path = "/WEB-INF/pages/input.jsp";
		}
		if ("/product-save.action".equals(servletPath)) {
			String productName = request.getParameter("productName");
			String productDesc = request.getParameter("productDesc");
			String productPrice = request.getParameter("productPrice");
			Product product = new Product(null, productName, productDesc, Double.parseDouble(productPrice));
			System.out.println("Save Product: " + product);
			product.setProductId(1001);
			request.setAttribute("product", product);
			path = "/WEB-INF/pages/details.jsp";
		}
		if (path != null) {
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
