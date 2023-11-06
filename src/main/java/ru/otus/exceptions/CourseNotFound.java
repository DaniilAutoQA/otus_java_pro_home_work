package ru.otus.exceptions;

public class CourseNotFound extends Exception{

    public CourseNotFound(String courseName) {
        super(String.format("course with name %s is not found", courseName));

    }
}
