package de.toxicpointer.clubmanager;

import de.toxicpointer.clubmanager.cli.Cli;
import de.toxicpointer.clubmanager.club.Club;
import de.toxicpointer.clubmanager.gamepairs.GamePair;
import de.toxicpointer.clubmanager.gamepairs.GamePairManager;
import de.toxicpointer.clubmanager.gui.ClubUI;
import de.toxicpointer.clubmanager.utils.file.FileHelpers;
import javafx.application.Application;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class ClubManager {
  private static final String DESKTOP_PATH = System.getProperty("user.home") + "/Desktop";
  private static final File DATA_DIRECTORY = FileHelpers.createDir(new File(DESKTOP_PATH, "schule/awe/clubmanager"));
  private static final File CLUB_DIR = FileHelpers.createDir(new File(DATA_DIRECTORY, "clubs"));

  public static final AtomicReference<ClubDataManager> clubDataManager = new AtomicReference<>();
  public static final AtomicReference<GamePairManager> gamePairManager = new AtomicReference<>();

  public static void main(final String[] args) {
    final File clubsFile = new File(CLUB_DIR, "clubs" + Club.EOF);
    final File gamePairFile = new File(CLUB_DIR, "gamepairs" + GamePair.EOF);

    clubDataManager.set(new ClubDataManager(clubsFile, gamePairFile));
    gamePairManager.set(new GamePairManager(clubDataManager.get()));

    if (Arrays.stream(args).anyMatch(s -> s.equalsIgnoreCase("--cli"))) {
      final Cli cli = new Cli(clubDataManager.get(), gamePairManager.get());
      cli.start();
    } else {
      Application.launch(ClubUI.class, args);
    }
  }
}
