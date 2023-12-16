package com.dsp.licensingservice.service;

import com.dsp.licensingservice.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {

    @Autowired
    private MessageSource messages;
    public License getLicense(String licenseId, String organizationId){

        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software Product");
        license.setProductName("DSP");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale){
        if(license!=null){
            license.setOrganizationId(organizationId);
        }
        return String.format(messages.getMessage("license.create.message",null,locale),license.getId());
        //return String.format(" This is the post method and the object is : %s", license.toString());
    }

    public String updateLicense(License license, String organizationId){
        if(license!=null){
            license.setOrganizationId(organizationId);
        }
        return String.format(" This is the put method and the object is : %s", license.toString());
    }

    public String deleteLicense(String licenseId, String organizationId){
        return String.format(" This is the delete method and the object is : %s", licenseId);
    }

}
