package backend.model.dao;

import java.util.List;

import backend.model.entity.SubgroupEntity;

public interface SubgroupDao {

	void save(SubgroupEntity obj);
	void update(SubgroupEntity obj);
	void deleteById(Long id);
	SubgroupEntity findById(Long id);
	List<SubgroupEntity> findAll();
}
