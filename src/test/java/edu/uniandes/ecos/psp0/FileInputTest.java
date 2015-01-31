package edu.uniandes.ecos.psp0;

import edu.uniandes.ecos.psp0.model.FileInput;
import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FileInputTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FileInputTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FileInputTest.class );
    }
    
    private LinkedList<Double> getTestList(){
    	LinkedList<Double> inputs = new LinkedList<Double>();
    	inputs.add(15.0D);
    	inputs.add(69.9D);
    	inputs.add(6.5D);
    	inputs.add(22.4D);
    	inputs.add(28.4D);
    	inputs.add(65.9D);
    	inputs.add(19.4D);
    	inputs.add(198.7D);
    	inputs.add(38.8D);
    	inputs.add(138.2D);
    	return inputs;
    }
    
    /**
     * Test the load of file
     */
    public void testLoadFile(){
    	FileInput inputTest = new FileInput();
    	//Test: Non-existing file
    	assertFalse(inputTest.getFile("DoesNotExist.txt"));
    	
    	//Test: Existing file
    	ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println("Erdaaa aquí vaaa"+classLoader.getResource("test.txt"));
    	assertTrue(inputTest.getFile(classLoader.getResource("test.txt").getFile()));
    }
    
    /**
     * Tests the load of the numbers
     */
    public void testLoadNumbers(){
    	ClassLoader classLoader = this.getClass().getClassLoader();
    	FileInput inputTest = new FileInput();
    	inputTest.getFile(classLoader.getResource("test.txt").getFile());
    	inputTest.readNumbersFromFile();
    	assertEquals(getTestList(), inputTest.getValuesFromFile());
    }

}
