package backend.model.dao;

import java.util.List;

import backend.model.entity.ProductEntity;

public interface ProductDao {

	void save(ProductEntity obj);
	void update(ProductEntity obj);
	void deleteById(Long id);
	ProductEntity findById(Long id);
	List<ProductEntity> findAll();
}
