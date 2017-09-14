package com.boaheninc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boaheninc.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
