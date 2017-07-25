/**
 * Created by johnclayton on 7/24/17.
 */
public class Sequence

{

    static int efficencyValue;
    // compute iteration of n
    public static int computeIterative(int n)

    {

        efficencyValue=0;

        int value[] = new int[n + 2];

        value[0] = 0;

        value[1] = 1;

        for (int i = 2; i <= n; i++)

        {

            efficencyValue++;

            value[i] = 2 * value[i - 1] + value[i - 2];

        }
        // returns the value of n
        return value[n];

    }
    // computes the recursion of n
    public static long computeRecursive(long n)

    {

        efficencyValue=0;

        return recursive(n);

    }

    private static long recursive(long n) {

        efficencyValue++;

        if (n <= 1)

            return 1;
        else
            return n * recursive(n - 1);

//        if (n == 0)
//
//            return 0;
//
//        else if (n == 1)
//
//            return 1;
//
//        else
//
//        {
//            return 2 * recursive(n - 1) + recursive(n - 2);
//
//        }

    }

    public static int getEfficiency()

    {

        return efficencyValue;

    }

}

