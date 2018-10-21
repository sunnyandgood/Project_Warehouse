package com.edu.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.entity.StoreEntity;
import com.edu.service.StoreService;
import com.edu.service.impl.StoreServiceImpl;

public class StoreServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private StoreService storeService = new StoreServiceImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String state = request.getParameter("state");
		if("selectStore".equals(state)) {
			this.displaySelectStore(request,response);
		}else {
			this.displayAllStore(request,response);
		}
		
	}
	
	/*
	 *  展示所有商品 
	 */
	private void displayAllStore(HttpServletRequest request, HttpServletResponse response) {
		this.shoppingCartOperation(request, response);
		List<StoreEntity> displayList = storeService.selectAll();
		request.setAttribute("displayList", displayList);
		try {
			request.getRequestDispatcher("/store.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 展示选择的商品
	 */
	private void displaySelectStore(HttpServletRequest request, HttpServletResponse response) {
		this.shoppingCartOperation(request, response);
		//1、接收参数
		String storeName = request.getParameter("storeName");
		String storePrice = request.getParameter("storePrice");
		//2、做简单处理（封装）
		//3、调用方法
		List<StoreEntity> list = storeService.selectStore(storeName, storePrice);
		request.setAttribute("displayList", list);
		try {
			request.getRequestDispatcher("/store.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 购物车操作（添加、删除、展示）
	 */
	private void shoppingCartOperation(HttpServletRequest request, HttpServletResponse response) {
		List<StoreEntity> list = new LinkedList<>();
		Double priceCount = 0.0;
		HttpSession session = request.getSession();
		//接收addStoreId参数
		String addStoreId = request.getParameter("addStoreId");
		if(null != addStoreId) {
			StoreEntity storeEntity = storeService.selectStoreById(Integer.parseInt(addStoreId));
			session.setAttribute(storeEntity.getS_id(), storeEntity);
		}
		//接收deleteStoreId参数
		String deleteStoreId = request.getParameter("deleteStoreId");
		if(null != deleteStoreId) {
			session.removeAttribute(deleteStoreId);
		}
		Enumeration<String> names = session.getAttributeNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			StoreEntity storeEntity = (StoreEntity) session.getAttribute(name);
			list.add(storeEntity);
			String price = storeEntity.getS_price();
			if(null != price) {
				priceCount = priceCount + Double.parseDouble(price);
			}
		}
		request.setAttribute("shoppingList", list);
		request.setAttribute("pageCount", list.size());
		request.setAttribute("priceCount", priceCount);
	}
}
