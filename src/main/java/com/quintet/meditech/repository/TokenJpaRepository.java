package com.quintet.meditech.repository;

import com.quintet.meditech.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<Token, Integer> {
    public Token findByTokenString(String token);
    public Token findByUserUserId(int id);
    public void deleteAllByUserUserId(int id);
}
