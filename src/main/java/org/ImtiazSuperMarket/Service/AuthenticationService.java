package org.ImtiazSuperMarket.Service;

import org.ImtiazSuperMarket.Domain.User;
import org.ImtiazSuperMarket.dao.UserDoa;

public class AuthenticationService {
    public final static UserDoa userDoa = new UserDoa();
    public Boolean checkLogin(String username, String password){
        User user = userDoa.getUserByUserNameAndPassword(username,password);
        if(user !=null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
