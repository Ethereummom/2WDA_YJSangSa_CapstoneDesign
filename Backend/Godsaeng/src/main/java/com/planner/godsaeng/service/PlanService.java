package com.planner.godsaeng.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.repository.PlanRepository;

public class PlanService {
	
	Plan plan = null;
	@Autowired
	PlanRepository planRepository;
	
	//플랜 CREATE(INSERT)
	public boolean InsertPlan(PlanDTO d) {
		plan = Plan.builder()
				.p_id(d.getP_id())
				.u_id(d.getU_id())
				.p_startdate(d.getP_startdate())
				.p_enddate(d.getP_enddate())
				.p_starttime(d.getP_starttime())
				.p_endtime(d.getP_endtime())
				.p_title(d.getP_title())
				.p_content(d.getP_content())
				.p_category(d.getP_category())
				.p_remindornot(d.getP_remindornot())
				.build();
		
		try {
		planRepository.save(plan);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
	
	public List<Plan>ReadDailyPlan(String u_id,String p_startdate) {
		return planRepository.findByUidAndPStartDateOrderByPStartTimeAsc(u_id, p_startdate);
		
		
	}
	
	public boolean UpdatePlan(PlanDTO d) {
		plan = Plan.builder()
				.p_id(d.getP_id())
				.u_id(d.getU_id())
				.p_startdate(d.getP_startdate())
				.p_enddate(d.getP_enddate())
				.p_starttime(d.getP_starttime())
				.p_endtime(d.getP_endtime())
				.p_title(d.getP_title())
				.p_content(d.getP_content())
				.p_category(d.getP_category())
				.p_remindornot(d.getP_remindornot())
				.build();
		try {
			planRepository.save(plan);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}	
	}
	
	
	public boolean DeletePlan(long p_id) {
		try {
			planRepository.deleteById(p_id);
			return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	

}
