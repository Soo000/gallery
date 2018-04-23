package com.kkwriter.gallery.service.product;

import java.util.List;

import com.kkwriter.gallery.entity.product.GlyProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.kkwriter.gallery.entity.product.GlyProductProp;
import com.kkwriter.gallery.entity.product.GlyProductType;

import javax.servlet.http.HttpServletRequest;

public interface ProductService {
	
	void uploadProductFile(MultipartFile file);

	List<GlyProductProp> queryAllProps();
	
	List<GlyProductType> queryAllTypes();

	void addProduct(MultipartFile[] productPics, HttpServletRequest request);

    Page<GlyProduct> queryProductByPage(Pageable pageable);
}
