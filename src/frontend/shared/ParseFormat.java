package frontend.shared;

public class ParseFormat {

	public static Long tryParseToLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
}
