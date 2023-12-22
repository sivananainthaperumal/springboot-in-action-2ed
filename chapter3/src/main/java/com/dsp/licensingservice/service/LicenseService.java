package com.dsp.licensingservice.service;

import com.dsp.licensingservice.config.ServiceConfig;
import com.dsp.licensingservice.model.License;
import com.dsp.licensingservice.model.Organization;
import com.dsp.licensingservice.repository.LicenseRepository;
import com.dsp.licensingservice.service.client.OrganizationDiscoveryClient;
import com.dsp.licensingservice.service.client.OrganizationRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    private MessageSource messages;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ServiceConfig config;

    //@Autowired
    //OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;

    public License getLicense(String licenseId, String organizationId){

        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId,licenseId);

        if(null == license){
            throw new IllegalArgumentException(String.format(
                    messages.getMessage("license.search.error.message",null,null),
                    licenseId,organizationId));
        }
        return license.withComment(config.getProperty());

    }

    public License createLicense(License license, String organizationId, Locale locale){

        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());

    }

    public License updateLicense(License license, String organizationId){

        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId, String organizationId){

        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        return String.format(" This is the delete method and the object is : %s", licenseId);
    }

    public License getLicense(String licenseId, String organizationId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId,licenseId);

        if(null == license){
            throw new IllegalArgumentException(String.format(
                    messages.getMessage("license.search.error.message",null,null),
                    licenseId,organizationId));
        }

        Organization organization = retrieveOrganizationInfo(organizationId,
                clientType);
        if (null != organization) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }
        return license.withComment(config.getProperty());
    }

    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        Organization organization = null;

        switch (clientType) {
//            case "feign":
//                System.out.println("I am using the feign client");
//                organization = organizationFeignClient.getOrganization(organizationId);
//                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
                break;
        }

        return organization;
    }
}
