package com.prominentpixel.angularexcercise.dao;

import com.prominentpixel.angularexcercise.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    @Query("SELECT q FROM User q where q.status ='ACTIVE'")
    List<User> getAllUsers();

    @Query("SELECT q FROM User q WHERE q.status ='ACTIVE' and (lower(q.firstName) like %:searchTerm% or lower(q.lastName) like %:searchTerm% or lower(q.email) like %:searchTerm%)")
    Page<User> findAllByUser(Pageable pageable, String searchTerm);

    @Modifying
    @Query("UPDATE User u set u.status ='INACTIVE' where u.id = :id")
    void deActivateUser(@Param("id") long id);

    @Query("SELECT COUNT(e) FROM User e WHERE lower(e.firstName) like %:searchTerm%")
    Long countBySearchTerm(String searchTerm);

    Optional<User> findByEmail(String email);
}
