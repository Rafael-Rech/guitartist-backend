package com.tcc.tcc.domain.dto.user;

import java.util.Date;
import java.util.List;

import com.tcc.tcc.common.DateConverter;
import com.tcc.tcc.domain.dto.lesson.LessonResponseDTO;

public class UserResponseDTO {
    private String name;
    private String email;
    private String id;
    private short appVersion;
    private List<LessonResponseDTO> lessons;
    private String time;
    private String profilePicture;
    private short noteRepresentation;

    public UserResponseDTO(){}
    
    public UserResponseDTO(String name, String password, String email, String id, short appVersion, List<LessonResponseDTO> lessons) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.appVersion = appVersion;
        this.lessons = lessons;
        this.time = DateConverter.dateToString(new Date());
    }

    public UserResponseDTO(String name, String password, String email, String id, short appVersion, List<LessonResponseDTO> lessons, Date time) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.appVersion = appVersion;
        this.lessons = lessons;
        this.time = DateConverter.dateToString(time);
    }

    public UserResponseDTO(String name, String password, String email, String id, short appVersion, List<LessonResponseDTO> lessons, String time) {
        this.name = name;
        this.email = email;
        this.id = id;
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
    public List<LessonResponseDTO> getLessons() {
        return lessons;
    }
    public void setLessons(List<LessonResponseDTO> lessons) {
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
}
