package by.test.additional;

/**
 * not used right now, will be in use if one letter can be repeated, so the string "aaa" for "abc" will be needed
 */
@Deprecated
public class PereborStringCanUseSeveralTimes {

  private static String inputStr = "ABCDEF";
  private static int finalValue = inputStr.length() - 1;

  public static void main(String[] args) {
      ordinaryProcedure();
  }

  private static void ordinaryProcedure() {
    int length = inputStr.length();
    int[] perebor = new int[length];
    init(perebor);
    while (!finished(perebor)) {
      increment(perebor);
      String result = "";
      for (int aPerebor : perebor) {
        result += inputStr.charAt(aPerebor);
      }
      System.out.println(result);
    }
  }

  private static void init(int[] perebor) {
    perebor[perebor.length - 1] = -1;
  }

  private static void increment(int[] perebor) {
    for (int i = perebor.length - 1; i >= 0; i--) {
      if (perebor[i] < finalValue) {
        perebor[i]++;
        printArray(perebor);
        return;
      } else {
        perebor[i] = 0;
        continue;
      }
    }
  }

  private static boolean finished(int[] perebor) {
    for (int aPerebor : perebor) {
      if (aPerebor < finalValue) {
        return false;
      }
    }
    return true;
  }

  private static boolean printArray(int[] perebor) {
    for (int aPerebor : perebor) {
      System.out.print(aPerebor);
    }
    System.out.println("");
    return true;
  }
}
