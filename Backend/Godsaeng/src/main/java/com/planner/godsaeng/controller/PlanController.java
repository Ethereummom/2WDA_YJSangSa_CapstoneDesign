package com.planner.godsaeng.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.service.PlanService;

@Controller
@RequestMapping("/plan")

public class PlanController {
	
	PlanService service = new PlanService(); 
	
	@PostMapping("/addplan")
	public String addPlan(@ModelAttribute PlanDTO d) {
		service.InsertPlan(d);
		return null;
	}
	
	@GetMapping("/listplan")
	public List<Plan> listPlan(Model m) {
		String currentuser_id = (String)(m.getAttribute("u_id"));
		LocalDateTime todaystime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		String realtodaystime = todaystime.format(formatter);
		return service.ReadDailyPlan(currentuser_id, realtodaystime);
		
	}
	
	@PostMapping("/updateplan")
	public String updatePlan(@ModelAttribute PlanDTO d) {
		service.UpdatePlan(d);
		return null;
		
	}
	@GetMapping("/deleteplan")
	public String deletePlan(@ModelAttribute PlanDTO d) {
		service.DeletePlan(d.getP_id());
		
		return null;
	}
	

}
