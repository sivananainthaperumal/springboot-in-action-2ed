package com.optimagrowth.organization.repository;

import java.util.Optional;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimagrowth.organization.model.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization,String>  {
    public Optional<Organization> findById(String organizationId);

    public Optional<Organization> findByName(String OrganizationName);
}
