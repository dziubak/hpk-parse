package com.bot.telegram.component.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for replacement which contains main information of replacements
 * 
 * @author yagi
 *
 */
@Entity
@Table(name = "replacement")
public class Replacement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name_group")
	private String nameGroup;

	@Column(name = "number_of_couple")
	private String numberOfCouple;

	@Column(name = "teacher_replacement")
	private String teacherReplacement;

	@Column(name = "subject")
	private String subject;

	@Column(name = "teacher")
	private String teacher;

	@Column(name = "classroom")
	private String classroom;

	@Column(name = "replacement_general_id")
	private int replacementGeneralId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	public String getNumberOfCouple() {
		return numberOfCouple;
	}

	public void setNumberOfCouple(String numberOfCouple) {
		this.numberOfCouple = numberOfCouple;
	}

	public String getTeacherReplacement() {
		return teacherReplacement;
	}

	public void setTeacherReplacement(String teacherReplacement) {
		this.teacherReplacement = teacherReplacement;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public int getReplacementGeneralId() {
		return replacementGeneralId;
	}

	public void setReplacementGeneralId(int replacementGeneralId) {
		this.replacementGeneralId = replacementGeneralId;
	}

}