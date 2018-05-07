package com.kkwriter.gallery.service.product;

import com.kkwriter.gallery.entity.json.ModifyProductJsonBean;
import com.kkwriter.gallery.entity.product.GlyProduct;
import com.kkwriter.gallery.entity.product.GlyProductPicture;
import com.kkwriter.gallery.entity.product.GlyProductProp;
import com.kkwriter.gallery.entity.product.GlyProductType;
import com.kkwriter.gallery.entity.product.GlyRProductProps;
import com.kkwriter.gallery.entity.product.GlyRProductTypeProduct;
import com.kkwriter.gallery.exception.GalleyConsoleException;
import com.kkwriter.gallery.repository.product.GlyProductPictureRepository;
import com.kkwriter.gallery.repository.product.GlyProductPropRepository;
import com.kkwriter.gallery.repository.product.GlyProductRepository;
import com.kkwriter.gallery.repository.product.GlyProductTypeProductRepository;
import com.kkwriter.gallery.repository.product.GlyProductTypeRepository;
import com.kkwriter.gallery.repository.product.GlyRProductPropsRepository;
import com.kkwriter.gallery.result.ReturnEnum;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

/**
 * @author lisha
 */
@Service
public class ProductServiceImpl implements ProductService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final static String PICTURE_BASE_PATH = File.separator + "home" + File.separator + "gallery" +
			File.separator + "apps" + File.separator + "gallery-web" + File.separator + "res" + File.separator + "img" + File.separator;

	@Resource(name = "glyProductRepository")
	private GlyProductRepository glyProductRepository;
	@Resource(name = "glyRProductPropsRepository")
	private GlyRProductPropsRepository glyRProductPropsRepository;
	@Resource(name = "glyProductTypeProductRepository")
	private GlyProductTypeProductRepository glyProductTypeProductRepository;
	@Resource(name = "glyProductPictureRepository")
	private GlyProductPictureRepository glyProductPictureRepository;
	@Resource(name = "glyProductPropRepository")
	private GlyProductPropRepository glyProductPropRepository;
	@Resource(name = "glyProductTypeRepository")
	private GlyProductTypeRepository glyProductTypeRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteProduct(int productId) {
		// 删除产品属性
		glyRProductPropsRepository.deleteByProductId(productId);
		// 删除产品类型
		glyProductTypeProductRepository.deleteByProductId(productId);
		// 删除产品配图
		// 获取所有图片信息
		final List<GlyProductPicture> pictureList = glyProductPictureRepository.findAllByProductId(productId, new Sort(Direction.DESC));
		// 遍历删除
		pictureList.forEach(this::deleteProductPicture);
		// 删除产品
		glyProductRepository.deleteById(productId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void modifyProduct(ModifyProductJsonBean param) {
		int productId = param.getProductId();
		// 根据ID获取产品
		GlyProduct product = glyProductRepository.findById(productId).orElse(null);
		if (product == null) {
			throw new GalleyConsoleException(ReturnEnum.UPDATE_FAILED);
		}
		// 设置新产品信息
		product.setProductName(param.getProductName());
		product.setProductIntro(param.getProductIntro());
		product.setProductDetail(param.getProductDetail());
		product.setInitialPrice(Float.valueOf(param.getInitialPrice()));
		product.setDiscount(Float.valueOf(param.getDiscount()));
		product.setRealPrice(Float.valueOf(rounding(product.getInitialPrice() * product.getDiscount() / 10)));
		product.setInventoryNumber(Integer.valueOf(param.getInventoryNumber()));
		product.setBookNumber(Integer.valueOf(param.getBookNumber()));
		product.setResidueNumber(product.getInventoryNumber() - product.getBookNumber() - product.getSaleNumber());
		product.setProductOrder(Float.valueOf(param.getProductOrder()));
		product.setIsValid(Integer.valueOf(param.getIsValid()));
		product.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		// 更新产品信息
		glyProductRepository.saveAndFlush(product);
		// 操作属性
		List<String> attrList = param.getProductAttrs();
		if (attrList != null && !attrList.isEmpty()) {
			// 删除产品属性
			glyRProductPropsRepository.deleteByProductId(productId);
			// 新建产品属性
			attrList.forEach((attr) -> {
				GlyRProductProps props = new GlyRProductProps();
				props.setId(getProductAttrRelationId());
				props.setProductId(productId);
				props.setPropId(Integer.valueOf(attr));
				// 保存
				glyRProductPropsRepository.saveAndFlush(props);
			});
		}
		// 操作类型
		List<String> typeList = param.getProductTypes();
		if (typeList != null && !typeList.isEmpty()) {
			// 删除产品类型
			glyProductTypeProductRepository.deleteByProductId(productId);
			// 新建产品类型
			typeList.forEach((type) -> {
				GlyRProductTypeProduct types = new GlyRProductTypeProduct();
				types.setId(getProductTypesRelationId());
				types.setProductId(productId);
				types.setProductTypeId(Integer.valueOf(type));
				// 保存
				glyProductTypeProductRepository.saveAndFlush(types);
			});
		}
		// 产品主图
		List<ModifyProductJsonBean.PictureInfo> mainPictures = param.getMainPictures();
		if (mainPictures != null && !mainPictures.isEmpty()) {
			toOperatePicture(mainPictures, productId, 11);
		}
		// 产品详情图
		List<ModifyProductJsonBean.PictureInfo> detailPictures = param.getDetailPictures();
		if (detailPictures != null && !detailPictures.isEmpty()) {
			toOperatePicture(detailPictures, productId, 21);
		}
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	void toOperatePicture(final List<ModifyProductJsonBean.PictureInfo> pictures, final int productId, final int type) {
		pictures.forEach((picture) -> {
			String operateType = picture.getOperateType();
			switch (operateType) {
				case "add":
					addProductPicture(productId, type, picture);
					break;
				case "delete":
					deleteProductPicture(picture);
					break;
				case "modify":
					deleteProductPicture(picture);
					addProductPicture(productId, type, picture);
					break;
				default:
			}
		});
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	void addProductPicture(final int productId, final int type, final ModifyProductJsonBean.PictureInfo picture) {
		// 图片保存到相应主机目录
		String pictureName = savePicture2Disk(productId, picture.getNewPicture());
		// 入库
		GlyProductPicture glyPicture = new GlyProductPicture();
		glyPicture.setProductPictureCode(getProductPictureId());
		glyPicture.setProductId(productId);
		glyPicture.setProductPictureFileName("product" + File.separator + productId + File.separator + pictureName);
		glyPicture.setProductPictureType(type);
		glyPicture.setProductPictureOrder((float) picture.getPosition());
		glyPicture.setIsValid(1);
		glyProductPictureRepository.saveAndFlush(glyPicture);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	void deleteProductPicture(final ModifyProductJsonBean.PictureInfo picture) {
		deleteProductPicture(picture.getOldPicture());
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	void deleteProductPicture(final GlyProductPicture picture) {
		// 数据库删除
		glyProductPictureRepository.delete(picture);
		// 文件系统中删除
		File file = new File(PICTURE_BASE_PATH + picture.getProductPictureFileName());
		// 判断图片是否存在
		if (file.exists()) {
			if (!file.delete()) {
				logger.error("删除图片失败！图片信息：{}", picture.getProductPictureFileName());
			}
		}
	}

	private String savePicture2Disk(final int productId, final String newPicture) {
		byte[] b = Base64.decodeBase64(newPicture.substring(23));
		for (int i = 0; i < b.length; ++i) {
			// 调整异常数据
			if (b[i] < 0) {
				b[i] += 256;
			}
		}
		String pictureName = generatePictureName();
		String imgPath = PICTURE_BASE_PATH + "product" + File.separator + productId;
		File pictureDirectory = new File(imgPath);
		if (!pictureDirectory.exists()) {
			if (!pictureDirectory.mkdirs()) {
				throw new RuntimeException("文件路径创建失败！");
			}
		}
		imgPath += File.separator + pictureName;
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imgPath))) {
			bos.write(b);
			bos.flush();
		} catch (IOException e) {
			logger.error("savePicture2Disk()保存图片发生异常！异常信息：" + e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return pictureName;
	}

	private String generatePictureName() {
		return System.currentTimeMillis() + ".jpg";
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void uploadProductFile(MultipartFile file) {
		try (InputStream in = file.getInputStream(); Workbook wb = WorkbookFactory.create(in)) {
			Sheet sheet = wb.getSheetAt(0);
			int rows = sheet.getLastRowNum() + 1;
			int cellNum;
			Row row;
			Cell cell;
			for (int i = 1; i < rows; i++) {
				GlyProduct product = new GlyProduct();
				String[] props = null;
				String[] types = null;
				String[] pictures = null;
				row = sheet.getRow(i);
				cellNum = row.getLastCellNum();
				for (int j = 0; j < cellNum; j++) {
					cell = row.getCell(j);
					switch (j) {
					case 0:
						product.setProductName(cell.getStringCellValue());
						break;
					case 1:
						product.setProductIntro(cell.getStringCellValue());
						break;
					case 2:
						product.setProductDetail(cell.getStringCellValue());
						break;
					case 3:
						product.setInitialPrice((float) cell.getNumericCellValue());
						break;
					case 4:
						product.setDiscount((float) cell.getNumericCellValue());
						break;
					case 5:
						product.setInventoryNumber((int) cell.getNumericCellValue());
						break;
					case 6:
						product.setProductOrder((float) cell.getNumericCellValue());
						break;
					case 7:
						props = cell.getStringCellValue().trim().split("\\|");
						break;
					case 8:
						types = cell.getStringCellValue().trim().split("\\|");
						break;
					case 9:
						pictures = cell.getStringCellValue().trim().split("\\|");
						break;
					default:
					}
				}
				product.setRealPrice(product.getInitialPrice() * product.getDiscount() / 10);
				product.setSaleNumber(0);
				product.setResidueNumber(product.getInventoryNumber());
				product.setBookNumber(0);
				product.setIsValid(1);
				product.setProductId(getProductId());
				// 保存产品信息
				glyProductRepository.save(product);
				// 保存产品属性
				if (props == null) {
					throw new RuntimeException("产品属性不能为空！");
				}
				GlyRProductProps productProps = new GlyRProductProps();
				productProps.setProductId(product.getProductId());
				for (String s : props) {
					productProps.setId(getProductAttrRelationId());
					productProps.setPropId(Integer.parseInt(s.trim()));
					glyRProductPropsRepository.save(productProps);
				}
				// 保存产品类型
				if (types == null) {
					throw new RuntimeException("产品类型不能为空！");
				}
				GlyRProductTypeProduct productType = new GlyRProductTypeProduct();
				productType.setProductId(product.getProductId());
				for (String s : types) {
					productType.setId(getProductTypesRelationId());
					productType.setProductTypeId(Integer.parseInt(s.trim()));
					glyProductTypeProductRepository.save(productType);
				}
				// 保存产品配图
				if (pictures == null) {
					throw new RuntimeException("产品配图不能为空！");
				}
				GlyProductPicture productPicture = new GlyProductPicture();
				productPicture.setProductId(product.getProductId());
				for (String pic : pictures) {
					String[] args = pic.trim().split("-");
					productPicture.setProductPictureFileName(args[0].trim());
					productPicture.setProductPictureType(Integer.parseInt(args[1].trim()));
					productPicture.setProductPictureOrder(Float.parseFloat(args[2].trim()));
					productPicture.setIsValid(1);
					// 获取配图ID
					productPicture.setProductPictureCode(getProductPictureId());
					glyProductPictureRepository.save(productPicture);
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addProduct(MultipartFile[] productPics, HttpServletRequest request) {
		// 参数校验
		String productName = request.getParameter("productName");
		String productIntro = request.getParameter("productIntro");
		String productDetail = request.getParameter("productDetail");
		String initPrice = request.getParameter("initPrice");
		String discount = request.getParameter("discount");
		String inventory = request.getParameter("inventoryNumber");
		String productOrder = request.getParameter("productOrder");
		String[] attrs = request.getParameterValues("productAttrs");
		String[] types = request.getParameterValues("productTypes");
		if (productName == null || productIntro == null || productDetail == null
				|| initPrice == null || discount == null || inventory == null
				|| attrs == null || types == null || productPics == null || productPics.length == 0) {
			throw new GalleyConsoleException(ReturnEnum.PARAM_ERROR);
		}
		if (productOrder == null) {
			productOrder = "1.0";
		}
		// new 一个产品
		GlyProduct product = new GlyProduct();
		product.setProductName(productName);
		product.setProductIntro(productIntro);
		product.setProductDetail(productDetail);
		product.setInitialPrice(Float.parseFloat(initPrice));
		product.setDiscount(Float.parseFloat(discount));
		product.setRealPrice(product.getInitialPrice() * product.getDiscount() / 10);
		product.setInventoryNumber(Integer.parseInt(inventory));
		product.setSaleNumber(0);
		product.setResidueNumber(product.getInventoryNumber());
		product.setBookNumber(0);
		product.setProductOrder(Float.parseFloat(productOrder));
		product.setIsValid(1);
		// 设置生成的产品ID
		product.setProductId(getProductId());
		// 保存产品
		glyProductRepository.save(product);
		// new 产品属性
		GlyRProductProps productProps = new GlyRProductProps();
		productProps.setProductId(product.getProductId());
		for (String attr : attrs) {
			// 保存一个属性
			// 设置一个 产品-属性记录 ID
			productProps.setId(getProductAttrRelationId());
			productProps.setPropId(Integer.parseInt(attr));
			glyRProductPropsRepository.save(productProps);
		}
		// new 产品类型
		GlyRProductTypeProduct productType = new GlyRProductTypeProduct();
		productType.setProductId(product.getProductId());
		for (String type : types) {
			// 保存一个类型
			// 设置一个 产品-类型 记录ID
			productType.setId(getProductTypesRelationId());
			productType.setProductTypeId(Integer.parseInt(type));
			glyProductTypeProductRepository.save(productType);
		}
		// 保存产品配图
		// 判断base文件夹是否存在，不存在则创建
		File base = new File(PICTURE_BASE_PATH + File.separator + "product" + File.separator + product.getProductId());
		if (!base.exists()) {
			if (!base.mkdirs()) {
				throw new RuntimeException("创建文件夹失败！");
			}
		}
		GlyProductPicture productPicture = new GlyProductPicture();
		productPicture.setProductId(product.getProductId());
		for (int i = 0; i < productPics.length; i++) {
			productPicture.setProductPictureCode(getProductPictureId());
			String fileName = "product" + File.separator + product.getProductId() + File.separator + productPics[i].getOriginalFilename();
			productPicture.setProductPictureFileName(fileName);
			int pictureType = 11;
			if (i != 0) {
				pictureType = 21;
			}
			productPicture.setProductPictureType(pictureType);
			productPicture.setProductPictureOrder(i + 1.0F);
			productPicture.setIsValid(1);
			glyProductPictureRepository.save(productPicture);
			// 将图片保存至对应目录
			try (InputStream io = productPics[i].getInputStream();
				 FileOutputStream fos = new FileOutputStream(new File( PICTURE_BASE_PATH + fileName))) {
				BufferedInputStream bis = new BufferedInputStream(io);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				int n;
				while ((n = bis.read()) != -1) {
					bos.write(n);
				}
				bos.flush();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public Page<GlyProduct> queryProductByPage(Pageable pageable) {
		Page<GlyProduct> products = glyProductRepository.findAll(pageable);
	    if (!products.hasContent()) {
	        throw new GalleyConsoleException(ReturnEnum.DO_NOT_HAVE_DATA);
        }
	    return products;
	}

	@Override
	public GlyProduct getProductInfoById(int id) {
		return glyProductRepository.findById(id).orElse(null);
	}

	@Override
	public List<GlyProductPicture> getAllPictures(int productId) {
		return glyProductPictureRepository.findAllByProductId(productId, new Sort(Direction.ASC, "productPictureType", "productPictureOrder"));
	}

	@Override
	public List<GlyProductProp> queryAllProps() {
		return glyProductPropRepository.findAll(new Sort(Direction.DESC, "creationTime"));
	}

	@Override
	public List<GlyProductType> queryAllTypes() {
		return glyProductTypeRepository.findAll(new Sort(Direction.DESC, "creationTime", "parentProductTypeId"));
	}

	private int getProductId() {
		int productId = 1;
		Page<GlyProduct> page = glyProductRepository.findAll(PageRequest.of(0, 1, new Sort(Direction.DESC, "productId")));
		Iterator<GlyProduct> iterator = page.iterator();
		if (iterator.hasNext()) {
			productId = iterator.next().getProductId() + 1;
		}
		return productId;
	}

	private int getProductAttrRelationId() {
		int id = 1;
		Page<GlyRProductProps> propPage = glyRProductPropsRepository.findAll(PageRequest.of(0, 1, new Sort(Direction.DESC, "id")));
		Iterator<GlyRProductProps> iterator = propPage.iterator();
		if (iterator.hasNext()) {
			id = iterator.next().getId() + 1;
		}
		return id;
	}

	private int getProductTypesRelationId() {
		int id = 1;
		Page<GlyRProductTypeProduct> typePage = glyProductTypeProductRepository.findAll(PageRequest.of(0, 1, new Sort(Direction.DESC, "id")));
		Iterator<GlyRProductTypeProduct> iterator = typePage.iterator();
		if (iterator.hasNext()) {
			id = iterator.next().getId() + 1;
		}
		return id;
	}

	private Integer getProductPictureId() {
		int id = 1;
		Page<GlyProductPicture> picturePage = glyProductPictureRepository.findAll(PageRequest.of(0, 1, new Sort(Direction.DESC, "productPictureCode")));
		Iterator<GlyProductPicture> iterator = picturePage.iterator();
		if (iterator.hasNext()) {
			id = iterator.next().getProductPictureCode() + 1;
		}
		return id;
	}

	private static <T extends Number> String rounding(T number) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		return nf.format(number);
	}

}
