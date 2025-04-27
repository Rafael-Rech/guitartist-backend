package com.tcc.tcc.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tcc.tcc.domain.dto.lesson.LessonRequestDTO;
import com.tcc.tcc.domain.dto.lesson.LessonResponseDTO;
import com.tcc.tcc.domain.dto.user.UserRequestDTO;
import com.tcc.tcc.domain.dto.user.UserResponseDTO;
import com.tcc.tcc.domain.model.Lesson;
import com.tcc.tcc.domain.service.UserService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/lesson")
public class LessonController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<List<LessonResponseDTO>> findAllLessons(@PathVariable String id) {
        UserResponseDTO userResponseDTO = userService.findUserById(id);
        return ResponseEntity.ok(userResponseDTO.getLessons());
    }

    @GetMapping("/{userId}/{lessonId}")
    public ResponseEntity<?> findLessonById(@PathVariable String userId, @PathVariable int lessonId) {
        UserResponseDTO userResponseDTO = userService.findUserById(userId);
        List<LessonResponseDTO> lessons = userResponseDTO.getLessons();
        for(LessonResponseDTO lesson : lessons){
            if(lesson.getId() == lessonId){
                return ResponseEntity.ok(lesson);
            }
        }
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }
}
