package com.guillermods.common.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guillermods.common.security.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findUserByUsername(String userName);
}
