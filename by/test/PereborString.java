package by.test;

/**
 * todo: Nice: will be unit test and api functions
 */
public class PereborString {

  private static String inputStr = "ABCDEF";
  static int currentIndex = 0;
  static int currentIndex2 = 0;

  public static void main(String[] args) {
      uniqueProcedure();
  }

  private static void uniqueProcedure() {
    int length = inputStr.length();
    int[] perebor = new int[length];
    uniqueInit(perebor);
    while (!uniqueFinished(perebor)) {
      boolean madeIncrement = uniqueIncrement(perebor);
      if (madeIncrement) {
        String result = "";
        for (int aPerebor : perebor) {
          result += inputStr.charAt(aPerebor);
        }
        System.out.println(result);
      }
    }
  }

  private static boolean uniqueIncrement(int[] perebor) {
    boolean madeExchange = false;
    if (currentIndex <= perebor.length - 2) {
      exchange(perebor);
      madeExchange = true;
    }
    if (currentIndex <= perebor.length - 2) {
      currentIndex += 1;
    } else {
      if (currentIndex2 <= perebor.length - 1) {
        currentIndex2++;
        currentIndex = 0;
      }
    }
    return madeExchange;
  }

  private static void exchange(int[] perebor) {
    int currentValue = perebor[currentIndex];
    perebor[currentIndex] = perebor[currentIndex + 1];
    perebor[currentIndex + 1] = currentValue;
  }

  private static void uniqueInit(int[] perebor) {
    int maxValue = inputStr.length() - 1;
    for (int i = 0; i < perebor.length; i++) {
      perebor[i] = maxValue;
      maxValue--;
    }
  }

  private static boolean uniqueFinished(int[] perebor) {
    return currentIndex2 == perebor.length - 1 && currentIndex == perebor.length - 1;
  }

  private static boolean printArray(int[] perebor) {
    for (int aPerebor : perebor) {
      System.out.print(aPerebor);
    }
    System.out.println("");
    return true;
  }
}
