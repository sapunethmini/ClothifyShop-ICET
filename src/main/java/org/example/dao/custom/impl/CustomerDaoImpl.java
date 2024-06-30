package org.example.dao.custom.impl;

import org.example.dao.custom.CustomerDao;
import org.example.dto.Order;
import org.example.dto.Product;
import org.example.dto.Supplier;
import org.example.entity.EmployeeEntity;
import org.example.entity.OrderEntity;
import org.example.entity.ProductEntity;
import org.example.entity.SupplierEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Object dto) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(Object dto) {
        return false;
    }

    @Override
    public boolean save(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;

    }

    @Override
    public boolean saveorder(OrderEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }





    @Override
    public boolean saveSup(Supplier dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierEntity entity=new ModelMapper().map(dto,SupplierEntity.class);
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    public boolean saveProduct(Product dto){
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        ProductEntity entity = new ModelMapper().map(dto, ProductEntity.class);
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }




    @Override
    public boolean save(Order entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public boolean saveSup(SupplierEntity entity) {
        return false;
    }


    @Override
    public boolean update(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateP(ProductEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public boolean updateS(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

//
}

