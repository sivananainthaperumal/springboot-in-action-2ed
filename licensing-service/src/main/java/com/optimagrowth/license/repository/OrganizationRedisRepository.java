package com.optimagrowth.license.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.optimagrowth.license.model.Organization;

import java.util.Optional;

@Repository
public interface OrganizationRedisRepository extends
        CrudRepository<Organization,String>{
        public Optional<Organization> findById(String organizationId);

        public Optional<Organization> findByName(String OrganizationName);
        }