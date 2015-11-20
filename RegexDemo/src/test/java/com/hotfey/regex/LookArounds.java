package com.hotfey.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

public class LookArounds {
	@Ignore
	@Test
	public void positiveLookaheads() {
		String input = "1448005760373";
		String regex = "57(?=6)";
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
	public void negativeLookaheads() {
		String input = "1448005760373";
		String regex = "57(?!6)";
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
	public void positiveLookbehinds() {
		String input = "1448005760373";
		String regex = "(?<=57)6";
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
	public void negativeLookbehinds() {
		String input = "1448005760373";
		String regex = "(?<!57)6";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		boolean bl = matcher.find();
		System.out.println("Negative lookbehinds:" + bl);
		if (bl) {
			System.out.println(matcher.group());
		}
	}
}
