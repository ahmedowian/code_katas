package ahmedowian.code_katas.natural_numbers;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
 * The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * @author Ahmed Owian
 */
public class NaturalNumberGenerator
{
    public static long getSumOfMultiplesOf3Or5(int max) 
    {
        long sum = 0;
        for (int i = 3; i < max; i++)
        {
            if (i % 3 == 0 || i % 5 == 0)
            {
                sum += i;
            }
        }
        
        return sum;
    }
}
