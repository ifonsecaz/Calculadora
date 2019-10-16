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
 * @author jaimevarela
 */
public class PilaArregloTest {
    
    public PilaArregloTest() {
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
     * Test of push method, of class PilaArreglo.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        Object dato = "test";
        PilaArreglo instance = new PilaArreglo();
        instance.push(dato);
    }

    /**
     * Test of pop method, of class PilaArreglo.
     */
    @Test
    public void testPop() {
        System.out.println("pop");
        PilaArreglo instance = new PilaArreglo();
        instance.push("prueba");
        Object expResult = "prueba";
        Object result = instance.pop();
        assertEquals(expResult, result);
    }

    /**
     * Test of peek method, of class PilaArreglo.
     */
    @Test
    public void testPeek() {
        System.out.println("peek");
        PilaArreglo instance = new PilaArreglo();
        instance.push("prueba");
        Object expResult = "prueba";
        Object result = instance.peek();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class PilaArreglo.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        PilaArreglo instance = new PilaArreglo();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }
    
}
