package com.taxi.dao;

import com.taxi.domain.Action;
import com.taxi.domain.User;

import java.util.List;

public interface ActionDao {
    Action getUserAction(User user);
    void addSumToAction(User user, Action action, double sum);
    void takeSumFromAction(User user, Action action, double sum);
    void addNewAction(Action action);
}
