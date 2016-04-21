package ahmedowian.code_katas.natural_numbers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NaturalNumberGeneratorTest
{
    @Test
    public void test0()
    {
        // No multiples
        assertEquals(0, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(0));
        assertEquals(0, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(1));
        assertEquals(0, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(2));
        assertEquals(0, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(3));
    }
    
    @Test
    public void test3()
    {
        // 3
        assertEquals(3, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(4));
        assertEquals(3, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(5));
    }
    
    @Test
    public void test8()
    {
        // 3 + 5
        assertEquals(8, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(6));
    }
    
    @Test
    public void test14()
    {
        // 3 + 5 + 6 
        assertEquals(14, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(7));
        assertEquals(14, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(8));
        assertEquals(14, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(9));
    }
    
    @Test
    public void test23()
    {
        // 3 + 5 + 6 + 9
        assertEquals(23, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(10));
    }
    
    @Test
    public void test33()
    {
        // 3 + 5 + 6 + 9 + 10
        assertEquals(33, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(11));
        assertEquals(33, NaturalNumberGenerator.getSumOfMultiplesOf3Or5(12));
    }

    @Test
    public void test1000()
    {
        long sum = NaturalNumberGenerator.getSumOfMultiplesOf3Or5(1000);
        System.out.println("Sum is " + sum);
        assertEquals(233168, sum);
    }
}
