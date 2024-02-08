package backend.model.dao.factory;

import backend.accessdb.DbConnection;
import backend.model.dao.ProductDao;
import backend.model.dao.impl.ProductImpl;

public class ProductFactory {

	public static ProductDao createProductDao() {
		return new ProductImpl(DbConnection.getConnection());
	}
	
}
