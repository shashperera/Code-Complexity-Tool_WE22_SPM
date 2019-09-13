import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;

public class TestJUnit {


	   @Test
	   public void testSizeComplexity() throws Exception {
		   SizeComplexity2 sc=new SizeComplexity2();  //Creating objects of the class
			sc.measureCompSize();
	      Assert.assertEquals(17, 19);
	   }

	
	}