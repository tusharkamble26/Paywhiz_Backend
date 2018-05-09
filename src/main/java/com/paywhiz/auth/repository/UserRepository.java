package com.paywhiz.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;



import com.paywhiz.auth.model.User;

//@Repository
//@Entity
//@Table(name = "user")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    @Query("select u from User u where u.id=1")
    User findEver();
    
    @Modifying(clearAutomatically = true)
    @Query("update User u  set u.wallet = 900 ")
    void setWalletU(String w);
    
}
