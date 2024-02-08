package backend.model.dao.factory;

import backend.accessdb.DbConnection;
import backend.model.dao.GroupDao;
import backend.model.dao.impl.GroupImpl;

public class GroupFactory {

	public static GroupDao createGroupDao() {
		return new GroupImpl(DbConnection.getConnection());
	}
	
}
