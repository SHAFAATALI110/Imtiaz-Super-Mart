package org.ImtiazSuperMarket.dao;

public class SqlQueryConstant {
    public final static String GET_ALL_USER = "select * from users";
    public final static String GET_ALL_INVOICE_MASTER_DATA = "select * from invoice_master";

    public final static String GET_ALL_USER_NAME = "select username from users";

public final static String GET_ALL_PRODUCT_ID_NAME = "SELECT product_id, product_name FROM product";
    public final static String GET_ALL_PRODUCT = "select * from product";
    public final static String GET_ALL_PRODUCT_NAME = "select product_name from product";
    public final static String GET_PRODUCT_ID_BY_NAME = "select product_id from product where product_name = ?";

    public final static String GET_USER_BY_ID = "select * from users where user_id = ? ";
    public final static String GET_USER_BY_NAME = "select * from users where username  like %?% ";


    public final static String GET_ALL_CUSTOMER = "select * from customer";

    public final static String INSERT_INTO_PRODUCT = "insert into product (product_code,product_name,unit_in_stock,unit_price)" +
            "values (?,?,?,?)";

    public final static String INSERT_INTO_USER = "insert into users (username,password,usertype)" +
            "values (?,?,?)";
    public final static String INSERT_INTO_CUSTOMER = "insert into customer (name,contact_address)" +
            "values (?,?)";
    public final static String GET_BY_NAME = "select * from customer c where c.customer_name like  '%?%' ";
//    public final static String GET_USER_BY_NAME = "select * from users u where u.username like  '%?%' ";

    public final static String GET_BY_ID = "select * from customer c where c.customer_id = ? ";
    public final static String DELETE_USER_BY_ID = "delete from users  where user_id = ? ";
    public final static String DELETE_PRODUCT_BY_ID = "delete from product where product_id = ? ";

    public final static String DELETE_BY_ID = "delete from customer c where c,id = ? ";
    public final static String UPDATE_USER_BY_ID = "update users set username = ?, password = ?, usertype = ? where user_id = ?";

    public final static String UPDATE_BY_ID = "update customer set name = ?, contact_address = ? where customer_id = ?";
    public final static String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from users where username = ? and password = ?";
    public  final  static  String INSERT_INTO_INVOICE_MASTER = "insert into invoice_master (customer_id,user_id) values(?,?)";
    public  final  static  String INSERT_INTO_INVOICE_DETAIL = "insert into invoice_detail (product_name,quantity) values(?,?)";



}
