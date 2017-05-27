package com.didispace;

import com.didispace.domain.User;
import com.didispace.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Before
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Test
	public void test() throws Exception {
		addUser();
		findByNameLike();
	}

	public void addUser(){
		// 创建三个User，并验证User总数
		userRepository.save(new User(1L, "didi", 30));
		userRepository.save(new User(2L, "mama", 40));
		userRepository.save(new User(3L, "kaka", 50));
		//Assert.assertEquals(3, userRepository.findAll().size());
	}

	public void findByNameLike(){
		List<User> list = userRepository.findByUsernameLike("a");
		for(User u :list ){
			System.err.println(u.getUsername());
		}
	}

	public void findByAgeGreater(){
		List<User> list = userRepository.findByAgeGreaterThan(30);
		for(User u :list ){
			System.err.println(u.getUsername());
		}
	}

	public void findByAge(){
		User u = userRepository.findByAge(30);
		System.err.println(u.getUsername());
	}
	public void findByIdAndDel(){
		// 删除一个User，再验证User总数
		User u = userRepository.findOne(1L);
		userRepository.delete(u);
		//Assert.assertEquals(2, userRepository.findAll().size());
	}

	public void findByNameAndDel(){
		// 删除一个User，再验证User总数
		User u = userRepository.findByUsername("mama");
		userRepository.delete(u);
		//Assert.assertEquals(1, userRepository.findAll().size());
	}

}
