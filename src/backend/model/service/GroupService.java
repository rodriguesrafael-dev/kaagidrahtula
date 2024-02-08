package backend.model.service;

import java.util.List;

import backend.model.dao.GroupDao;
import backend.model.dao.factory.GroupFactory;
import backend.model.entity.GroupEntity;

public class GroupService {

	private GroupDao groupDao = GroupFactory.createGroupDao();
	
	public void saveOrUpdate(GroupEntity groupEntity) {
		if (groupEntity.getId() == null) {
			groupDao.save(groupEntity);
		} else {
			groupDao.update(groupEntity);
		}
	}
	
	public void delete(GroupEntity groupEntity) {
		groupDao.deleteById(groupEntity.getId());
	}
	
	public GroupEntity findById(GroupEntity groupEntity) {
		return groupDao.findById(groupEntity.getId());
	}
	
	public List<GroupEntity> findAll() {
		return groupDao.findAll();
	}
	
}
