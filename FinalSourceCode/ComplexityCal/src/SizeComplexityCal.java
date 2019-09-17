import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeComplexityCal {
	private String[] Arithmetic= {"+","-","*","/","%","=","|",".",">"};
	private String[] Keywords= {"void","double","int","long","float","String"};
	private String[] KeywordsOther= {"printf","println","cout","cin","if","for","while","do-while","switch","case","System","out"};
	private String[] Manipulators= {"endl","\n"};	
	private String[] Keywords2= {"new","delete","throws"};
	private ArrayList<String> var=new ArrayList<>();
	private ArrayList<String> tokens;
	 
	public ArrayList measureSize(String str) throws Exception{
				
		tokens = new ArrayList<>();	
		String CurrentLine=str;
								
			regexChecker(CurrentLine, "dd", 1);
			detectChar(CurrentLine,Arithmetic,1);
			detectChar(CurrentLine,Keywords,1);
			detectChar(CurrentLine,Keywords2,2);
			detectChar(CurrentLine,Manipulators,1);
			detectChar(CurrentLine, KeywordsOther, 1);
			
			for(String k:var) {
				regexCheckerKeyword(CurrentLine,k,1,false);
			}
			
			return tokens;	
	}
	
	public void detectChar(String CurrentLine,String ch[],int val) throws Exception {
		
		for(String k:ch) {
			
			if(ch==Keywords) {
				regexCheckerKeyword(CurrentLine,k,val,true);
			}else if(k.length()<=1) {		
				regexChecker(CurrentLine,k,val);	
			}else {		
				regexCheckerKeyword(CurrentLine,k,val,false);		 
			}		
		}		
	}
	
	public void regexCheckerKeyword(String str,String ch,int val,boolean keyword) {
		
		String variable = "";
		Pattern p;
		Matcher m;
		
		if(keyword==true) {
			p = Pattern.compile("[\\s+\\(?\\{?]"+ch+"\\s+[A-Za-z]+");
	        m = p.matcher(str);
		}else {
			p = Pattern.compile(ch);
	        m = p.matcher(str);
		}
		 
        while(m.find()) {
        	 	
        	//System.out.print(m.group().replaceAll("[^A-Za-z]", "").substring(0,ch.length()).trim()+", ");
        	tokens.add(m.group().replaceAll("[^A-Za-z]", "").substring(0,ch.length()).trim()+", ");
      	  	
        	if(keyword==true) {
        		
        		variable=m.group().substring(ch.length()+1).trim();
        		
    			if(!var.contains(variable)) {
    					var.add(variable);
    					//System.out.print("(added variable:"+variable+")");
    			}
        	}
        }
	}
	
	public void regexChecker(String str,String ch,int val) throws Exception{
		
		Pattern p;
		Matcher m;
		
		if(ch=="dd") {
			p = Pattern.compile("\\d+|\\\"([^\\\"]*)\\\"");
	        m = p.matcher(str);
		}else if(ch=="="){
			p = Pattern.compile("[\\s\\d\\w][\\=]+");
	        m = p.matcher(str);
		}else{
			p = Pattern.compile("\\"+ch+"+.");
	        m = p.matcher(str);
		}
		
        while(m.find()) {	
        	tokens.add(m.group()+", ");
        }  
	}	
	


}
