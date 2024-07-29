package org.ImtiazSuperMarket.Mapper;

import org.ImtiazSuperMarket.Domain.Customer;
import org.ImtiazSuperMarket.Domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements IMapper<User> {
    private final static String ID = "user_id";
    private final static String USER_NAME = "username";
    private final static String PASSWORD = "password";
    private  final static String EMPLOYEETYPE = "usertype";
    @Override
    public List<User> resultSetToList(ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (rs.next()){
            User user = User.builder()
                    .id(rs.getInt(ID))
                    .username(rs.getString(USER_NAME))
                    .password(rs.getString(PASSWORD))
                    .empType(rs.getString(EMPLOYEETYPE))
                    .build();
            userList.add(user);
        }

        return userList;
    }

    @Override
    public User resultSetToObject(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return User.builder()
                    .id(rs.getInt(ID))
                    .username(rs.getString(USER_NAME))
                    .password(rs.getString(PASSWORD))
                    .build();

        }
        return null;
    }
}
