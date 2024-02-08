package backend.model.service;

import java.util.List;

import backend.model.dao.ProductDao;
import backend.model.dao.factory.ProductFactory;
import backend.model.entity.ProductEntity;

public class ProductService {

	private ProductDao productDao = ProductFactory.createProductDao();
	
	public void saveOrUpdate(ProductEntity productEntity) {
		if (productEntity.getId() == null) {
			productDao.save(productEntity);
		} else {
			productDao.update(productEntity);
		}
	}
	
	public void delete(ProductEntity productEntity) {
		productDao.deleteById(productEntity.getId());
	}
	
	public ProductEntity findById(ProductEntity productEntity) {
		return productDao.findById(productEntity.getId());
	}
	
	public List<ProductEntity> findAll() {
		return productDao.findAll();
	}
	
}
