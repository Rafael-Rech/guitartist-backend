package com.tcc.tcc.domain.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document("User")
public class User implements UserDetails{
    private String name;
    private String password;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String id;
    private short appVersion;
    private List<Lesson> lessons;
    private Date time;
    private String profilePicture;
    private short noteRepresentation;
    


    public User(String name, String password, String email, String id, short appVersion, List<Lesson> lessons) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.appVersion = appVersion;
        this.lessons = lessons;
        this.time = new Date();
        this.profilePicture = null;
        this.noteRepresentation = 0;
    }

    public User(String name, String password, String email, String id, short appVersion, List<Lesson> lessons, Date time, String profilePicture, short noteRepresentation) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.appVersion = appVersion;
        this.lessons = lessons;
        this.time = time;
        this.profilePicture = profilePicture;
        this.noteRepresentation = noteRepresentation;
    }

    public User(){}
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public short getAppVersion() {
        return appVersion;
    }
    public void setAppVersion(short appVersion) {
        this.appVersion = appVersion;
    }
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public short getNoteRepresentation() {
        return noteRepresentation;
    }

    public void setNoteRepresentation(short noteRepresentation) {
        this.noteRepresentation = noteRepresentation;
    }

    @Override
    public String toString(){
        return "User: " + name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Empty list because there's no user roles in this system
    }

    @Override
    public String getUsername(){
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
