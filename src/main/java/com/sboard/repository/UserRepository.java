package com.sboard.repository;

import com.sboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public int countByUid(String uid);
    public int countByNick(String nick);
    public int countByEmail(String email);
    public int countByHp(String hp);

}
