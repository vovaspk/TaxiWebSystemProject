package com.taxi.dao.daoImpl;

import com.taxi.domain.Street;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WayDaoImplTest extends UserDaoImpl {
    private WayDaoImpl wayDao;

    @Before
    public void setUp(){
        this.wayDao = new WayDaoImpl();
    }

    @Test
    public void getSumKm() {
        Street home = new Street();
        home.setId(4);
        Street dest = new Street();
        dest.setId(11);
        Assert.assertEquals(3.13, wayDao.getSumKm(home,dest), 0.01);

    }
}