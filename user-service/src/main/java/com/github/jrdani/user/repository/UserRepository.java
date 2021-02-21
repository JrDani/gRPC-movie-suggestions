package com.github.jrdani.user.repository;

import com.github.jrdani.user.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
