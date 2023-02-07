package com.fmi.oop_i.services;

public class ExternalDomainsService {
	public static final String[] BANNED_TLDS = new String[] { ".biz" };
	
	public String[] getBannedTLDs() {
		return BANNED_TLDS;
	}

	public String tldRegexFor(String domain) {
		return ".*" + domain + "(?!\\.).*";
	}

	public boolean urlMatchesTLD(String shopURL, String domain) {
		return shopURL.matches(tldRegexFor(domain));
	}
}
