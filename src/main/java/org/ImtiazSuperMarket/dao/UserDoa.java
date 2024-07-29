package org.ImtiazSuperMarket.dao;

import org.ImtiazSuperMarket.Domain.Product;
import org.ImtiazSuperMarket.Domain.User;
import org.ImtiazSuperMarket.Mapper.UserMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.ImtiazSuperMarket.dao.SqlQueryConstant.*;

public class UserDoa extends BaseDao implements ICrud<User> {
    private final UserMapper userMapper = new UserMapper();
    public User getUserByUserNameAndPassword(String username, String password){
        try {
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_USERNAME_AND_PASSWORD);
            ps.setString(1,username);
            ps.setString(2,password);
         //   ps.setString(3,empType);
            ResultSet rs = ps.executeQuery();
            return  userMapper.resultSetToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void insert(User obj) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_USER);
            ps.setString(1, obj.getUsername());
            ps.setString(2,obj.getPassword());
            ps.setString(3,obj.getEmpType());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_USER);
            return userMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getAllUserName() {
        List<User> userName = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_USER);
            while (rs.next()) {
                userName.add(User.builder()
                                .id(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .build());
            }
            return userName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getByName(String name) {
        try{
            PreparedStatement stmt = connection.prepareStatement("select * from users where username  like '%"+name+"%' ");
//            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            return userMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public User getById(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet resultSet = ps.executeQuery();
            userMapper.resultSetToObject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(User obj, long id) {
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BY_ID);
            ps.setString(1, obj.getUsername());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getEmpType());
            ps.setInt(4, (int) id);
            ps.executeUpdate();
            if(ps.executeUpdate() > 0) {
                System.out.println("OK");
            } else{
                    System.out.println("NOT OK");
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delteById(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
