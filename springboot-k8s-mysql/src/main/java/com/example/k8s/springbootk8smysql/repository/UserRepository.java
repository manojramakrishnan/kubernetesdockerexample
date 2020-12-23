package com.example.k8s.springbootk8smysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.k8s.springbootk8smysql.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
