public class Sequence

{

    static int efficencyValue;
    // compute iteration of n
    public static int computeIterative(int n){

        efficencyValue=0;

        int value = 0;

        if(n ==0)
            return 0;

        else{
            for (int i = 1; i <= n; i++){
                value = i * n;

                efficencyValue++;
            }
        }
        // returns the value
        return value;

    }
    // computes the recursion of n
    public static int computeRecursive(int n)

    {

        efficencyValue=0;

        return recursive(n);

    }

    private static int recursive(int n){

        efficencyValue++;

        if (n == 0)
            return 0;

        else if (n == 1)
            return 1;

        else{

            return n + recursive(n - 1);

        }
    }

    public static int getEfficiency(){

        return efficencyValue;
    }

}