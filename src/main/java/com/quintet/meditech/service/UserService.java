package com.quintet.meditech.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.quintet.meditech.model.*;
import com.quintet.meditech.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserJPARepository userRepo;
    @Autowired
    private AddressBookJpaRepository adddressBookJpaRepository;
    @Autowired
    private Users user;
    @Autowired
    private UserAvatarJpaRepository avatarRepo;
    @Autowired
    private UserAvatar userAvatar;
    @Autowired
    private DegreeJpaRepository degreeRepo;
    @Autowired
    private Degree degree;
    @Autowired
    private Speciality speciality;
    @Autowired
    private SpecialityJpaRepository specialityRepo;
    @Autowired
    private CategoriesJpaRepository categoryRepo;

    public Users getUser(String mobileNumber) {
        user =  userRepo.findByMobileNumber(mobileNumber);
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
        try {
            userAvatar = avatarRepo.findByUserUserId(user.getUserId());
            System.out.println("avatar id is "+user.getAddressBooks().getUser().getUserId());
            user.setUserAvatar(userAvatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            degree = user.getDegree();
            degree.setUser(user);
            degreeRepo.save(degree);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            speciality = user.getSpeciality();
            speciality.setUser(user);
            specialityRepo.save(speciality);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            List<Categories> categories = user.getCategories();
            //categoryRepo.saveAll(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userRepo.save(user);
        System.out.println("User address is "+user.getAddressBooks().getCity());
	}
    public Users findUser(int id) {
        return userRepo.findById(id);
    };


}
