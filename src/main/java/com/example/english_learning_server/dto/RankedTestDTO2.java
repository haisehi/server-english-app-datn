package com.example.english_learning_server.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class RankedTestDTO2 {
    private Integer testId;

//    private String description;
    private Integer examTime;
    private String level;
    private String maxNumberOfExams;
    private Integer userTestStatus;
    private String testName;
    private String type;
    private Integer courseId;
    private String score;
    private String date;
    private LocalTime examTimes;


}
