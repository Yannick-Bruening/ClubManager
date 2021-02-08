package de.toxicpointer.clubmanager.utils.file;

import java.io.File;

public class FileHelpers {
  private FileHelpers() {
  }

  public static File createDir(final File file) {
    file.mkdirs();
    return file;
  }
}
