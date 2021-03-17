package de.toxicpointer.clubmanager.utils.cli;

import de.toxicpointer.clubmanager.ClubDataManager;
import de.toxicpointer.clubmanager.club.Club;

import java.util.Scanner;

public class CliHelper {
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
    final String formatEnd = format + "\n";
    System.out.printf(formatEnd, args);
  }

  public static void printHeader(final String text) {
    printBlank();
    println(text);
    printBlank();
  }

  public static void printWaitingMessage(final String message, final long duration) {
    println(message);

    try {
      Thread.sleep(duration);
    } catch (final InterruptedException e) {
      System.err.println("Could not sleep Thread");
      Thread.currentThread().interrupt();
    }
  }

  public static Club getClubFromConsoleByString(final String prefix, final ClubDataManager clubDataManager) {
    Club club = null;
    String clubName;

    while (club == null) {
      clubName = getConsoleInput(prefix);
      club = clubDataManager.findClub(clubName);

      if (club == null) {
        printf("Der club '%s' wurde nicht gefunden!", clubName);
      }
    }
    return club;
  }
}
