package com.didispace;

import com.didispace.domain.Admin;
import com.didispace.domain.AdminRepository;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationAdminTests {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Before
	public void deleteAll() {
		//adminRepository.deleteAll();
	}


	@Test
	public void test() throws Exception {
		findByNamePaging(1,2,null);//first page
		findByNamePaging(2,2,null); //second page
	}

	/**
	 * 分页
	 */
	private PageRequest buildPageRequest(int pageNumber, int pageSize,String sortType) {
		Sort sort = null;
		sort = new Sort(Sort.Direction.DESC, "name");
		//参数1表示当前第几页,参数2表示每页的大小,参数3表示排序
		return new PageRequest(pageNumber-1,pageSize,sort);
	}
	public void findByNamePaging(int pageNumber,int pageSize,String sortType){
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Page<Admin> admis = adminRepository.findByNameLike("t", pageRequest);
		for(Admin admin:admis){
			System.err.println(admin.getName());
		}

	}


	public void getAdminBySexOrAddress() {
		BasicDBList orList = new BasicDBList(); //用于记录
		orList.add(new BasicDBObject("sex", 1));
		orList.add(new BasicDBObject("address", "shenyang"));
		BasicDBObject orDBObject = new BasicDBObject("$or", orList);
		List<Admin> list = mongoTemplate.find(new BasicQuery(orDBObject), Admin.class);
		for(Admin admin:list){
			System.err.println(admin.getName());
		}
	}

	public void getAdminBySexAndName() {
		BasicDBList andList  = new BasicDBList(); //用于记录
		andList.add(new BasicDBObject("sex", 0));
		andList.add(new BasicDBObject("name", "lisi"));
		BasicDBObject orDBObject = new BasicDBObject("$and", andList);
		List<Admin> list = mongoTemplate.find(new BasicQuery(orDBObject), Admin.class);
		for(Admin admin:list){
			System.err.println(admin.getName());
		}
	}

	public void addAdmin(){
		Admin admin = new Admin();
		admin.setName("zhangsan");
		admin.setSex(1);
		admin.setAddress("dalian");
		adminRepository.save(admin);
	}

	public void addAdmins(){
		List<Admin> adminList = new ArrayList<>();
		Admin admin = new Admin();
		admin.setName("zhangsan");
		admin.setSex(1);
		admin.setAddress("dalian");
		adminList.add(admin);
		Admin admin1 = new Admin();
		admin1.setName("lisi");
		admin1.setSex(0);
		admin1.setAddress("shenyang");
		adminList.add(admin1);
		Admin admin2 = new Admin();
		admin2.setName("wangwu");
		admin2.setSex(0);
		admin2.setAddress("where");
		adminList.add(admin2);
		adminRepository.save(adminList);
	}

	public void getAllAdmin() {
		List<Admin> adminList= adminRepository.findAll();
		for(Admin admin:adminList){
			System.err.println(admin.getName());
		}
	}

}
