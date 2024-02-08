package backend.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import backend.accessdb.DbConnection;
import backend.accessdb.DbException;
import backend.model.dao.ProductDao;
import backend.model.entity.ProductEntity;

public class ProductImpl implements ProductDao {

	private Connection connection;
	
	public ProductImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void save(ProductEntity obj) {
		PreparedStatement ps = null;
		
		String sqlSave 
			= "INSERT INTO kdproduct "
				+ "(description, "
				+ "reference, "
				+ "barcode) "
			+ "VALUES"
				+ "(?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sqlSave, 
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getReference());
			ps.setString(3, obj.getBarcode());
			
			Long rowsAffected = ps.executeLargeUpdate();
			
			if (rowsAffected > 0L) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					Long id = rs.getLong(1);
					obj.setId(id);
				}
				DbConnection.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DbConnection.closeStatement(ps);
		}
	}

	@Override
	public void update(ProductEntity obj) {
		PreparedStatement ps = null;
		
		String sqlUpdate
			= "UPDATE "
				+ "kdproduct "
			+ "SET "
				+ "description = ?, "
				+ "reference = ?, "
				+ "barcode = ? "
			+ "WHERE "
				+ "id = ?";
		
		try {
			ps = connection.prepareStatement(sqlUpdate);
						
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getReference());
			ps.setString(3, obj.getBarcode());
			ps.setLong(4, obj.getId());
				
			ps.executeLargeUpdate();
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DbConnection.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Long id) {
		PreparedStatement ps = null;
		
		String sqlDelete
		= "DELETE FROM "
			+ "kdproduct "
		+ "WHERE "
			+ "id = ?";
		
		try {
			ps = connection.prepareStatement(sqlDelete);
			
			ps.setLong(1, id);
			
			ps.executeLargeUpdate();
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DbConnection.closeStatement(ps);
		}	
	}

	@Override
	public ProductEntity findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sqlSelectById 
				= "SELECT * "
				+ "FROM "
					+ "kdproduct "
				+ "WHERE "
					+ "id = ?";
		try {
			ps = connection.prepareStatement(sqlSelectById);		
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				ProductEntity obj = new ProductEntity();
				
				obj.setId(rs.getLong("id"));
				obj.setDescription(rs.getString("description"));
				obj.setReference(rs.getString("reference"));
				obj.setBarcode(rs.getString("barcode"));
				
				return obj;
			}
			return null;
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DbConnection.closeStatement(ps);
			DbConnection.closeResultSet(rs);
		}
	}
	
	@Override
	public List<ProductEntity> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sqlSelectAll
			= "SELECT * "
			+ "FROM "
				+ "kdproduct";
		
		try {
			ps = connection.prepareStatement(sqlSelectAll);
			rs = ps.executeQuery();
			
			List<ProductEntity> list = new ArrayList<>();
			
			while (rs.next()) {
				ProductEntity obj = new ProductEntity();
				
				obj.setId(rs.getLong("id"));
				obj.setDescription(rs.getString("description"));
				obj.setReference(rs.getString("reference"));
				obj.setBarcode(rs.getString("barcode"));
				
				list.add(obj);		
			}
			return list;
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DbConnection.closeStatement(ps);
			DbConnection.closeResultSet(rs);
		}
	}
}
