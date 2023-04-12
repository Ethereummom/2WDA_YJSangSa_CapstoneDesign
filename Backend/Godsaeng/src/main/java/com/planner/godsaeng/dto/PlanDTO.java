package com.planner.godsaeng.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PlanDTO {
	
	private long p_id;
	private String u_id;
	private LocalDateTime p_startdate;
	private LocalDateTime p_enddate;
	private LocalDateTime p_starttime;
	private LocalDateTime p_endtime;
	private String p_title;
	private String p_content;
	private String p_category;
	private int p_remindornot;

}
