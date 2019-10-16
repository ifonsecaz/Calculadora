/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ExpresionTest {
    
    public ExpresionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of evaluarPostfija method, of class Expresion.
     */
    @Test
    public void testEvaluarPostfija() {
        System.out.println("evaluarPostfija");
        String expresionPostfija = "2 10 +";
        String expResult = Double.toString(12);
        String result = Expresion.evaluarPostfija(expresionPostfija);
        assertEquals(expResult, result);
    }

    /**
     * Test of convertirPostfija method, of class Expresion.
     */
    @Test
    public void testConvertirPostfija() {
        System.out.println("convertirPostfija");
        String expresionInfija ="4+10/2";
        String expResult ="4 10 2 / + ";
        String result = Expresion.convertirPostfija(expresionInfija);
        assertEquals(expResult, result);
    }

    /**
     * Test of detectorErroresSintaxis method, of class Expresion.
     */
    @Test
    public void testDetectorErroresSintaxis() {
        System.out.println("detectorErroresSintaxis");
        String prob = "6-((";
        boolean expResult = false;
        boolean result = Expresion.detectorErroresSintaxis(prob);
        assertEquals(expResult, result);
    }

    /**
     * Test of detectorErroresMath method, of class Expresion.
     */
    @Test
    public void testDetectorErroresMath() {
        System.out.println("detectorErroresMath");
        String prob = "19/0+5";
        boolean expResult = false;
        boolean result = Expresion.detectorErroresMath(prob);
        assertEquals(expResult, result);
    }
    
}
