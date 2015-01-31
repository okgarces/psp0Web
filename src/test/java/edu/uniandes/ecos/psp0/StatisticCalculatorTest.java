package edu.uniandes.ecos.psp0;

import edu.uniandes.ecos.psp0.model.StatisticCalculator;
import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StatisticCalculatorTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StatisticCalculatorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( StatisticCalculatorTest.class );
    }
    
    private LinkedList<Double> getTestList1(){
    	LinkedList<Double> inputs = new LinkedList<Double>();
    	inputs.add(160D);
    	inputs.add(591D);
    	inputs.add(114D);
    	inputs.add(229D);
    	inputs.add(230D);
    	inputs.add(270D);
    	inputs.add(128D);
    	inputs.add(1657D);
    	inputs.add(624D);
    	inputs.add(1503D);
    	return inputs;
    }
    
    private LinkedList<Double> getTestList2(){
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
     * Test for Mean Calculations
     */
    public void testMeanCalc(){
    	StatisticCalculator calc = new StatisticCalculator();
    	//First Set of Inputs
    	calc.setInputData(getTestList1());
    	assertEquals(550.6D, calc.calculateMean(), 1e-8D);
    	//Second set of inputs
    	calc.setInputData(getTestList2());
    	assertEquals(60.32D, calc.calculateMean(), 1e-8D);
    }
    
    /**
     * Test for Standard Deviation Calculations
     */
    public void testStdDevCalc(){
    	StatisticCalculator calc = new StatisticCalculator();
    	//First Set of Inputs
    	calc.setInputData(getTestList1());
    	assertEquals(572.03D, calc.calculateStdDev(), 0.01);
    	//Second set of inputs
    	calc.setInputData(getTestList2());
    	assertEquals(62.26D, calc.calculateStdDev(), 0.01);
    }

}
