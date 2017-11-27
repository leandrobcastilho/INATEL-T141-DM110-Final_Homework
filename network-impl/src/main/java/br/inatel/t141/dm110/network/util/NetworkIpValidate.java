package br.inatel.t141.dm110.network.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkIpValidate {

	public static boolean isIpv4(String ipAddress) {
		if (ipAddress == null) {
			return false;
		}
		String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
	
	public static boolean isCidr(String cidr) {
		boolean isValid = false;
		try {
			if (Integer.valueOf(cidr)>=0 && Integer.valueOf(cidr)<=32)
				isValid = true;
		} catch (NumberFormatException e) {
			isValid = false;
		}
		return isValid;
	}
	
}
