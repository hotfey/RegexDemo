package com.hotfey.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

public class RegexTimestampRange {
	@Ignore
	@Test
	public void a() {
		String startRow = "1448017934380";
		String stopRow = "1448917934380";

		String regex = regexString(startRow, stopRow);
		if (regex.isEmpty()) {
			System.out.println("regex empty!");
		} else {
			System.out.println(regex);
			String input = "";
			for (int i = 0; i < 2; i++) {
				switch (i) {
				case 0:
					input = startRow;
					break;
				case 1:
					input = stopRow;
					break;
				default:
					break;
				}
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(input);
				if (matcher.find()) {
					System.out.println(matcher.group());
				}
			}
		}
	}

	private String regexString(String startRow, String stopRow) {
		int length = currentTimeMillis().length();
		if (startRow.length() != length) {
			return "";
		}
		if (stopRow.length() != length) {
			return "";
		}
		if (startRow.compareTo(stopRow) > 0) {
			return "";
		}

		String baseString = "";
		String startRowVariable = "";
		String stopRowVariable = "";

		int i = 0;
		for (i = 0; i <= length; i++) {
			baseString = startRow.substring(0, length - i);
			if (stopRow.startsWith(baseString)) {
				startRowVariable = startRow.substring(length - i);
				stopRowVariable = stopRow.substring(length - i);
				break;
			}
		}

		return createRegex(baseString, startRowVariable, stopRowVariable, i);
	}

	private String createRegex(String baseString, String startRowVariable, String stopRowVariable, int length) {
		if (length == 0) {
			return "";
		}
		char[] startChar = startRowVariable.toCharArray();
		char[] stopChar = stopRowVariable.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(baseString);

		stringBuffer.append("((");

		stringBuffer.append(startChar[0]);

		for (int i = 0; i < length - 1; i++) {
			stringBuffer.append("\\d");
			stringBuffer.append("(?<!");
			for (int j = 0; j <= i; j++) {
				stringBuffer.append(startChar[j]);
			}
			int temp = Integer.parseInt(startChar[i + 1] + "");
			if (temp == 0) {
				stringBuffer.append("[^0-");
				stringBuffer.append("9])");
			} else {
				stringBuffer.append("[0-");
				stringBuffer.append(temp - 1);
				stringBuffer.append("])");
			}
		}

		stringBuffer.append(")|(");

		stringBuffer.append(stopChar[0]);

		for (int i = 0; i < length - 1; i++) {
			stringBuffer.append("\\d");
			stringBuffer.append("(?<!");
			for (int j = 0; j <= i; j++) {
				stringBuffer.append(stopChar[j]);
			}
			int temp = Integer.parseInt(stopChar[i + 1] + "");
			if (temp == 9) {
				stringBuffer.append("[^0-");
				stringBuffer.append("9])");
			} else {
				stringBuffer.append("[");
				stringBuffer.append(temp + 1);
				stringBuffer.append("-9])");
			}
		}

		stringBuffer.append("))");

		return stringBuffer.toString();
	}

	private String currentTimeMillis() {
		return System.currentTimeMillis() + "";
	}
}
