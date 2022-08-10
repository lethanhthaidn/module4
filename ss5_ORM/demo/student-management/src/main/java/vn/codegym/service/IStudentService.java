package vn.codegym.service;

import vn.codegym.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    void save(Student student);
    void update(Student student);
    Student findById(int id);
    void delete(int id);
}
