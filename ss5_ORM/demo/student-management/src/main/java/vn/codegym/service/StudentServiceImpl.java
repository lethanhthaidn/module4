package vn.codegym.service;

import vn.codegym.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.repository.IStudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    IStudentRepository studentRepository;
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.update(student);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        studentRepository.delete(id);
    }
}
