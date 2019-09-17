import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class CiComplexityCalculator {
	

	public static final String TYPE = "java";

	public int[] inheritanceComplexity(String SOURCE_CODE_PATH) throws Exception {

		
		FileReader file = new FileReader(SOURCE_CODE_PATH);
		BufferedReader reader = new BufferedReader(file);

		
		int lines = 0;
		while (reader.readLine() != null)
			lines++;
		reader.close();
		
		
		System.out.println("Number of lines: " + lines);

		
		int ciArray[] = printCi(lines, TYPE, SOURCE_CODE_PATH);
		/*
		 * for (int i = 0; i < lines; i++) { 
		 * //System.out.println("Ci " + "line " + (i +
		 * 1) + ": " + ciArray[i]);
		 * 
		 * }
		 */
		return ciArray;
		
	}

	public int[] printCi(int Size, String Type,String SOURCE_CODE_PATH) throws Exception {

		File file = new File(SOURCE_CODE_PATH);
		Scanner scanner = new Scanner(file);

		File file1 = new File(SOURCE_CODE_PATH);
		Scanner scanner1 = new Scanner(file1);

		int arraySize = Size;

		int Ci[] = new int[arraySize];

		int defaultValue = 0;

//		if (Type == "java") {
//			defaultValue = 1;
//
//		} else {
//			defaultValue = 0;
//		}

		int i = 0;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			if (line.contains("extends ")) {
				defaultValue = defaultValue + 1;
			}
			if (line.contains("implements ")) {
				defaultValue = defaultValue + 1;
			}
			
			for (int x = 0; x < arraySize; x++) {
				Ci[x] = defaultValue;
			}
			
		}
		
		//line1.contains("class ") || 
		//|| line1.equals("else {") || line1.equals("else{")
		//|| line1.equals("\\r?\\n")
		//("(\bString\b)(?=.*?\bvoid\b)(?=.*?\bSystem.\b).*$)")
		while (scanner1.hasNextLine()) {

			String line1 = scanner1.nextLine();

		
			if (line1.contains("String ")|| line1.contains("Physics ")|| line1.contains("obj") || line1.contains("void ")||line1.contains("System")  
					 || line1.equals("}") || line1.equals("")  ) {
				Ci[i] = 0;
			}
			i++;
		}
			
		System.out.println("Total inheritance: " + defaultValue);
		return Ci;
		
		
	}
	

}
