package com.kkwriter.gallery.service.product;

import java.util.List;

import com.kkwriter.gallery.entity.product.GlyProduct;
import com.kkwriter.gallery.entity.product.GlyProductPicture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.kkwriter.gallery.entity.product.GlyProductProp;
import com.kkwriter.gallery.entity.product.GlyProductType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lisha
 */
public interface ProductService {

	/**
	 * 商品导入
	 * @param file 文件
	 */
	void uploadProductFile(MultipartFile file);

	/**
	 * 查询所有的商品属性
	 * @return list
	 */
	List<GlyProductProp> queryAllProps();

	/**
	 * 查询所有的商品类型
	 * @return list
	 */
	List<GlyProductType> queryAllTypes();

	/**
	 * 添加商品
	 * @param productPics 商品配图
	 * @param request 该请求
	 */
	void addProduct(MultipartFile[] productPics, HttpServletRequest request);

	/**
	 * 分页查询商品
	 * @param pageable 分页参数
	 * @return 商品信息
	 */
    Page<GlyProduct> queryProductByPage(Pageable pageable);

	/**
	 * 通过ID查询商品信息
	 * @param id 商品ID
	 * @return 查询到的商品
	 */
	GlyProduct getProductInfoById(int id);

	/**
	 * 查询指定ID商品的所有配图
	 * @param productId 商品ID
	 * @return 指定商品的所有配图
	 */
    List<GlyProductPicture> getAllPictures(int productId);
}
