/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets)
  {
	  //base case 1:
      //if floors = 0 then no drops are required OR floors = 1 then 1 drop is required
      if(floors==0 || floors==1)
          return floors;

      //base case 2:
      //if only one sheets is there then drops = floors
      if(sheets==1)
          return floors;
      
      int minimumDrops=Integer.MAX_VALUE, tempResult;
      //check dropping from all the floors 1 to floors and pick the minimum among those.
      //for every drop there are 2 scenarios - a) either sheets will break b) sheets will not break
      for (int i = 1; i <=floors ; i++) {
          //for the worst case pick the maximum among a) and b)
          tempResult = Math.max(glassFallingRecur(i-1,sheets-1), glassFallingRecur(floors-i,sheets));
          minimumDrops = Math.min(tempResult,minimumDrops);
      }
      return minimumDrops + 1;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int floors, int sheets) {
	  int memoTable[][]=new int [floors+1][sheets+1];
	  for (int i=0;i<=floors;i++) 
		  for (int j=1;j<=sheets;j++)
			  memoTable[i][j]=0; 
	  return glassFallingMemoizedHelper(memoTable,floors,sheets);
  }
  
  private int glassFallingMemoizedHelper(int memo[][],int floors, int sheets)
  {
	  
	  if (memo[floors][sheets]!=0) 
		  return memo[floors][sheets];

	//if floors = 0 then no drops are required OR floors = 1 then 1 drop is required
      if (floors==0||floors==1) 
		  return memo[floors][sheets]=floors;  

      	  
      //if only one sheets is there then drops = floors
	  if (sheets==1) 
		  return memo[floors][1]=floors;  

	  
	  //check dropping from all the floors 1 to floors and pick the minimum among those.
      //for every drop there are 2 scenarios - a) either sheets will break b) sheets will not break
	  int tempResult;
	  int minimumDrops=Integer.MAX_VALUE;
	  for (int i=1;i<=floors;i++) {
			  tempResult=Math.max(glassFallingMemoizedHelper(memo,i-1,sheets-1),glassFallingMemoizedHelper(memo,floors-i,sheets));
			  minimumDrops = Math.min(tempResult,minimumDrops);
			  }
	  return memo[floors][sheets]=minimumDrops + 1;	
}
  
  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
	  int [][] sheetDrops = new int [sheets+1][floors+1];
      //base case 1:
      //if floors = 0 then no drops are required // OR floors = 1 then 1 drop is required
      for (int i = 1; i <=sheets ; i++) {
          sheetDrops[i][0] = 0;
          sheetDrops[i][1] = 1;
      }
      //base case 2:
      //if only one sheet is there then drops = floors
      for (int i = 1; i <=floors ; i++) {
          sheetDrops[1][i] = i;
      }

      for (int i = 2; i <=sheets ; i++) {
          for (int j = 2; j <=floors ; j++) {
              sheetDrops[i][j] = Integer.MAX_VALUE;
              int tempResult;
              for (int k = 1; k <=j ; k++) {
                  tempResult = 1 + Math.max(sheetDrops[i-1][k-1], sheetDrops[i][j-k]);
                  sheetDrops[i][j] = Math.min(tempResult,sheetDrops[i][j]);
              }
          }
      }
      // sheetDrops[sheets][floors] will have the result : minimum number of drops required in worst case
      return sheetDrops[sheets][floors];
  }


  public static void main(String args[])
  {
      GlassFalling gf = new GlassFalling();
      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
  
}
