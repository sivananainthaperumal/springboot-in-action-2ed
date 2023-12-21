package com.dsp.licensingservice.service;

import com.dsp.licensingservice.config.ServiceConfig;
import com.dsp.licensingservice.model.License;
import com.dsp.licensingservice.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    private MessageSource messages;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ServiceConfig config;
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

}
