package de.toxicpointer.clubmanager;

import de.toxicpointer.clubmanager.utils.file.FileHelpers;

import java.io.File;

public class ClubManager {
  private static final String DESKTOP_PATH = System.getProperty("user.home") + "/Desktop";
  private static final File DATA_DIRECTORY = FileHelpers.createDir(new File(DESKTOP_PATH, "schule/awe/clubmanager"));
  private static final File CLUB_DIR = FileHelpers.createDir(new File(DATA_DIRECTORY, "clubs"));

  public static void main(final String[] args) {
    final Cli cli = new Cli(CLUB_DIR);
    cli.start();
  }

}
