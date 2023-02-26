package org.shop.admin.user;

import com.shop.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //query의 User는 엔티티명 따라감 테이블 명이 아님
    //이메일로 유저 검색
    @Query("select u from User u join fetch u.roles where u.email = :email")
    public User getUserByEmail(@Param("email") String email);

    //상태업데이트
    @Query("update User u set u.enabled = ?2 where u.id = ?1")
    @Modifying
    public void updateEnabledStatus(Long id, boolean enabled);

    /*
    조건(아이디, 이메일, 이름)에 따른 검색
     */
    @Query("select u from User u where concat(u.id, ' ', u.email, ' ', u.firstName, ' '," +
            " u.lastName) like %?1%")
    public Page<User> findAll(String keyword, Pageable pageable);

}
