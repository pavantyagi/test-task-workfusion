package by.test;

/**
 * Created by Vasilina on 31.03.2015.
 */
public class Coins {
  public static void main(String[] args) {
    int[] coins = {25, 10, 5, 1};
    int arr[] = new int[coins.length];
    int number = 23;
    int i = 0 ;
    int diff = number;
    while (i <= coins.length -1) {
      if (diff > coins[i]) {
        arr[i] = diff / coins[i];
        diff -= arr[i] * coins[i];
      }
      i++;
    }
    printArray(arr, coins);
    while (arr[coins.length -1 ] < number) {
      descrement(arr, coins);
    }
    //i = coins.length -2 ;
    System.out.println("all");
  }

  private static void descrement(int[] perebor, int[] coins) {
    for (int i = perebor.length -2; i >= 0; i--) {
      if (perebor[i] > 0) {
        perebor[i+1]+=(coins[i])/coins[i+1];
        perebor[i]--;
        printArray(perebor, coins);
        return;
      } else {
        perebor[i] = 0;
        continue;
      }
    }
  }


  private static boolean printArray(int[] perebor, int[] coins) {
    for (int aPerebor : perebor) {
      System.out.print(aPerebor + ",");
    }
    System.out.println("");
    for (int i=0; i<coins.length; i++) {
      if (perebor[i] > 0) {
        System.out.print(perebor[i] + " монеты " + coins[i] + ",");
      }
    }
    System.out.println("");

    return true;
  }

}
