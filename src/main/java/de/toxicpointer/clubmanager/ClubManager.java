package de.toxicpointer.clubmanager;

import de.toxicpointer.clubmanager.cli.Cli;
import de.toxicpointer.clubmanager.club.Club;
import de.toxicpointer.clubmanager.gamepairs.GamePair;
import de.toxicpointer.clubmanager.gamepairs.GamePairManager;
import de.toxicpointer.clubmanager.utils.file.FileHelpers;

import java.io.File;

public class ClubManager {
  private static final String DESKTOP_PATH = System.getProperty("user.home") + "/Desktop";
  private static final File DATA_DIRECTORY = FileHelpers.createDir(new File(DESKTOP_PATH, "schule/awe/clubmanager"));
  private static final File CLUB_DIR = FileHelpers.createDir(new File(DATA_DIRECTORY, "clubs"));

  public static void main(final String[] args) {
    final File clubsFile = new File(CLUB_DIR, "clubs" + Club.EOF);
    final File gamePairFile = new File(CLUB_DIR, "gamepairs" + GamePair.EOF);

    final ClubDataManager clubDataManager = new ClubDataManager(clubsFile, gamePairFile);
    final GamePairManager gamePairManager = new GamePairManager(clubDataManager);

    final Cli cli = new Cli(clubDataManager, gamePairManager);

    cli.start();
  }

}
