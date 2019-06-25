package com.taxi.dao;

import com.taxi.domain.Action;
import com.taxi.domain.UserAction;

public interface UserActionDao {
    UserAction getUserActionByAction(Action action);
    void createnewUserAction(Action action);
}
