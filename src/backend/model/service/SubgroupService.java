package backend.model.service;

import java.util.List;

import backend.model.dao.SubgroupDao;
import backend.model.dao.factory.SubgroupFactory;
import backend.model.entity.SubgroupEntity;

public class SubgroupService {

	private SubgroupDao subgroupDao = SubgroupFactory.createSubgroupDao();
	
	public void saveOrUpdate(SubgroupEntity subgroupEntity) {
		if (subgroupEntity.getId() == null) {
			subgroupDao.save(subgroupEntity);
		} else {
			subgroupDao.update(subgroupEntity);
		}
	}
	
	public void delete(SubgroupEntity subgroupEntity) {
		subgroupDao.deleteById(subgroupEntity.getId());
	}
	
	public SubgroupEntity findById(SubgroupEntity subgroupEntity) {
		return subgroupDao.findById(subgroupEntity.getId());
	}
	
	public List<SubgroupEntity> findAll() {
		return subgroupDao.findAll();
	}
	
}
