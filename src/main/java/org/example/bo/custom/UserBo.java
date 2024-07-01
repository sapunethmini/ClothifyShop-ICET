package org.example.bo.custom;

import org.example.bo.SuperBo;
import org.example.dto.Employee;
import org.example.dto.Order;
import org.example.dto.Product;
import org.example.dto.Supplier;

import java.util.List;

public interface UserBo extends SuperBo {
    boolean saveUser(Employee dto);
    boolean Saveorder(Order dto);
    boolean saveSup(Supplier dto);

    boolean saveProduct(Product dto);

    boolean updateEmployee(Employee dto);
    boolean updateProduct(Product dto);
    boolean updateSupplier(Supplier dto);





}
