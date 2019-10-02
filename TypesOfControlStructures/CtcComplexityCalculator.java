
public class CtcComplexityCalculator {
	public static int FindIf(String str) {
		int ctc = 1;
		String word = null;
		String words[] = str.split(" ");

		if (str.contains("&") || str.contains("&&") || str.contains("|") || str.contains("||")) {
			for (int j = 0; j < words.length; j++) {
				if (words[j].equals("&")) {
					ctc++;
				}
				if (words[j].equals("&&")) {
					ctc++;
				}
				if (words[j].equals("|")) {
					ctc++;
				}
				if (words[j].equals("||")) {
					ctc++;
				}
			}

		}
		return ctc;
	}

	public static int FindLoops(String str) {
		int ctc = 2;

		String words[] = str.split(" ");

		if (str.contains("&") || str.contains("&&") || str.contains("|") || str.contains("||")) {
			for (int j = 0; j < words.length; j++) {
				if (words[j].equals("&")) {
					ctc += 2;
				}
				if (words[j].equals("&&")) {
					ctc += 2;
				}
				if (words[j].equals("|")) {
					ctc += 2;
				}
				if (words[j].equals("||")) {
					ctc += 2;
				}
			}
		}
		return ctc;
	}

	public static int FindSwitch(String str) {
		String word = null;
		int ctc = 0;

		if (str.contains("case ")) {
			ctc = 1;
			return ctc;
		}
		return ctc;
	}

}
