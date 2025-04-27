package com.tcc.tcc.domain.dto.user;

import java.util.Date;
import java.util.List;

import com.tcc.tcc.common.DateConverter;
import com.tcc.tcc.domain.dto.lesson.LessonRequestDTO;


public class UserRequestDTO {
    private String name;
    private String password;
    private String email;
    private short appVersion;
    private List<LessonRequestDTO> lessons;
    private String time;
    private String profilePicture;
    private short noteRepresentation;

    public UserRequestDTO(){}
    
    public UserRequestDTO(String name, String password, String email, short appVersion, List<LessonRequestDTO> lessons) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.appVersion = appVersion;
        this.lessons = lessons;
        this.time = DateConverter.dateToString(new Date());
    }

    public UserRequestDTO(String name, String password, String email, short appVersion, List<LessonRequestDTO> lessons, String time) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.appVersion = appVersion;
        this.lessons = lessons;
        this.time = time;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
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
    public short getAppVersion() {
        return appVersion;
    }
    public void setAppVersion(short appVersion) {
        this.appVersion = appVersion;
    }
    public List<LessonRequestDTO> getLessons() {
        return lessons;
    }
    public void setLessons(List<LessonRequestDTO> lessons) {
        this.lessons = lessons;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
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
        return (name + " - " + password + ", " + email + ", " + appVersion + ", " + lessons + ", " + time + ", " + noteRepresentation);
    }
}
