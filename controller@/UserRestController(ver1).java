package com.planner.godsaeng.api;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.model.UserDTO;
import com.planner.godsaeng.model.UserDAO;

@RestController
public class UserRestController {
	final UserDAO dao;
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public UserRequestController(UserDAO dao) {
		this.dao = dao;
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
	@PostMapping("/login")
	public String login(UserDTO sign, HttpSession session, Model model) {
		boolean result = UserDAO.loginSign(sign);
		
		if(result) {
			session.setAttribute("loginState", "login");
			session.setAttribute("id", sign.getSignup_email());
			session.setAttribute("pw", sign.getSignup_password());
			return "redirect:index";
		}
		else {
			model.addAttribute("message", "회원정보가 일치하지 않습니다.");
			return "login";
		}
		
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.setAttribute("id", null);
		session.setAttribute("pw", null);
		session.setAttribute("loginState", "logout");
		
		model.addAttribute("message", "로그아웃되었습니다.");
		return "redirect:/index";
	}
	
	@GetMapping("/list")
	public String listData(Model m) {
		List<UserDTO> list;
		try {
			list = dao.getAll();
			m.addAttribute("datalist",list);
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("유저 정보 목록이 생성 과정에서 문제 발생!!");
			m.addAttribute("error","유저 정보 목록이 정상적으로 처리되지 않았습니다!!");
		}
		return "/datas/datatreat";
	}
	
	
	@GetMapping("/add")
	public String addDatas(@ModelAttribute UserDTO d, Model m) {
		try {
			dao.addUser(d);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("유저 정보 추가 과정에서 문제 발생!");
			m.addAttribute("error","유저 정보가 정상적으로 등록되지 않았습니다!!");
		}
		return "redirect:/yjserver/list";
	}
	
	@GetMapping("/delete/{num}")
	public String deleteDatas(@PathVariable int num, Model m) {
		try {
			dao.delUser(num);
		}catch(SQLException e) {
			e.printStackTrace();
			logger.warn("유저 정보 삭제 과정에서 문제 발생!!");
			m.addAttribute("error","유저 정보가 정상적으로 삭제되지 않았습니다.");
			}
		return "redirect:/yjserver/list";
	}
	
	@GetMapping("/update/{num}")
	public String addDatas(@ModelAttribute UserDTO d, Model m) {
		try {
			dao.updateUser(d);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("유저 정보 수정 과정에서 문제 발생!");
			m.addAttribute("error","유저 정보가 정상적으로 수정되지 않았습니다!!");
		}
		return "redirect:/yjserver/list";
	}
}
