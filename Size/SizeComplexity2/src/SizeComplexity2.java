
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SizeComplexity2 {

	private int Cs = 0; // Variable for Complexity due to Size (Cs)
	private String[] input = { "+", "-", "*", "/", "%", "++", "--", "void", "double", "int", "float", "String",
			"printf", "println", "cout", "cin", "if", "for", "while", "do-while", "switch", "case" };
	
	public void measureCompSize() throws Exception {

		FileReader fileReader = new FileReader("G:/Yr 3 Semester 2/SPM/tool/sh/sample.txt"); // Creating FileReader
		BufferedReader bfReader = new BufferedReader(fileReader); // Creating BufferedReader Object

		String CurrentLine = "", line;
		// Number variable set to Zero
		int numbers = 0;

		// Reading Content from the file
		while ((line = bfReader.readLine()) != null) {

			CurrentLine = line;

			// Replace all the numbers in every iteration
			numbers = line.replaceAll("[^0-9]", "").length();

			// Assign values for the array and
			findCharacter(CurrentLine, input, 1);
			System.out.println("Line " + this.Cs);

		}

		System.out.println("Complexity of the program statement due to Size :" + this.Cs);
	}

	// Find single character signs
	// Parameters = Current line, Input array to match and Value(Cs) assigned to the
	// sign detected
	public void findCharacter(String CurrentLine, String input[], int val) throws Exception {

		// Enhanced for loop
		// Read the whole array, str is assigned with First character of array
		for (String str : input) {
			if (str.length() <= 1) { // if the sign to be checked is a single sign
				singleChar(CurrentLine, str, val);
			} else {
				matchPattern(CurrentLine, str, val);
			}
		}
	}

	public void singleChar(String str, String ch, int val) throws Exception {

		// Remove the spaces in the line read
		str = str.replace(" ", "");

		// Check whether Array to be checked is null or not
		while (str.indexOf(ch) != -1) {

			// identify characters within quotes
			if (ch == "\"") {
				while (str.indexOf(ch) != -1) { // until the input array is empty
					str = str.substring(str.indexOf(ch) + 1); // get the substring from index of current item + 1

					while (str.indexOf(ch) != -1) {

						if (str.charAt(0) == ch.charAt(0)) { /* Compare 0th values of program and input array */
							str = str.substring(str.indexOf(ch) + 1);
							break; /* Substring and remove those signs and break */
						}

						str = str.substring(str.indexOf(ch) + 1); // Get the substring from index of current item + 1
						this.Cs = val + Cs; // Get value of Cs by adding the Value assigned to character
						break;
					}
				}
				break;
			}

			str = str.substring((str.indexOf(ch)));
			// System.out.println(str);
			if (str.length() == 1) { // At the end of the program get final Cs value
				this.Cs += val + Cs;
				break;

				/* If there are two or more of the Same character identified (ex : ++) */
			} else if ((str.charAt(str.indexOf(ch) + 1) == ch.charAt(0)) || str.charAt(str.indexOf(ch) + 1) == '=') {

				str = str.substring((str.indexOf(ch) + 2));

				if (str.length() == 0) {
					break;
				} else if ((ch == ">" || ch == "<") && (str.charAt(str.indexOf(ch)) == ch.charAt(0))) {

					str = str.substring((str.indexOf(ch) + 1));
				}

			} else {
				this.Cs += val;
				str = str.substring((str.indexOf(ch) + 1));
			}
		}
	}

	public void matchPattern(String str, String pattern, int val) {

		char[] input = str.toCharArray();
		char[] pat = pattern.toCharArray();

		int LS = input.length;					//Get input array and 
		int LP = pat.length;
		int max = (LS - LP + 1);
		boolean flag;

		for (int i = 0; i < max; i++) {			//flag is true until the loop ends
			flag = true;

			for (int j = 1; j <= LP && flag == true; j++) {		//flag is false until the loop ends

				if (pat[j - 1] != input[j + i - 1]) {
					flag = false;
				}
			}

			if (flag == true) {

				if ((i != 0 && str.toString().substring(i, i + LP).equals(">="))
						|| (i != 0 && str.toString().substring(i, i + LP).equals("<="))) {

					if (str.toString().charAt(i - 1) == '>' || str.toString().charAt(i - 1) == '<') {
						continue;
					}
				}

				if (str.toString().substring(i, i + LP).equals(">>")
						|| str.toString().substring(i, i + LP).equals("<<")) {

					if (str.toString().charAt(i + LP) == '=' || str.toString().charAt(i + LP) == '>'
							|| str.toString().charAt(i + LP) == '<') {

						flag = false;
						i++;
					} else {
						this.Cs += val;
					}

				} else if (str.toString().substring(i, i + LP).equals(">>>") && str.toString().charAt(i + LP) == '=') {

					flag = false;

				} else {
					this.Cs += val;
				}
			}
		}
	}

	//To get methods and 
	/*
	 * public void classElements(Class c,int val) {
	 * 
	 * Field[] field = c.getDeclaredFields(); Method[] method =
	 * c.getDeclaredMethods();
	 * 
	 * for(Field f:field) { System.out.println("method = " + f.toString()); }
	 * 
	 * for(Method m:method) { System.out.println("method = " + m.toString());
	 * patternM(m.toString(), "throws",val); } }
	 */

	public static void main(String[] args) throws Exception {

		SizeComplexity2 sc = new SizeComplexity2(); // Creating objects of the class
		sc.measureCompSize(); // Call Measure Complexity Size Method

	
		
	}
}
