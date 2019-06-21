package com.taxi.dao;

import com.taxi.domain.Action;
import com.taxi.domain.User;

import java.util.List;

public interface ActionDao {
    Action getUserAction(User user);
}
