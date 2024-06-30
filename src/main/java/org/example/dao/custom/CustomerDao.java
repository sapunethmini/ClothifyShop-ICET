package org.example.dao.custom;

import org.example.dao.CrudDao;
import org.example.dto.Employee;
import org.example.dto.Order;
import org.example.dto.Product;
import org.example.dto.Supplier;
import org.example.entity.EmployeeEntity;
import org.example.entity.OrderEntity;
import org.example.entity.ProductEntity;
import org.example.entity.SupplierEntity;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends CrudDao {
    boolean save(EmployeeEntity entity);
    boolean saveorder(OrderEntity entity);

    boolean save(Order entity);

    boolean saveSup(Supplier entity);
    boolean saveProduct(Product entity);


    boolean saveSup(SupplierEntity entity);

    boolean update(EmployeeEntity entity);
    boolean updateP(ProductEntity entity);
    boolean updateS(SupplierEntity entity);
}
