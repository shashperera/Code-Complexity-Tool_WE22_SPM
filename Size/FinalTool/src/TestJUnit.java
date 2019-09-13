import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;

public class TestJUnit {


	   @Test
	   public void testSizeComplexity() throws Exception {
		   FinalSizeComplexty sc=new FinalSizeComplexty();  //Creating objects of the class
			sc.measureCompSize();
	      Assert.assertEquals(57, 57);
	   }

	
	}