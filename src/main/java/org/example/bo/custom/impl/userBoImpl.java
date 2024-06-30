package org.example.bo.custom.impl;

import org.example.bo.custom.UserBo;
import org.example.dao.DaoFactory;
import org.example.dao.custom.CustomerDao;
import org.example.dto.Employee;
import org.example.dto.Order;
import org.example.dto.Product;
import org.example.dto.Supplier;
import org.example.entity.EmployeeEntity;
import org.example.entity.OrderEntity;
import org.example.entity.ProductEntity;
import org.example.entity.SupplierEntity;
import org.example.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.List;

public class userBoImpl implements UserBo {
    private CustomerDao customerDoa= DaoFactory.getInstance().getDao(DaoType.USER);

    @Override
    public boolean saveUser(Employee dto) {
        return customerDoa.save(new ModelMapper().map(dto, EmployeeEntity.class));
    }



    public boolean Saveorder(Order dto) {
        return customerDoa.saveorder(new ModelMapper().map(dto, OrderEntity.class));
    }



    public boolean saveSup(Supplier dto) {

        return customerDoa.saveSup(new ModelMapper().map(dto, Supplier.class));
    }



    public boolean saveProduct(Product dto) {

        return customerDoa.saveProduct(new ModelMapper().map(dto, Product.class));
    }




    @Override
    public boolean updateEmployee(Employee dto) {

        return customerDoa.update(new ModelMapper().map(dto, EmployeeEntity.class));}

    @Override
    public boolean updateProduct(Product dto) {
        return customerDoa.updateP(new ModelMapper().map(dto, ProductEntity.class));}

    @Override
    public boolean updateSupplier(Supplier dto) {
        return customerDoa.updateS(new ModelMapper().map(dto, SupplierEntity.class));}
}






