package com.umbb.mobguide.models;

import java.io.Serializable;

/**
 * Model class for University Faculty.
 */
public class Faculty implements Serializable {
    private String id;
    private String name;
    private String description;
    private String phone;
    private String email;
    private String locationUri; // Google Maps URI

    public Faculty(String id, String name, String description, String phone, String email, String locationUri) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.locationUri = locationUri;
    }

    // Standard getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getLocationUri() { return locationUri; }
}
