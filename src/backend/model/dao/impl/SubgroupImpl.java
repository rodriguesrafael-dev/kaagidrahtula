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
import backend.model.dao.SubgroupDao;
import backend.model.entity.SubgroupEntity;

public class SubgroupImpl implements SubgroupDao {

private Connection connection;
	
	public SubgroupImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void save(SubgroupEntity obj) {
		PreparedStatement ps = null;
		
		String sqlSave 
			= "INSERT INTO kdsubgroup "
				+ "(name) "
			+ "VALUES"
				+ "(?)";
		
		try {
			ps = connection.prepareStatement(sqlSave, 
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, obj.getSubgroupname());
			
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
	public void update(SubgroupEntity obj) {
		PreparedStatement ps = null;
		
		String sqlUpdate
			= "UPDATE "
				+ "kdsubgroup "
			+ "SET "
				+ "name = ? "
			+ "WHERE "
				+ "id = ?";
		
		try {
			ps = connection.prepareStatement(sqlUpdate);
						
			ps.setString(1, obj.getSubgroupname());
			ps.setLong(2, obj.getId());
				
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
			+ "kdsubgroup "
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
	public SubgroupEntity findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sqlSelectById 
				= "SELECT * "
				+ "FROM "
					+ "kdsubgroup "
				+ "WHERE "
					+ "id = ?";
		try {
			ps = connection.prepareStatement(sqlSelectById);		
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				SubgroupEntity obj = new SubgroupEntity();
				
				obj.setId(rs.getLong("id"));
				obj.setSubgroupname(rs.getString("name"));
				
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
	public List<SubgroupEntity> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sqlSelectAll
			= "SELECT * "
			+ "FROM "
				+ "kdsubgroup";
		
		try {
			ps = connection.prepareStatement(sqlSelectAll);
			rs = ps.executeQuery();
			
			List<SubgroupEntity> list = new ArrayList<>();
			
			while (rs.next()) {
				SubgroupEntity obj = new SubgroupEntity();
				
				obj.setId(rs.getLong("id"));
				obj.setSubgroupname(rs.getString("name"));
				
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
