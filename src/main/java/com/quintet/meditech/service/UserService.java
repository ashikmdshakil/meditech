package com.quintet.meditech.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.quintet.meditech.repository.AddressBookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quintet.meditech.model.Users;
import com.quintet.meditech.repository.UserJPARepository;

@Service
public class UserService {
    @Autowired
    private UserJPARepository userRepo;
    @Autowired
    private AddressBookJpaRepository adddressBookJpaRepository;
    @Autowired
    private Users user;

    public Users getUser(String mobileNumber) {
        user =  userRepo.findByMobileNumber(mobileNumber);
        //byte[] base64Image = Base64.getEncoder().encode(user.getUserAvatar().getImage());
        //user.getUserAvatar().setBase64Image(base64Image);
        //System.out.println(user.getUserAvatar().getBase64Image().toString());
        return user;
    }

    public void updateUserLoginInfo(Users user) {
        userRepo.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public void updateRole(Users user) {

    }

    public void deleteUser(Users user) {
        userRepo.delete(user);
    }

    public void updateUser(Users user){
    	adddressBookJpaRepository.save(user.getAddressBooks());
        userRepo.save(user);
	}
    public Users findUser(int id) {
        return userRepo.findById(id);
    };


}
