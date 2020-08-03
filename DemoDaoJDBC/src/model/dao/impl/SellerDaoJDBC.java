package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection connection;

	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Department department = instantiateDepartment(resultSet);
				Seller seller = instantiateSeller(resultSet, department);
				return seller;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(preparedStatement);
			DB.closeResultSet(resultSet);
		}

	}

	private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(resultSet.getInt("Id"));
		seller.setName(resultSet.getString("Name"));
		seller.setEmail(resultSet.getString("Email"));
		seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
		seller.setBirthDate(resultSet.getDate("BirthDate"));
		seller.setDepartment(department);
		return seller;
	}

	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setId(resultSet.getInt("DepartmentId"));
		department.setName(resultSet.getString("DepName"));
		return department;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name");
	
			resultSet = preparedStatement.executeQuery();

			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (resultSet.next()) {

				Department departmentControll = map.get(resultSet.getInt("DepartmentId"));

				if (departmentControll == null) {
					departmentControll = instantiateDepartment(resultSet);
					map.put(resultSet.getInt("DepartmentId"), departmentControll);
				}
				Seller seller = instantiateSeller(resultSet, departmentControll);
				sellerList.add(seller);
			}
			return sellerList;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");
			preparedStatement.setInt(1, department.getId());
			resultSet = preparedStatement.executeQuery();

			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (resultSet.next()) {

				Department departmentControll = map.get(resultSet.getInt("DepartmentId"));

				if (departmentControll == null) {
					departmentControll = instantiateDepartment(resultSet);
					map.put(resultSet.getInt("DepartmentId"), departmentControll);
				}
				Seller seller = instantiateSeller(resultSet, departmentControll);
				sellerList.add(seller);
			}
			return sellerList;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatment(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

}
