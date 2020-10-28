package com.quintet.meditech.repository;

import com.quintet.meditech.model.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAvatarJpaRepository extends JpaRepository<UserAvatar, Integer> {
    public UserAvatar findByAvatarId(int id);
}
