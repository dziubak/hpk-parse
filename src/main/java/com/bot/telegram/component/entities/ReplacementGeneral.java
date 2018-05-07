package com.bot.telegram.component.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for replacement which contains general information of replacements for
 * each replacement
 * 
 * @author yagi
 *
 */
@Entity
@Table(name = "replacement_general")
public class ReplacementGeneral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "teacher_on_duty")
	private String teacherOnDuty;

	@Column(name = "group_on_duty")
	private String groupOnDuty;

	@Column(name = "position")
	private String position;

	@Column(name = "date_of_replacement")
	private String dateOfReplacement;

	@Column(name = "day_of_week")
	private String dayOfWeek;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeacherOnDuty() {
		return teacherOnDuty;
	}

	public void setTeacherOnDuty(String teacherOnDuty) {
		this.teacherOnDuty = teacherOnDuty;
	}

	public String getGroupOnDuty() {
		return groupOnDuty;
	}

	public void setGroupOnDuty(String groupOnDuty) {
		this.groupOnDuty = groupOnDuty;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDateOfReplacement() {
		return dateOfReplacement;
	}

	public void setDateOfReplacement(String dateOfReplacement) {
		this.dateOfReplacement = dateOfReplacement;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

}
