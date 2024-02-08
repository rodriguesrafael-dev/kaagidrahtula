package backend.model.dao.factory;

import backend.accessdb.DbConnection;
import backend.model.dao.SubgroupDao;
import backend.model.dao.impl.SubgroupImpl;

public class SubgroupFactory {

	public static SubgroupDao createSubgroupDao() {
		return new SubgroupImpl(DbConnection.getConnection());
	}
	
}
