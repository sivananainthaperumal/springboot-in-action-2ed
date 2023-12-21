package com.dsp.licensingservice.controller;

import com.dsp.licensingservice.model.License;
import com.dsp.licensingservice.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){
        License license = licenseService.getLicense(licenseId,organizationId);
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license){
                return ResponseEntity.ok(licenseService.updateLicense(license, organizationId));
    }

    @PostMapping
    public ResponseEntity<License> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language",required = false)
            Locale locale){
        return ResponseEntity.ok(licenseService.createLicense(license, organizationId,locale));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }

}
