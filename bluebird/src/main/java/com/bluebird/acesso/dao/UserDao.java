package com.bluebird.acesso.dao;

import com.bluebird.acesso.model.User;

public interface UserDao {

	User findByUserName(String username);

}