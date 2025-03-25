package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller obj);
    void update(Seller obj);
    void delete(Seller obj);
    Seller getById(Integer id);
    List<Seller> finAll();
    List<Seller> finAllByDepartment(Department department);
}
