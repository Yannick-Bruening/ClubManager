package de.toxicpointer.clubmanager;

import de.toxicpointer.clubmanager.club.Club;
import de.toxicpointer.clubmanager.utils.cli.ChosenAction;

import java.io.File;

import static de.toxicpointer.clubmanager.utils.cli.CliHelper.getConsoleInput;
import static de.toxicpointer.clubmanager.utils.cli.CliHelper.getIntegerFromConsole;
import static de.toxicpointer.clubmanager.utils.cli.CliHelper.printBlank;
import static de.toxicpointer.clubmanager.utils.cli.CliHelper.printMessageAndWait;
import static de.toxicpointer.clubmanager.utils.cli.CliHelper.println;

public class Cli {
  final File file;
  boolean running = true;

  public Cli(final File file) {
    this.file = file;
  }

  public void start() {
    showInitBanner();
    startInnerCycle();
  }

  private void startInnerCycle() {
    while (running) {
      final ChosenAction action = showMenuAndAwaitInput();

      switch (action) {
        case LIST -> printList();
        case CREATE -> createClub();
        case NONE -> printMessageAndWait("Ihre auswahl war nicht gültig! Bitte nutzen sie A,T oder B", 2000);
        // This case includes the END input as well because the behaviour of end and error always result in a SysExit
        default -> endCli();
      }
    }
  }

  private void showInitBanner() {
    println("Willkommen zur Vereinsverwaltung!");
    println("Der gewählte Speicherort ist: " + file.getAbsolutePath());
    printBlank();
  }

  private ChosenAction showMenuAndAwaitInput() {
    println("\n\n\n\n\n\n");
    println("\t\tMenü");
    printBlank();
    println("A.) Verein Anlegen");
    println("T.) Tabelle Anzeigen");
    println("B.) Beenden");

    final String input = getConsoleInput("Bitte eingeben: ");
    printBlank();

    return ChosenAction.getByOption(input.charAt(0));
  }

  private void printList() {
    printBlank();
    printBlank();
    println("Liste der Vereine: ");
    Club.loadClubsFromFile(new File(file, "clubs.tpclub")).forEach(club -> println(club.toString()));
  }

  private void createClub() {
    printBlank();
    println("Verein Anlegen");
    printBlank();

    final String name = getConsoleInput("Name: ");
    final int points = getIntegerFromConsole("Punkte: ", true);
    final int goals = getIntegerFromConsole("Tore: ", true);
    final int conceded = getIntegerFromConsole("Gengentore: ", true);

    final Club c = new Club(name, points, goals, conceded);
    c.save(new File(file, "clubs.tpclub"));

    printMessageAndWait("Es wurde folgender Verein angelegt! " + c.toString(), 2000);
  }

  public void endCli() {
    println("Bis zum nächsten Mal");
    running = false;
  }
}
