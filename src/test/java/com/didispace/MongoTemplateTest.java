package com.didispace;

import com.didispace.domain.Admin;
import com.didispace.domain.User;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by liuhaiyang on 2017/3/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class MongoTemplateTest {

    @Autowired
    MongoOperations mongoTemplate;

    @Test
    public void test() throws Exception {
        updateColl();
    }

    public void  findAllColletions(){
        Set<String> cName = mongoTemplate.getCollectionNames();
        for(String str: cName){
            System.err.println(str);
        }
    }

    public void createCollection(){
        DBCollection collection = mongoTemplate.createCollection("xxx");
    }

    public void findOneColl(){
        DBCollection collection = mongoTemplate.getCollection("admin");
        System.err.println(collection.count());
        Admin admin = mongoTemplate.findOne(query(where("name").is("zhangsan")), Admin.class);
        System.err.println(admin.getAddress());
    }

    public void updateColl(){
        Admin admin = mongoTemplate.findOne(query(where("name").is("template")), Admin.class);
        admin.setName("template-update"); //change attributes
        mongoTemplate.save(admin);
    }


    public void addColl(){
        //add one
        Admin p = new Admin();
        p.setName("template");
        p.setAddress("hahahaha");
        mongoTemplate.insert(p);
        //add list
        List<Admin> list = new ArrayList<>();
        Admin p1 = new Admin();
        p1.setName("template11111");
        p1.setAddress("hahahaha11111");
        Admin p2 = new Admin();
        p2.setName("template22222");
        p2.setAddress("hahahaha2222");
        list.add(p1);
        list.add(p2);
        mongoTemplate.insert(list,Admin.class);
    }

    /**
     * accounts是User的一个集合属性，private List<Account> accounts = new ArrayList<Account>();
     * balance 是Account的一个属性 private Double balance;
     */
    public void findByComplexParam(){
        List<User> result = mongoTemplate.find(
                new Query(where("age").lt(50).and("accounts.balance").gt(1000.00d)),
                User.class);
    }
    public void batchUpdate(){
        WriteResult wr = mongoTemplate.updateMulti(
                query(where("accounts.accountType").is("xxx")),
                new Update().inc("accounts.$.balance", 50.00),
                User.class);
    }
}
