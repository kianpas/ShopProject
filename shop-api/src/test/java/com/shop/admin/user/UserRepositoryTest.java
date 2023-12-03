package com.shop.admin.user;

import com.shop.core.entity.Role;
import com.shop.core.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
		
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	/**
	 * 유저생성 테스트 롤 1개
	 */
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
//		User user = new User("user@user.com", "1234", "first", "last");
//		user.addRole(roleAdmin);
		
//		User savedUser = repo.save(user);
		

//		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	/**
	 * 유저생성 테스트 롤 2개
	 */
	@Test
	public void testCreateNewUserWithTwoRole() {
		
//		User userRole2 = new User("role2@user.com", "1234", "first", "last");
		Role roleEditor = new Role(3L);
		Role roleAssistant = new Role(5L);
		
//		userRole2.addRole(roleEditor);
//		userRole2.addRole(roleAssistant);
		
//		User savedUser = repo.save(userRole2);
		
//		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	/**
	 * 전체 조회 테스트
	 */
	@Test
	public void testListAllUsers() {
		List<User> listUsers = repo.findAll();
		
		listUsers.forEach(user -> System.out.println(user));
		
	}
	
	/**
	 * 조회 테스트
	 */
	@Test
	public void testGetUserById() {

		User findUser = repo.findById(1L).get();
		System.out.println(findUser);
		assertThat(findUser).isNotNull();
	}
	
	/**
	 * 수정 테스트
	 */
	@Test
	public void testUpdateUserDetails() {
		User updateUser = repo.findById(1L).get();
		updateUser.setEnabled(true);
		updateUser.setEmail("upuser@user.com");
		repo.save(updateUser);
	}

	/**
	 * 롤 업데이트 테스트
	 */
	@Test
	public void testUpdateUserRoles() {
		User updateUser = repo.findById(5L).get();
		Role roleEditor = new Role(3L);
		Role roleSalesperson = new Role(2L);

		updateUser.getRoles().remove(roleEditor);
		updateUser.addRole(roleSalesperson);
		
		repo.save(updateUser);
	}
	
	/**
	 * 유저 삭제 테스트
	 */
	@Test
	public void testDeleteUserById() {
//		User user = repo.findById(2).get();
		Long userId = 3L;
		repo.deleteById(userId);
	}
	
	/**
	 * 이메일 기준 아이디 체크
	 */
	@Test
	public void testGetUserByEmail() {
		User userByEmail = repo.getUserByEmail("nam@codejava.net");
		System.out.println(userByEmail);
//		assertThat(userByEmail).isNotNull();
		
	}
	
	@Test
	public void testCountById() {
		Long countById = repo.count();
		System.out.println(countById);
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testDisableUser() {
		Long id = 1L;
		repo.updateEnabledStatus(id, false);
	}
	
	@Test
	public void testEnableUser() {
		Long id = 1L;
		repo.updateEnabledStatus(id, true);
	}
	
	/**
	 * 페이징 테스트
	 * @return
	 */
	@Test
	public void testListFirstPage() {
		int pageNum = 0;
		int pageSize = 5;

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<User> page = repo.findAll(pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isEqualTo(pageSize);
		
	}
	
	/**
	 * 검색 테스트
	 * @return
	 */
	@Test
	public void testSearch() {
		String keyword = "bruce";
		int pageNum = 0;
		int pageSize = 5;
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<User> page = repo.findAll(keyword, pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isGreaterThan(0);
		
	}
	
	
}
