package com.check24.IMDB.api.user;


import com.check24.IMDB.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select case when count(c)> 0 then true else false end from UserEntity c where lower(c.username) like lower(:username)")
    boolean existsUser(@Param("username") String username);

    Optional<UserEntity> findByUsername(String username);
}
