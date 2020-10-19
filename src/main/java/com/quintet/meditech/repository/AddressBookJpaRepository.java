package com.quintet.meditech.repository;

import com.quintet.meditech.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookJpaRepository extends JpaRepository<AddressBook, Integer> {

}
