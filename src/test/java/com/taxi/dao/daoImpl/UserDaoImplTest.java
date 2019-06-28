package com.taxi.dao.daoImpl;

import com.taxi.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class UserDaoImplTest extends UserDaoImpl {
    private UserDaoImpl userDao;

    @Before
    public void setUp(){
        this.userDao = new UserDaoImpl();
    }

    @Test
    public void getIdByUserName1() {
        int id = userDao.getIdByUserName("user");
        //user 1
        Assert.assertEquals(id,1);


    }

    @Test
    public void getUserByUserNameAndPassword1() {

    }

    @Test
    public void getUserById1() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("user");
        user.setUserMail("user@gmail.com");
        Assert.assertEquals(user.getUserId(), userDao.getUserById(1).getUserId());
        Assert.assertEquals(user.getUserName(), userDao.getUserById(1).getUserName());
        Assert.assertEquals(user.getUserMail(), userDao.getUserById(1).getUserMail());

    }
}