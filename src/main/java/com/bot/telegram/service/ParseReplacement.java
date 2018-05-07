package com.bot.telegram.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bot.telegram.component.entities.Replacement;
import com.bot.telegram.component.entities.ReplacementGeneral;
import com.bot.telegram.component.interfaces.IReplacement;
import com.bot.telegram.component.interfaces.IReplacementGeneral;

/**
 * Parse replacements from hpk.edu.ua/replacements
 * 
 * @author yagi
 *
 */
@Service
public class ParseReplacement {

	@Autowired
	private IReplacement iReplacement;

	@Autowired
	private IReplacementGeneral iReplacementGeneral;

	/**
	 * Method for parsing replacements from hpk.edu.ua/replacements and save info in
	 * database (in two tables - replacement, which contains all replacements; and
	 * replacement_general, which contains general information for list
	 * replacements).
	 */
	public void parseReplacements() {
		Document doc;
		try {
			doc = Jsoup.connect("http://hpk.edu.ua/replacements").get();
			Elements tableWithReplacement = doc.select("#post-77 > div.news-body > table > tbody tr");

			// String parsePosition = doc.select("#post-77 > div.news-body > p:nth-child(2)
			// > strong:nth-child(3)").text();
			// String parseDayOfWeek = doc.select("#post-77 > div.news-body > p:nth-child(2)
			// > strong:nth-child(1)")
			// .text();

			String parseDayOfWeekAndPosition = doc.select("#post-77 > div.news-body > p:nth-child(2) > strong").text();

			String dayOfweek = parseDayOfWeekAndPosition.substring(0, parseDayOfWeekAndPosition.indexOf(" "));
			String position = parseDayOfWeekAndPosition.substring(parseDayOfWeekAndPosition.lastIndexOf(" ") + 1);

			String parseDateOfReplacement = doc.select("#post-77 > div.news-body > p:nth-child(1)").text();
			int indexOfStartForSubstringDateOfReplacement = 20;
			String dateOfReplacement = parseDateOfReplacement.substring(indexOfStartForSubstringDateOfReplacement);

			String parseTeacherOnDuty = doc
					.select("#post-77 > div.news-body > table > tbody > tr:nth-child(2) > td > strong").text();
			int indexOfStartForSubstringTeacherOnDuty = 29;
			String teacherOnDuty = parseTeacherOnDuty.substring(indexOfStartForSubstringTeacherOnDuty);

			// String parseGroupOnDuty = doc.select("#post-77 > div.news-body > table >
			// tbody > tr:nth-child(3) > td")
			// .text();

			String additionalInfoGoToClasses = doc
					.select("#post-77 > div.news-body > table > tbody > tr:nth-child(2) > td > p:nth-child(3) > strong")
					.text();

			String additionalInfoGoToPractice = doc
					.select("#post-77 > div.news-body > table > tbody > tr:nth-child(2) > td > p:nth-child(4) > strong")
					.text();

			// int indexOfStartForSubstringGroupOnDuty = 15;
			// String groupOnDuty =
			// parseGroupOnDuty.substring(indexOfStartForSubstringGroupOnDuty);

			ReplacementGeneral replacementGeneral = new ReplacementGeneral();

			replacementGeneral.setTeacherOnDuty(teacherOnDuty);
			// replacement.setGroupOnDuty(groupOnDuty);

			replacementGeneral.setGroupOnDuty(additionalInfoGoToClasses + " " + additionalInfoGoToPractice);

			replacementGeneral.setDateOfReplacement(dateOfReplacement);
			// replacement.setDayOfWeek(parseDayOfWeek);
			// replacement.setPosition(parsePosition);

			replacementGeneral.setDayOfWeek(dayOfweek);
			replacementGeneral.setPosition(position);

			iReplacementGeneral.save(replacementGeneral);

			for (int i = 0; i < tableWithReplacement.size(); i++) {
				if (tableWithReplacement.get(i).select("td").size() == 6) {
					Replacement replacement = new Replacement();

					if (tableWithReplacement.get(i).select("td:nth-child(1)").text().length() == 7) {
						continue;
					}

					replacement.setNameGroup(parseNameGroup(tableWithReplacement, replacement, i));

					replacement.setNumberOfCouple(tableWithReplacement.get(i).select("td:nth-child(2)").text());
					replacement.setTeacherReplacement(tableWithReplacement.get(i).select("td:nth-child(3)").text());
					replacement.setSubject(tableWithReplacement.get(i).select("td:nth-child(4)").text());
					replacement.setTeacher(tableWithReplacement.get(i).select("td:nth-child(5)").text());
					replacement.setClassroom(tableWithReplacement.get(i).select("td:nth-child(6)").text());

					replacement.setReplacementGeneralId(replacementGeneral.getId());

					iReplacement.save(replacement);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for correct parsing name of group (if field on site is null then name
	 * of group equals cell which located over this cell and not null)
	 * 
	 * @param tableWithReplacement
	 * @param replacement
	 * @param i
	 * @return String nameGroup
	 */
	private String parseNameGroup(Elements tableWithReplacement, Replacement replacement, int i) {
		String nameGroup;
		if (tableWithReplacement.get(i).select("td:nth-child(1)").text().length() != 0) {
			nameGroup = tableWithReplacement.get(i).select("td:nth-child(1)").text();
		} else {
			nameGroup = tableWithReplacement.get(i - 1).select("td:nth-child(1)").text();

			if (tableWithReplacement.get(i - 1).select("td:nth-child(1)").text().length() == 0) {
				nameGroup = tableWithReplacement.get(i - 2).select("td:nth-child(1)").text();

				if (tableWithReplacement.get(i - 2).select("td:nth-child(1)").text().length() == 0) {
					nameGroup = tableWithReplacement.get(i - 3).select("td:nth-child(1)").text();
				}
			}
		}
		return nameGroup;
	}

}
