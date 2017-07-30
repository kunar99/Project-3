public class Sequence{

    static int efficiencyVal = 0;

    //Method for calculating an Iterative
    public static int computeIterative(int n){

        int value = 0;

        if(n ==0)
            return 0;

        else{
            for (int i = 1; i <= n; i++){
                value = i * n;

                efficiencyVal++; //Counter for efficiency
            }
        }
        //Returns the value for iterative
        return value;

    }
    //Method for calculating a Recursive
    public static int computeRecursive(int n){

      return recursive(n);

    }

    private static int recursive(int n){

        efficiencyVal++; //Counter for efficiency

        if (n == 0)
            return 0;

        else if (n == 1)
            return 1;

        else{

            //Returns the value for recursive
            return n + recursive(n - 1);
        }
    }

    //Creating Efficiency method to be called in Project3 Class
    public static int getEfficiency(){

        //Returns the value for efficiency
        return efficiencyVal;
    }

}