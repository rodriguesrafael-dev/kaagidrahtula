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
import backend.model.dao.GroupDao;
import backend.model.entity.GroupEntity;

public class GroupImpl implements GroupDao {

	private Connection connection = null;
	
	public GroupImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void save(GroupEntity obj) {
		PreparedStatement ps = null;
		
		String sqlSave 
			= "INSERT INTO kdgroup "
				+ "(name) "
			+ "VALUES"
				+ "(?)";
		
		try {
			ps = connection.prepareStatement(sqlSave, 
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, obj.getGroupname());
			
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
	public void update(GroupEntity obj) {
		PreparedStatement ps = null;
		
		String sqlUpdate
			= "UPDATE "
				+ "kdgroup "
			+ "SET "
				+ "name = ? "
			+ "WHERE "
				+ "id = ?";
		
		try {
			ps = connection.prepareStatement(sqlUpdate);
						
			ps.setString(1, obj.getGroupname());
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
			+ "kdgroup "
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
	public GroupEntity findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sqlSelectById 
				= "SELECT * "
				+ "FROM "
					+ "kdgroup "
				+ "WHERE "
					+ "id = ?";
		try {
			ps = connection.prepareStatement(sqlSelectById);		
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				GroupEntity obj = new GroupEntity();
				
				obj.setId(rs.getLong("id"));
				obj.setGroupname(rs.getString("name"));
				
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
	public List<GroupEntity> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sqlSelectAll
			= "SELECT * "
			+ "FROM "
				+ "kdgroup";
		
		try {
			ps = connection.prepareStatement(sqlSelectAll);
			rs = ps.executeQuery();
			
			List<GroupEntity> list = new ArrayList<>();
			
			while (rs.next()) {
				GroupEntity obj = new GroupEntity();
				
				obj.setId(rs.getLong("id"));
				obj.setGroupname(rs.getString("name"));
				
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
