package com.hotfey.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

public class LookAroundsExtra {
	@Ignore
	@Test
	public void positiveLookaheadsExtra() {
		String input = "1448005760373";
		String regex = "57(?=[6-9])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		boolean bl = matcher.find();
		System.out.println("Positive lookaheads:" + bl);
		if (bl) {
			System.out.println(matcher.group());
		}
	}

	@Ignore
	@Test
	public void negativeLookaheadsExtra() {
		String input = "1448005760373";
		String regex = "57(?![6-9])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		boolean bl = matcher.find();
		System.out.println("Negative lookaheads:" + bl);
		if (bl) {
			System.out.println(matcher.group());
		}
	}

	@Ignore
	@Test
	public void positiveLookbehindsExtra() {
		String input = "1448005760373";
		String regex = "(?<=57)[6-9]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		boolean bl = matcher.find();
		System.out.println("Positive lookbehinds:" + bl);
		if (bl) {
			System.out.println(matcher.group());
		}
	}

	@Ignore
	@Test
	public void negativeLookbehindsExtra() {
		String input = "1448005760373";
		String regex = "(?<!57)[6-9]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		boolean bl = matcher.find();
		System.out.println("Negative lookbehinds:" + bl);
		if (bl) {
			System.out.println(matcher.group());
		}
	}
}
