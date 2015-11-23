package com.hotfey.regex;

public class RegexNumericRange {
	/**
	 * @param prefixLength Prefix random numeric length
	 * @param startNumeric
	 * @param stopNumeric
	 * @return
	 */
	public static String regexNumericRange(int prefixLength, long startNumeric, long stopNumeric) {
		return regexNumericRange(prefixLength, startNumeric, stopNumeric, 0);
	}

	/**
	 * @param prefixLength Prefix random numeric length
	 * @param startNumeric
	 * @param stopNumeric
	 * @param suffixLength Suffix random numeric length
	 * @return
	 */
	public static String regexNumericRange(int prefixLength, long startNumeric, long stopNumeric, int suffixLength) {
		String startIndex = String.valueOf(startNumeric);
		String stopIndex = String.valueOf(stopNumeric);
		int length = (startIndex.length() == stopIndex.length() ? startIndex.length() : 0);

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("^");
		stringBuffer.append("\\d{");
		stringBuffer.append(prefixLength);
		stringBuffer.append("}");
		stringBuffer.append(regexString(startIndex, stopIndex, length));
		stringBuffer.append("\\d{");
		stringBuffer.append(suffixLength);
		stringBuffer.append("}");

		return stringBuffer.toString();
	}

	private static String regexString(String startIndex, String stopIndex, int length) {
		if (length == 0) {
			return "";
		}
		if (startIndex.compareTo(stopIndex) > 0) {
			return "";
		}

		String baseString = "";
		String startIndexVariable = "";
		String stopIndexVariable = "";

		int i = 0;
		for (i = 0; i <= length; i++) {
			baseString = startIndex.substring(0, length - i);
			if (stopIndex.startsWith(baseString)) {
				startIndexVariable = startIndex.substring(length - i);
				stopIndexVariable = stopIndex.substring(length - i);
				break;
			}
		}

		if (i == 0) {
			return baseString;
		} else {
			return createRegex(baseString, startIndexVariable, stopIndexVariable, i);
		}
	}

	private static String createRegex(String baseString, String startIndexVariable, String stopIndexVariable,
			int length) {
		char[] startChar = startIndexVariable.toCharArray();
		char[] stopChar = stopIndexVariable.toCharArray();
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
}
