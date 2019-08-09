package com.demo.springmvc.repository;

import com.demo.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

}
