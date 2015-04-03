package by.test;

/**
 * todo: Nice: will be unit test and api functions
 * Created by Vasilina on 31.03.2015.
 */
public class Coins {
  public static void main(String[] args) {
    int[] coins = {25, 10, 5, 1};
    int arr[] = new int[coins.length];
    int number = 100;
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
    int countOfPerestanovok = 0;
    while (arr[coins.length -1 ] < number) {
      descrement(arr, coins);
      countOfPerestanovok++;
    }
    System.out.println("количество вариантов " + (countOfPerestanovok+1));
    //i = coins.length -2 ;
    System.out.println("all");
  }

  private static void descrement(int[] perebor, int[] coins) {
    for (int i = perebor.length -2; i >= 0; i--) {
      if (perebor[i] > 0) {
        int inc = (coins[i]) / coins[i + 1];
        perebor[i+1]+= inc;
        int value = inc*coins[i+1];
        if (value < coins[i]) {
          perebor[i+2]+=(coins[i]-value)/coins[i+2];
        }

        perebor[i]--;
        if (i<=perebor.length-3) {
          //return all 5 from 1, 10 from 5 again
          for (int j = perebor.length -2; j>i; j--) {
            int deleted = (perebor[j + 1]*coins[j+1]) / coins[j];
            perebor[j]+= deleted;
            int count = deleted * coins[j];
            perebor[j+1]-= count/coins[j+1];
          }
        }
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
