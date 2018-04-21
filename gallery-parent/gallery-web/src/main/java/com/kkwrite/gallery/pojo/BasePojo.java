package com.kkwrite.gallery.pojo;

public class BasePojo {

	/**
	 * 无效
	 */
	public static final int IS_VALID_N = 0;
	/**
	 * 有效
	 */
	public static final int IS_VALID_Y = 1;
	
	/**
	 * 产品类型字典类
	 */
	public static final class ProductType {
		
		/**
		 * 风格父类
		 */
		public static final int PARENT_PRODUCT_TYPE_STYLE = 1;
		
		/**
		 * 空间父类
		 */
		public static final int PARENT_PRODUCT_TYPE_SPACE = 9;
		
		/**
		 * 特色父类
		 */
		public static final int PARENT_PRODUCT_TYPE_FEATURE = 2;
		
		/**
		 * 新品
		 */
		public static final int PRODUCT_TYPE_NEW = 17;
		
		/**
		 * 折扣
		 */
		public static final int PRODUCT_TYPE_DISCOUNT = 18;
		
		/**
		 * 好物
		 */
		public static final int PRODUCT_TYPE_GOODS = 19;
	}
	
	/**
	 * 
	 * 预约类型枚举
	 * 
	 */
	public enum ReservationTypeEnum {
		MEASUREMENTDESIGN("测量设计", 1),
		OLDTONEW("以旧换新", 2),
		MAINTENANCE("维修保养", 3),
		WORKSFRAMED("作品装裱", 4)
		;
		private String reservationName;
		private int reservationType;
		ReservationTypeEnum(String reservationName, int reservationType) {
			this.reservationName = reservationName;
			this.reservationType = reservationType;
		}
		
		public String getReservationName() {
			return reservationName;
		}

		public void setReservationName(String reservationName) {
			this.reservationName = reservationName;
		}

		public int getReservationType() {
			return reservationType;
		}

		public void setReservationType(int reservationType) {
			this.reservationType = reservationType;
		}

		public static String getReservationName(int type) {
			for (ReservationTypeEnum e : ReservationTypeEnum.values()) {
				if (e.reservationType == type) {
					return e.reservationName;
				}
			}
			return null;
		}
		
	}
	
	/**
	 * 预约状态常量表
	 */
	public static final class ReservationStatus {
		/**
		 * 用户预约<br>
		 * 0
		 */
		public static final int RESERVATION_STATUS_BEGIN = 0;
		
		/**
		 * 预约处理中<br>
		 * 1
		 */
		public static final int RESERVATION_STATUS_HANDLING = 1;
		
		/**
		 * 预约处理完成<br>
		 * 2
		 */
		public static final int RESERVATION_STATUS_FINISHED = 2;
		
	}
	
	/**
	 * 订单常量字典
	 */
	public static final class OrderDict {
		/**
		 * 待付款
		 */
		public static final int ORDER_STATUS_WAITING_PAY = 1;
		
		/**
		 * 已支付
		 */
		public static final int ORDER_STATUS_PAYED = 2;
		
		/**
		 * 待收货
		 */
		public static final int ORDER_STATUS_WAITING_RECV = 3;
		
		/**
		 * 已完成
		 */
		public static final int ORDER_STATUS_FINISHED = 4;
		/**
		 * 订单过期
		 */
		public static final int ORDER_STATUS_EXPIRED = 5;
		/**
		 * 订单取消
		 */
		public static final int ORDER_STATUS_CANCELED = 6;
		/**
		 * 获取订单状态文字
		 */
		public static String getOrderStatus(int orderStatus) {
			String orderStatusText = "";
			switch (orderStatus) {
			case ORDER_STATUS_WAITING_PAY:
				orderStatusText = "待付款";
				break;
			case ORDER_STATUS_PAYED:
				orderStatusText = "已支付";
				break;
			case ORDER_STATUS_WAITING_RECV:
				orderStatusText = "待收货";
				break;
			case ORDER_STATUS_FINISHED:
				orderStatusText = "已完成";
				break;
			case ORDER_STATUS_EXPIRED:
				orderStatusText = "已过期";
				break;
			case ORDER_STATUS_CANCELED:
				orderStatusText = "已取消";
				break;
			default:
				break;
			}
			return orderStatusText;
		}
		
		/**
		 * 产品订单 
		 */
		public static final int ORDER_TYPE_PRODUCT = 1;
		
		/**
		 * 预约订单
		 */
		public static final int ORDER_TYPE_RESERVATION = 2;
	}
	
}
