package com.didispace.domain;

/**
 * Created by liuhaiyang on 2017/3/8.
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
    public Page<Admin> findByNameLike(String name,Pageable pageable);
}