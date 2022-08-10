package vn.codegym.repository;

import vn.codegym.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    void save(Student student);
    void update(Student student);
    Student findById(int id);
    void delete(int id);
}
