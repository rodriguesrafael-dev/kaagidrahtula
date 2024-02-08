package backend.model.dao;

import java.util.List;

import backend.model.entity.GroupEntity;

public interface GroupDao {

	void save(GroupEntity obj);
	void update(GroupEntity obj);
	void deleteById(Long id);
	GroupEntity findById(Long id);
	List<GroupEntity> findAll();
}
