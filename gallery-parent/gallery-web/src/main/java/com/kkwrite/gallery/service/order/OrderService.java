package com.kkwrite.gallery.service.order;

import java.util.List;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.pojo.address.GlyAddress;
import com.kkwrite.gallery.pojo.order.GlyOrder;
import com.kkwrite.gallery.pojo.order.Order;
import com.kkwrite.gallery.pojo.order.OrderProducts;
import com.kkwrite.gallery.pojo.product.GlyProductInstance;

public interface OrderService {
	
	public List<GlyOrder> getMyOrder(int userId) throws ServiceException;
	
	public Order getOrder(String orderCode) throws ServiceException;
	
	public List<OrderProducts> getOrdersProducts(List<GlyOrder> orders) throws ServiceException;

	public boolean saveOrder(GlyOrder glyOrder, List<GlyProductInstance> glyProductInstances,
			GlyAddress glyAddress, String subType) throws ServiceException;
	
	public int setOrderStatus(String orderCode, int orderStatus) throws ServiceException;
	
	public int deleteOrder(String orderCode) throws ServiceException;
	
}