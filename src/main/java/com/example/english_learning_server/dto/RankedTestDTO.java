package com.example.english_learning_server.dto;

public class RankedTestDTO {
    private Integer testId;
    private String testName;
    private String description;
    private String examDate;
    private Integer examTime;
    private String level;
    private String maxNumberOfExams;
    private Integer courseId;
    private String type;
    private String score;
    private String date;
    private String examTimes;
    private Integer status;  // Thêm trường status kiểu Integer

    // Constructor mặc định (nếu cần thiết)
    public RankedTestDTO() {
    }

    // Các constructor có tham số (nếu cần thiết)
    public RankedTestDTO(Integer testId, String testName, String description, String examDate, Integer examTime,
                         String level, String maxNumberOfExams, Integer courseId, String type, String score,
                         String date, String examTimes, Integer status) {
        this.testId = testId;
        this.testName = testName;
        this.description = description;
        this.examDate = examDate;
        this.examTime = examTime;
        this.level = level;
        this.maxNumberOfExams = maxNumberOfExams;
        this.courseId = courseId;
        this.type = type;
        this.score = score;
        this.date = date;
        this.examTimes = examTimes;
        this.status = status;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public Integer getExamTime() {
        return examTime;
    }

    public void setExamTime(Integer examTime) {
        this.examTime = examTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMaxNumberOfExams() {
        return maxNumberOfExams;
    }

    public void setMaxNumberOfExams(String maxNumberOfExams) {
        this.maxNumberOfExams = maxNumberOfExams;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExamTimes() {
        return examTimes;
    }

    public void setExamTimes(String examTimes) {
        this.examTimes = examTimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
