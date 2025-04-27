package com.tcc.tcc.domain.dto.lesson;

import com.tcc.tcc.domain.Enum.ELessonType;
import com.tcc.tcc.domain.Enum.ESubject;

public class LessonResponseDTO {
    private ESubject subject;
    private short id;
    private ELessonType type;
    private short numberOfTries;
    private short averagePrecision;
    private short proficiency;

    public LessonResponseDTO(ESubject subject, short id, ELessonType type) {
        this.subject = subject;
        this.id = id;
        this.type = type;
        this.numberOfTries = 0;
        this.averagePrecision = 0;
        this.proficiency = 0;
    }
    public LessonResponseDTO(ESubject subject, short id, ELessonType type, short numberOfTries, short averagePrecision,
            short proficiency) {
        this.subject = subject;
        this.id = id;
        this.type = type;
        this.numberOfTries = numberOfTries;
        this.averagePrecision = averagePrecision;
        this.proficiency = proficiency;
    }
    public LessonResponseDTO(){
        this.id = 0;
        this.numberOfTries = 0;
        this.averagePrecision = 0;
        this.proficiency = 0;
    }
    
    public ESubject getSubject() {
        return subject;
    }
    public void setSubject(ESubject subject) {
        this.subject = subject;
    }
    public short getId() {
        return id;
    }
    public void setId(short id) {
        this.id = id;
    }
    public ELessonType getType() {
        return type;
    }
    public void setType(ELessonType type) {
        this.type = type;
    }
    public short getNumberOfTries() {
        return numberOfTries;
    }
    public void setNumberOfTries(short numberOfTries) {
        this.numberOfTries = numberOfTries;
    }
    public short getAveragePrecision() {
        return averagePrecision;
    }
    public void setAveragePrecision(short averagePrecision) {
        this.averagePrecision = averagePrecision;
    }
    public short getProficiency() {
        return proficiency;
    }
    public void setProficiency(short proficiency) {
        this.proficiency = proficiency;
    }
}
