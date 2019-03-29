public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices)
  {
	  if (rodLength <= 0) 
          return 0; 
      int max_val = Integer.MIN_VALUE; 

      for (int i = 0; i<rodLength; i++) 
          max_val = Math.max(max_val, 
                            lengthPrices[i] + rodCuttingRecur(rodLength-i-1, lengthPrices )); 

      return max_val; 
  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
      int val[] = new int[rodLength+1]; 
      val[0] = 0; 

      for (int i = 1; i<=rodLength; i++) 
      { 
          int max_val = Integer.MIN_VALUE; 
          for (int j = 0; j < i; j++) 
              max_val = Math.max(max_val,  
                                 lengthPrices[j] + val[i-j-1]); 
          val[i] = max_val; 
      } 

      return val[rodLength];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}