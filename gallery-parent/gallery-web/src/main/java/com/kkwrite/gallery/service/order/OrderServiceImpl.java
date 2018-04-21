package com.kkwrite.gallery.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kkwrite.gallery.exception.ServiceException;
import com.kkwrite.gallery.mapper.order.GlyOrderMapper;
import com.kkwrite.gallery.mapper.product.GlyProductInstanceMapper;
import com.kkwrite.gallery.pojo.address.GlyAddress;
import com.kkwrite.gallery.pojo.order.GlyOrder;
import com.kkwrite.gallery.pojo.order.Order;
import com.kkwrite.gallery.pojo.order.OrderProducts;
import com.kkwrite.gallery.pojo.product.GlyProductInstance;
import com.kkwrite.gallery.pojo.product.Product;
import com.kkwrite.gallery.pojo.user.GlyUser;
import com.kkwrite.gallery.service.cart.CartService;
import com.kkwrite.gallery.service.product.ProductService;
import com.kkwrite.gallery.service.user.UserService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private CartService carService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private GlyOrderMapper glyOrderMapper;
	@Autowired
	private GlyProductInstanceMapper glyProductInstanceMapper;
	
	@Override
	public List<GlyOrder> getMyOrder(int userId) throws ServiceException {
		try {
			GlyOrder params = new GlyOrder();
			params.setUserId(userId);
			List<GlyOrder> orders = glyOrderMapper.selectSelective(params);
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("根据 userId 查询订单出错");
		}
	}
	
	@Override
	public Order getOrder(String orderCode) {
		Order order =  (Order) glyOrderMapper.selectByPrimaryKey(orderCode);
		return order;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=ServiceException.class)
	public boolean saveOrder(GlyOrder glyOrder, List<GlyProductInstance> glyProductInstances,
			GlyAddress glyAddress, String subType) throws ServiceException {
		
		if (glyOrder == null) {
			throw new ServiceException("订单数据不能为空");
		}
		if (glyProductInstances == null || glyProductInstances.size() <= 0) {
			throw new ServiceException("产品数据不能为空");
		}
		
		// 插入订单表
		int result = glyOrderMapper.insertSelective(glyOrder);
		if (result != 1) {
			throw new ServiceException("插入订单表失败");
		}

		int[] productIds = new int[glyProductInstances.size()];
		// 插入产品实例表
		for (int i = 0; i < glyProductInstances.size(); i++) {
			result = glyProductInstanceMapper.insertSelective(glyProductInstances.get(i));
			productIds[i] = glyProductInstances.get(i).getProductId();
			if (result != 1) {
				throw new ServiceException("插入产品实例表失败");
			}
		}

		// 判断提交方式，如果加入购物车后提交订单，应该将购物车中的产品删除，如果是立即购买，则不需要删除
		if ("addToCart".equalsIgnoreCase(subType)) {
			GlyUser glyUser = userService.queryUserByUserId(glyOrder.getUserId());
			// 删除购物车中的产品
			boolean isRemoved = carService.removeFromCart(glyUser.getUsername(), productIds);
			if (!isRemoved) {
				throw new ServiceException("生成订单时，移除购物车中产品出错");
			}
		}
		
		return true;
	}

	/**
	 * 查询订单产品
	 */
	@Override
	public List<OrderProducts> getOrdersProducts(List<GlyOrder> orders) throws ServiceException {
		if (orders == null || orders.size() <= 0) return null;
		
		List<OrderProducts> ordersProducts = new ArrayList<OrderProducts>();
		for (GlyOrder order: orders) {
			OrderProducts orderProducts = new OrderProducts();
			orderProducts.setOrderCode(order.getOrderCode());
			orderProducts.setOrderStatus(order.getOrderStatus());
			orderProducts.setRealPayment(order.getRealPayment());
			orderProducts.setProductNum(order.getProductNum());
			orderProducts = getOrdesProductsDetails(orderProducts);
			if (orderProducts != null) {
				ordersProducts.add(orderProducts);
			}
		}
		
		return ordersProducts;
	}
	
	private OrderProducts getOrdesProductsDetails(OrderProducts orderProducts) throws ServiceException {
		if (orderProducts == null) return null;
		
		Map<String, String> orderParams = new HashMap<String, String>();
		orderParams.put("orderCode", orderProducts.getOrderCode());
		List<Map<String, Object>> resultOrderProducts = glyOrderMapper.selectOrderProducts(orderParams);
		if (resultOrderProducts != null) {
			// 设置产品类别数量
			orderProducts.setProductClassNum(resultOrderProducts.size()); 
			int[] productIds = new int[resultOrderProducts.size()];
			for (int i = 0; i < resultOrderProducts.size(); i++) {
				productIds[i] = Integer.parseInt(resultOrderProducts.get(i).get("productId").toString());
			}
			
			List<Product> products = productService.queryProduct(productIds);
			if (products != null) {
				orderProducts.setProducts(products);
			}
		}
		
		return orderProducts;
	}

	/**
	 * 设置订单状态
	 */
	@Override
	public int setOrderStatus(String orderCode, int orderStatus) throws ServiceException {
		GlyOrder glyOrderParam = new GlyOrder();
		glyOrderParam.setOrderCode(orderCode);
		glyOrderParam.setOrderStatus(orderStatus);
		return glyOrderMapper.updateByPrimaryKeySelective(glyOrderParam);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=ServiceException.class)
	public int deleteOrder(String orderCode) throws ServiceException {
		int result = glyOrderMapper.deleteByPrimaryKey(orderCode);
		if (result < 0) {
			throw new ServiceException("删除订单表出错");
		}
		result = glyProductInstanceMapper.deleteByOrderCode(orderCode);
		if (result < 0) {
			throw new ServiceException("删除订单实例表出错");
		}
		return result;
	}

}
