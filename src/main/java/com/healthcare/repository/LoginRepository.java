package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.healthcare.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String>{

}
