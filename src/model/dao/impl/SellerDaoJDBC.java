package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    private Connection conn;
    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void delete(Seller obj) {

    }

    @Override
    public Seller getById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE seller.Id = ?");
            ps.setInt(1, id);
            rs= ps.executeQuery();
            if (rs.next()){
                Department dep= instantiateDepartment(rs);

                Seller obj= instatiateSeller(rs,dep);


                return obj;
            }
            return null;
        }
        catch (Exception e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeConnection();
        }
    }

    private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep=new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> finAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "ORDER BY Name");

            rs= ps.executeQuery();
            List<Seller> sellers = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();
            while (rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep==null){
                    dep= instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }
                Seller obj= instatiateSeller(rs,dep);
                sellers.add(obj);

            }
            return sellers;
        }
        catch (Exception e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> finAllByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE DepartmentId = ?\n" +
                    "ORDER BY Name");
            ps.setInt(1, department.getId());
            rs= ps.executeQuery();
            List<Seller> sellers = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();
            while (rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep==null){
                    dep= instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }
                Seller obj= instatiateSeller(rs,dep);
                sellers.add(obj);

            }
            return sellers;
        }
        catch (Exception e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
}
