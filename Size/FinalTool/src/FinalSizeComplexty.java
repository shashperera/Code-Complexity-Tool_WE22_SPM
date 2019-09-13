
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FinalSizeComplexty {
	

	private int Cs=0; // Variable for Complexity due to Size (Cs)
	private String[] arithmeticRealtionOps= {"+","-","*","/","%","=","|",".",">"};
	private String[] keywords= {"void","double","int","long","float","String"};
	private String[] otherKeywords= {"printf","println","cout","cin","if","for","while","do-while","switch","case","System","out"};
	private String[] manipulators= {"endl","\n"};
	private boolean flag=false;
	
	//private String[] Keywords2= {"new","delete","throws"};
	private ArrayList<String> var=new ArrayList<>();
	 
	public void measureCompSize() throws Exception{
			
		FileReader fileReader = new FileReader("G:/Yr 3 Semester 2/SPM/tool/sh/sample.txt"); // Creating FileReader
		BufferedReader bufferedReader = new BufferedReader(fileReader); // Creating BufferedReader Object
		
		String CurrentLine="",line;
		
		while((line = bufferedReader.readLine())!= null) {
			
			CurrentLine=line;
			
			if(flag==true) {
				if(CurrentLine.indexOf("*/") != -1) {
					CurrentLine=CurrentLine.substring(CurrentLine.indexOf("*/"),CurrentLine.length());
					flag=false;		
				}else {
					continue;
				}		
			}else {
				
				if(CurrentLine.indexOf("/*") != -1) {	
					CurrentLine=CurrentLine.substring(0,CurrentLine.indexOf("/*"));
					flag=true;
				}	
			}
			
			if(CurrentLine.indexOf("//") != -1) {
				CurrentLine=CurrentLine.substring(0,CurrentLine.indexOf("//"));
			}
			
			regexChecker(CurrentLine, "dd", 1);
			findCharacter(CurrentLine,arithmeticRealtionOps,1);
			findCharacter(CurrentLine,keywords,1);
			findCharacter(CurrentLine,manipulators,1);
			findCharacter(CurrentLine, otherKeywords, 1);
			
			for(String k:var) {
				regexCheckerKeyword(CurrentLine,k,1,false);
			}
			
			System.out.println("");
		}
		
		System.out.println("Result :"+this.Cs);	
	}
	
	public void findCharacter(String CurrentLine,String ch[],int val) throws Exception {
		
		for(String k:ch) {
			
			if(ch==keywords) {
				regexCheckerKeyword(CurrentLine,k,val,true);
			}else if(k.length()<=1) {		
				regexChecker(CurrentLine,k,val);	
			}else {		
				regexCheckerKeyword(CurrentLine,k,val,false);		 
			}		
		}		
	}
	

public void regexCheckerKeyword(String stri,String ch,int val,boolean keyword) {
		
		String variable = "";
		Pattern p;
		Matcher m;
		
		if(keyword==true) {
			p = Pattern.compile("[\\s+\\(?\\{?]"+ch+"\\s+[A-Za-z]+");
	        m = p.matcher(stri);
		}else {
			p = Pattern.compile(ch);
	        m = p.matcher(stri);
		}
		 
        while(m.find()) {
        	 
        	System.out.print(m.group().replaceAll("[^A-Za-z]", "").substring(0,ch.length()).trim()+", ");
        	this.Cs+=val;  
        	  	
        	if(keyword==true) {
        		
        		variable=m.group().substring(ch.length()+1).trim();
        		
    			if(!var.contains(variable)) {
    					var.add(variable);
    					//System.out.print("(added variable:"+variable+")");
    			}
        	}
        }
	}


	public void regexChecker(String stri,String ch,int val) throws Exception{
		
		Pattern p;
		Matcher m;
		
		if(ch=="dd") {
			p = Pattern.compile("\\d+|\\\"([^\\\"]*)\\\"");
	        m = p.matcher(stri);
		}else if(ch=="="){
			p = Pattern.compile("[\\s\\d\\w][\\=]+");
	        m = p.matcher(stri);
		}else{
			p = Pattern.compile("\\"+ch+"+.");
	        m = p.matcher(stri);
		}
		
        while(m.find()) {	
        	System.out.print(m.group()+", ");
           	this.Cs+=val; 
        }  
	}

	

}
