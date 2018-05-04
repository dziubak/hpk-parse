package com.bot.telegram.component.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bot.telegram.component.entities.Replacement;
import com.bot.telegram.component.interfaces.IReplacement;

@Repository
public class ReplacementDao implements IReplacement {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_REPLACEMENT = "INSERT INTO replacement (teacher_on_duty, group_on_duty, position, date_of_replacement, day_of_week, name_group, "
			+ "number_of_couple, teacher_replacement, subject, teacher, classroom) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";

	public static class rowMapForReplacementWithDb implements RowMapper<Replacement> {

		@Override
		public Replacement mapRow(ResultSet rs, int rowNum) throws SQLException {
			Replacement replacement = new Replacement();
			replacement.setTeacherOnDuty(rs.getString(1));
			replacement.setGroupOnDuty(rs.getString(2));
			replacement.setPosition(rs.getString(3));
			replacement.setDateOfReplacement(rs.getString(4));
			replacement.setDayOfWeek(rs.getString(5));
			replacement.setNameGroup(rs.getString(6));
			replacement.setNumberOfCouple(rs.getString(7));
			replacement.setTeacherReplacement(rs.getString(8));
			replacement.setSubject(rs.getString(9));
			replacement.setTeacher(rs.getString(10));
			replacement.setClassroom(rs.getString(11));

			return replacement;
		}

	}

	public void insertReplacement(Replacement replacement) {
		jdbcTemplate.update(INSERT_REPLACEMENT, replacement.getTeacherOnDuty(), replacement.getGroupOnDuty(),
				replacement.getPosition(), replacement.getDateOfReplacement(), replacement.getDayOfWeek(),
				replacement.getNameGroup(), replacement.getNumberOfCouple(), replacement.getTeacherReplacement(),
				replacement.getSubject(), replacement.getTeacher(), replacement.getClassroom());
	}
}
