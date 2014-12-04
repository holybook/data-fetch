package ws.holybook.utils;

import java.text.Normalizer;

public class IdUtil {

	public static String encode(String input) {
		String nrml = Normalizer.normalize(input, Normalizer.Form.NFD);
		StringBuilder stripped = new StringBuilder();
		for (int i = 0; i < nrml.length(); ++i) {
			char c = nrml.charAt(i);
			if (Character.getType(c) != Character.NON_SPACING_MARK
					&& (Character.isLetterOrDigit(c) || c == ' ' || c == '-' || c == '_')) {
				if (c != ' ' && c != '-') {
					stripped.append(nrml.charAt(i));
				} else {
					stripped.append('_');
				}
			}
		}
		return stripped.toString();
	}
	
}
