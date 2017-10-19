package edu.unomaha.peerreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.unomaha.peerreview.model.Student;
import edu.unomaha.peerreview.repository.StudentRepository;


@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@RequestMapping(value="/api/student/all")
	public @ResponseBody Iterable<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	@GetMapping(value="/api/student/read_one")
	public @ResponseBody Student readOneStudent(@RequestParam int id) {
		return studentRepository.findOne(id);
	}
	
	@RequestMapping(value="/api/student/search")
	public @ResponseBody Iterable<Student> searchStudents(@RequestParam String s) {
		return studentRepository.findByStudentNameContainingOrStudentEmailAddressContaining(s, s);
	}
	
	@PostMapping(value="/api/student/delete")
	public @ResponseBody String deleteStudent(@RequestBody Student n) {
		studentRepository.delete(n.getId());
		return "{\"message\" : \"student was deleted.\"}";
	}
	
	
	@RequestMapping(value = "/api/student/create" , method = RequestMethod.POST)
	public @ResponseBody String createNewStudent (@RequestBody Student n) {
		studentRepository.save(n);
		return "{\"message\" : \"student was created.\"}";
	}
	
	@RequestMapping(value = "/api/student/update" , method = RequestMethod.POST)
	public @ResponseBody String updateStudent (@RequestBody Student n) {
		studentRepository.save(n);
		return "{\"message\" : \"student was updated.\"}";
	}
}