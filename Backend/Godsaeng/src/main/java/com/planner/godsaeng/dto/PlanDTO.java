package com.planner.godsaeng.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PlanDTO {
	
	private long p_id;
	private String u_id;
	private String p_startdate;
	private String p_enddate;
	private String p_starttime;
	private String p_endtime;
	private String p_title;
	private String p_content;
	private String p_category;
	private int p_remindornot;

}
