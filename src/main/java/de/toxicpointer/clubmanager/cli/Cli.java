package de.toxicpointer.clubmanager.cli;

import de.toxicpointer.clubmanager.ClubDataManager;
import de.toxicpointer.clubmanager.club.Club;
import de.toxicpointer.clubmanager.gamepairs.GamePairManager;
import de.toxicpointer.clubmanager.utils.cli.ChosenAction;
import de.toxicpointer.clubmanager.utils.cli.CliHelper;

public class Cli {
  private final ClubDataManager clubDataManager;
  private final GamePairManager gamePairManager;
  private boolean running = true;

  public Cli(final ClubDataManager clubDataManager, final GamePairManager gamePairManager) {
    this.clubDataManager = clubDataManager;
    this.gamePairManager = gamePairManager;
  }

  public void start() {
    showInitBanner();
    startInnerCycle();
  }

  private void startInnerCycle() {
    while (running) {
      final ChosenAction action = showMenuAndAwaitInput();

      switch (action) {
        case LIST_CLUBS -> printList();
        case CREATE_CLUB -> createClub();
        case CREATE_GAME_PAIRS -> generateGamePairs();
        case LIST_GAME_PAIRS -> listGamePairs();
        case NONE -> CliHelper.printMessageAndWait("Ihre auswahl war nicht gültig! Bitte nutzen sie A,T oder B", 2000);
        // This case includes the END input as well because the behaviour of end and error always result in a SysExit
        default -> endCli();
      }
    }
  }

  private void showInitBanner() {
    CliHelper.println("Willkommen zur Vereinsverwaltung!");
    CliHelper.println("Der gewählte Speicherort ist: " + clubDataManager.getClubFile().getParent());
    CliHelper.printBlank();
  }

  private ChosenAction showMenuAndAwaitInput() {
    CliHelper.println("\n");
    CliHelper.println("\t\tMenü");
    CliHelper.printBlank();
    CliHelper.println("A.) Verein Anlegen");
    CliHelper.println("T.) Tabelle Anzeigen");
    CliHelper.println("G.) Spielpaarungen generieren");
    CliHelper.println("S.) Spielpaarungen anzeigen");
    CliHelper.println("B.) Beenden");

    final String input = CliHelper.getConsoleInput("Bitte eingeben: ");
    CliHelper.printBlank();

    if (input.length() == 0) {
      return ChosenAction.NONE;
    }

    return ChosenAction.getByOption(input.charAt(0));
  }

  /*
   * Club related methods
   */

  private void createClub() {
    CliHelper.printBlank();
    CliHelper.println("Verein Anlegen");
    CliHelper.printBlank();

    final String name = CliHelper.getConsoleInput("Name: ");
    final int points = CliHelper.getIntegerFromConsole("Punkte: ", true);
    final int goals = CliHelper.getIntegerFromConsole("Tore: ", true);
    final int conceded = CliHelper.getIntegerFromConsole("Gengentore: ", true);

    final Club c = new Club(name, points, goals, conceded);
    clubDataManager.addClub(c);

    CliHelper.printMessageAndWait("Es wurde folgender Verein angelegt! " + c.toString(), 2000);
  }

  private void printList() {
    CliHelper.printBlank();
    CliHelper.printBlank();
    CliHelper.println("Liste der Vereine: ");

    final String format = "%-20s %-6s %-6s %-1.9s";
    CliHelper.printf(format, "Verein", "Punkte", "Tore", "Gegentore");

    clubDataManager.getClubs().forEach(club -> CliHelper.printf(format, club.getClubName(),
        club.getPoints(), club.getGoals(), club.getConceded()));
  }

  /*
   * Gamepair related methods
   */

  private void generateGamePairs() {
    gamePairManager.generateGamePairs(true);
  }

  private void listGamePairs() {
    final String format = "%-20s %-20s %-8s %-1.8s";
    CliHelper.printf(format, "Heimverein", "Gastverein", "Heimtore", "Gasttore");

    gamePairManager.getGamePairs().forEach(gamePair -> CliHelper.printf(format,
        gamePair.getHomeClub(clubDataManager).getClubName(),
        gamePair.getGuestClub(clubDataManager).getClubName(),
        gamePair.getHomeGoals(),
        gamePair.getGuestGoals()));
  }

  public void endCli() {
    CliHelper.println("Bis zum nächsten Mal");
    running = false;
  }
}
