package com.hotfey.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

public class RegexNumericRange {
	@Ignore
	@Test
	public void range() {
		String startRow = "1448017934380";
		String stopRow = "1448017934380";
		startRow = "100000001";
		stopRow = "100000001";

		int length = currentTimeMillis().length();
		length = 9;
		String regex = regexString(startRow, stopRow, length);
		if (regex.isEmpty()) {
			System.out.println("regex empty!");
		} else {
			System.out.println(regex);
			String input = "";
			int ok = 0;
			int no = 0;
			for (Long i = Long.parseLong(startRow); i <= Long.parseLong(stopRow); i++) {
				input = i.toString();
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(input);
				if (matcher.find()) {
					ok++;
				} else {
					no++;
				}
			}
			System.out.println("all" + (Long.parseLong(startRow) - Long.parseLong(stopRow) - 1));
			System.out.println("ok:" + ok);
			System.out.println("no:" + no);
		}
	}

	private String regexString(String startRow, String stopRow, int length) {

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

		if (i == 0) {
			return baseString;
		} else {
			return createRegex(baseString, startRowVariable, stopRowVariable, i);
		}
	}

	private String createRegex(String baseString, String startRowVariable, String stopRowVariable, int length) {
		char[] startChar = startRowVariable.toCharArray();
		char[] stopChar = stopRowVariable.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(baseString);

		stringBuffer.append("[");
		stringBuffer.append(startChar[0]);
		stringBuffer.append("-");
		stringBuffer.append(stopChar[0]);
		stringBuffer.append("]");

		stringBuffer.append("((");
		for (int i = 0; i < length - 1; i++) {
			stringBuffer.append("(?<!");
			for (int j = 0; j <= i; j++) {
				stringBuffer.append(startChar[j]);
			}
			int temp = Integer.parseInt(String.valueOf(startChar[i + 1]));
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
		for (int i = 0; i < length - 1; i++) {
			stringBuffer.append("(?<!");
			for (int j = 0; j <= i; j++) {
				stringBuffer.append(stopChar[j]);
			}
			int temp = Integer.parseInt(String.valueOf(stopChar[i + 1]));
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
		return Long.valueOf(System.currentTimeMillis()).toString();
	}
}
