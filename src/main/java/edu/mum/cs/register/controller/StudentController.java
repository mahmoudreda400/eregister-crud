package edu.mum.cs.register.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.cs.register.models.Student;
import edu.mum.cs.register.service.StudentServiceImpl;

@Controller
public class StudentController {

	private StudentServiceImpl studentService;

	@Autowired
	public StudentController(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}

	@GetMapping(value = { "/eregistrar/students" })
	public ModelAndView GetAllStudent(@RequestParam(defaultValue = "0") int pageNo) {
		System.out.println(">>>> stud: "+pageNo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("students", studentService.getStudentPage(pageNo));
		mav.addObject("currentPageNo", pageNo);
		mav.setViewName("studentsList");

		return mav;
	}
	
	@GetMapping(value = { "/eregistrar/addStudent" })
	public String displayAddStudentForm(Model model) {
		model.addAttribute("student",new Student() );
		return "addStudent";
	}
	
	@PostMapping(value = { "/eregistrar/saveStudent" })
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			return "addStudent";
		}
		student = studentService.saveStudent(student);
		return "redirect:/eregistrar/students";
	}
	
	@GetMapping(value = { "/eregistrar/editStudent/{studentId}" })
	public String editStudent(@PathVariable Long studentId, Model model) {
		Student student= studentService.getStudentById(studentId);
		if (student != null) {
			model.addAttribute("student", student);
			return "editStudent";
		}
		return "studentsList";
	}
	
	@GetMapping(value = { "/eregistrar/deleteStudent/{studentId}" })
	public String deleteBook(@PathVariable Long studentId, Model model) {
		studentService.deleteStudentById(studentId);
		return "redirect:/eregistrar/students";
	}
	
	@GetMapping(value = "registrar/search")
	public ModelAndView search(@RequestParam("keyword") String keyword,
	@RequestParam(defaultValue = "0") int pageno) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("students", studentService.search(pageno,keyword));
		modelAndView.addObject("currentPageNo", 0);
		modelAndView.setViewName("studentsList");
		return modelAndView;
	}
}
