package com.shlok.todoapplication.repository;

import com.shlok.todoapplication.model.Todo;
import com.shlok.todoapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}