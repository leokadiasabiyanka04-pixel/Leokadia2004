package com.example.demo3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
