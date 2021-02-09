package de.toxicpointer.clubmanager.utils.cli;

import java.util.Scanner;

public class CliHelper {
  private CliHelper() {
  }

  public static String getConsoleInput(final String prefix) {
    System.out.print(prefix);
    return new Scanner(System.in).nextLine();
  }

  public static Integer getIntegerFromConsole(final String prefix) {
    final String s = getConsoleInput(prefix);

    try {
      return Integer.parseInt(s);
    } catch (final Exception e) {
      System.err.println("Ungültige Eingabe! Es muss eine Zahl angegeben werden!");
      return getIntegerFromConsole(prefix);
    }
  }

  public static Integer getIntegerFromConsole(final String prefix, final boolean mustBePositive) {
    final Integer i = getIntegerFromConsole(prefix);

    if (i < 0) {
      System.err.println("Die Zahl muss positiv oder 0 sein.");
      return getIntegerFromConsole(prefix, mustBePositive);
    }
    return i;
  }

  public static void printBlank() {
    System.out.println();
  }

  public static void println(final String message) {
    System.out.println(message);
  }

  public static void printf(final String format, final Object... args) {
    System.out.printf(format + "\n", args);
  }

  public static void printMessageAndWait(final String message, final long duration) {
    println(message);

    try {
      Thread.sleep(duration);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }
}
