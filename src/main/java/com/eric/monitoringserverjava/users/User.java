package com.eric.monitoringserverjava.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String password;
    private Role[] roles;
    private String token;
    
    public enum Role {
        ADMIN("ADMIN"), USER("USER"), GUEST("GUEST");
        
        private String stringName;
        
        Role (String stringName) {
            this.stringName = stringName;
        }
        
        @Override
        public String toString () {
            return stringName;
        }
    }
    
    public User () {
    }
    
    public User (String id, String name, String password, Role[] roles, String token) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.token = token;
    }
    
    public String getId () {
        return id;
    }
    
    public void setId (String id) {
        this.id = id;
    }
    
    public String getName () {
        return name;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public String getPassword () {
        return password;
    }
    
    public void setPassword (String password) {
        this.password = password;
    }
    
    public Role[] getRoles () {
        return roles;
    }
    
    public void setRoles (Role[] roles) {
        this.roles = roles;
    }
    
    public String getToken () {
        return token;
    }
    
    public void setToken (String token) {
        this.token = token;
    }
    
    @Override
    public String toString () {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", token='" + token + '\'' +
                '}';
    }
}
