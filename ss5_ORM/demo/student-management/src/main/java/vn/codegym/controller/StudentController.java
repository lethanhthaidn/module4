package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.codegym.model.Student;
import vn.codegym.service.IStudentService;

@Controller
@RequestMapping({"student"})
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "list";
    }
    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "create";
    }
    @PostMapping("save")
    public String save(Student student){
        studentService.save(student);
        return "redirect:/student";
    }
    @GetMapping("{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "edit";
    }
    @PostMapping("edit")
    public String edit(Student student){
        studentService.update(student);
        return "redirect:/student";
    }
    @GetMapping("{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("student", studentService.findById(id));
        return "view";
    }
    @GetMapping("{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("student", studentService.findById(id));
        return "delete";
    }
    @PostMapping("delete")
    public String delete(Student student){
        studentService.delete(student.getId());
        return "redirect:/student";
    }
}
