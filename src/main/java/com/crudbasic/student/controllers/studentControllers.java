package com.crudbasic.student.controllers;

import com.crudbasic.student.models.Response;
import com.crudbasic.student.models.Student;
import com.crudbasic.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/student")
public class studentControllers {
    @Autowired
    private StudentRepository repository;

    //Lấy danh sách sinh viên
    @GetMapping("getAllStudent")
    List<Student> getAllStudent() {
        return repository.findAll();
    }

    //Tìm theo id
    @GetMapping("/{id}")
    ResponseEntity<Response> findByID(@PathVariable Long id) {
        Optional<Student> found = repository.findById(id);
        if(found.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("ok","Tìm thành công",found)
            );

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("fail","Không tìm được id = "+id,"")
            );
        }
    }

    //Thêm sinh viên với POST (Raw , JSON)

    @PostMapping("/insert")
    ResponseEntity<Response> insert(@RequestBody Student newStudent){
        return ResponseEntity.status(HttpStatus.OK).body(
                new Response("ok","Thêm thành công",repository.save(newStudent)));
    }

    //Cập nhật với Put
    @PutMapping("/update/{id}")
    ResponseEntity<Response> updateByID(@RequestBody Student newStudent, @PathVariable Long id){
        Student updateStudent =  repository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setAddress(newStudent.getAddress());
                    student.setMajor(newStudent.getMajor());
                    student.setPoint(newStudent.getPoint());
                    return repository.save(student);
                }).orElseGet(() -> {
                    newStudent.setID(id);
                    return repository.save(newStudent);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new Response("ok","Cập nhật thành công",updateStudent));
    }

    //Xoá với Delete
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Response> deleteByID(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("ok","Xoá thành công",""));
        };
        return ResponseEntity.status(HttpStatus.OK).body(
                new Response("fail","Không tìm thấy ID để xoá",""));
    }
}
