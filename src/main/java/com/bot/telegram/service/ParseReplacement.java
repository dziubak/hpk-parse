package com.bot.telegram.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bot.telegram.component.entities.Replacement;
import com.bot.telegram.component.interfaces.IReplacement;

@Component
@EnableScheduling
public class ParseReplacement {

	@Autowired
	private IReplacement iReplacement;

	@Scheduled(fixedRate = 60 * 1000)
	public void parseHtml() {
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

			for (int i = 0; i < tableWithReplacement.size(); i++) {
				if (tableWithReplacement.get(i).select("td").size() == 6) {
					Replacement replacement = new Replacement();

					replacement.setTeacherOnDuty(teacherOnDuty);
					// replacement.setGroupOnDuty(groupOnDuty);

					replacement.setGroupOnDuty(additionalInfoGoToClasses + " " + additionalInfoGoToPractice);

					replacement.setDateOfReplacement(dateOfReplacement);
					// replacement.setDayOfWeek(parseDayOfWeek);
					// replacement.setPosition(parsePosition);

					replacement.setDayOfWeek(dayOfweek);
					replacement.setPosition(position);

					if (tableWithReplacement.get(i).select("td:nth-child(1)").text().length() == 7) {
						continue;
					}

					replacement.setNameGroup(parseNameGroup(tableWithReplacement, replacement, i));

					replacement.setNumberOfCouple(tableWithReplacement.get(i).select("td:nth-child(2)").text());
					replacement.setTeacherReplacement(tableWithReplacement.get(i).select("td:nth-child(3)").text());
					replacement.setSubject(tableWithReplacement.get(i).select("td:nth-child(4)").text());
					replacement.setTeacher(tableWithReplacement.get(i).select("td:nth-child(5)").text());
					replacement.setClassroom(tableWithReplacement.get(i).select("td:nth-child(6)").text());

					iReplacement.insertReplacement(replacement);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param tableWithReplacement
	 * @param replacement
	 * @param i
	 * @return String nameGroup
	 */
	private String parseNameGroup(Elements tableWithReplacement, Replacement replacement, int i) {
		String nameGroup;
		if (tableWithReplacement.get(i).select("td:nth-child(1)").text().length() != 1) {
			nameGroup = tableWithReplacement.get(i).select("td:nth-child(1)").text();
		} else {
			nameGroup = tableWithReplacement.get(i - 1).select("td:nth-child(1)").text();

			if (tableWithReplacement.get(i - 1).select("td:nth-child(1)").text().length() == 1) {
				nameGroup = tableWithReplacement.get(i - 2).select("td:nth-child(1)").text();

				if (tableWithReplacement.get(i - 2).select("td:nth-child(1)").text().length() == 1) {
					nameGroup = tableWithReplacement.get(i - 3).select("td:nth-child(1)").text();
				}
			}
		}
		return nameGroup;
	}

}
