package com.xyg.repository;

import com.xyg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByPhoneAndPassword(String phoneNo,String password);

    User findUserByLoginToken(String token);
}
