package com.quintet.meditech.service;

import com.quintet.meditech.model.UserAvatar;
import com.quintet.meditech.model.Users;
import com.quintet.meditech.repository.UserAvatarJpaRepository;
import com.quintet.meditech.repository.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserAvatarService {
    @Autowired
    private UserAvatarJpaRepository avatarRepo;
    @Autowired
    private UserJPARepository userRepo;
    public void updateAvatar(Users user){
        //avatarRepo.save(userAvatar);
        System.out.println("user is is "+user.getUserId());
        userRepo.save(user);
    }
}
