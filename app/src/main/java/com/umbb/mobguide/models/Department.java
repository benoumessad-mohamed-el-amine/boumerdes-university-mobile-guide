package com.umbb.mobguide.models;

import java.io.Serializable;

/**
 * Model class for University Department.
 */
public class Department implements Serializable {
    private String facultyId;
    private String name;
    private String description;
    private String specialties;
    private String phone;
    private String email;
    private String locationUri;

    public Department(String facultyId, String name, String description, String specialties, String phone, String email, String locationUri) {
        this.facultyId = facultyId;
        this.name = name;
        this.description = description;
        this.specialties = specialties;
        this.phone = phone;
        this.email = email;
        this.locationUri = locationUri;
    }

    // Standard getters
    public String getFacultyId() { return facultyId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getSpecialties() { return specialties; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getLocationUri() { return locationUri; }
}
