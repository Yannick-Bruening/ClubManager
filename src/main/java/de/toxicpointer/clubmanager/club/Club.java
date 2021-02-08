package de.toxicpointer.clubmanager.club;

import de.toxicpointer.clubmanager.utils.serialization.TypedDeserializer;
import de.toxicpointer.clubmanager.utils.serialization.TypedSerializer;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Club implements Serializable {
  private static final String EOF = ".tpclub";
  @Serial
  private static final long serialVersionUID = -1041660496205820884L;
  String clubName;
  int points;
  int goals;
  int conceded;

  public Club(final String clubName) {
    this(clubName, 0);
  }

  public Club(final String clubName, final int points) {
    this(clubName, points, 0);
  }

  public Club(final String clubName, final int points, final int goals) {
    this(clubName, points, goals, 0);
  }

  public Club(final String clubName, final int points, final int goals, final int conceded) {
    this.clubName = clubName;
    this.points = points;
    this.goals = goals;
    this.conceded = conceded;
  }

  public String getClubName() {
    return clubName;
  }

  public void setClubName(final String clubName) {
    this.clubName = clubName;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(final int points) {
    this.points = points;
  }

  public int getGoals() {
    return goals;
  }

  public void setGoals(final int goals) {
    this.goals = goals;
  }

  public int getConceded() {
    return conceded;
  }

  public void setConceded(final int conceded) {
    this.conceded = conceded;
  }

  public void save(final File file) {
    final TypedSerializer<Club> clubTypedSerializer = new TypedSerializer<>();

    clubTypedSerializer.serialize(this, file, true);
  }

  /*
   * Static Methods
   */

  public static Club loadClubFromFile(final File file) {
    if (!file.exists()) {
      return null;
    }

    if (file.isDirectory()) {
      throw new IllegalArgumentException("The File must not be a directory");
    }

    if (!file.getName().toLowerCase().endsWith(EOF)) {
      throw new IllegalArgumentException("The file must be a .tpClub file");
    }

    try {
      final TypedDeserializer<Club> clubTypedDeserializer = new TypedDeserializer<>();
      return clubTypedDeserializer.deserialize(file);
    } catch (final Throwable e) {
      System.err.println("A CRITICAL DESERIALIZATION ERROR OCCURRED");
      System.exit(1);
      return null;
    }
  }

  public static List<Club> loadClubsFromDir(final File dir) {
    if (!dir.exists()) {
      return new ArrayList<>();
    }


    if (!dir.isDirectory()) {
      throw new IllegalArgumentException("The file must be a directory");
    }

    final List<Club> clubs = new ArrayList<>();

    if (dir.listFiles() != null && dir.listFiles().length > 0) {
      for (final File file : Objects.requireNonNull(
          dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(EOF)))) {
        clubs.add(loadClubFromFile(file));
      }
    }

    return clubs;
  }

  public static List<Club> loadClubsFromFile(final File file) {
    if (!file.exists()) {
      return new ArrayList<>();
    }

    if (file.isDirectory()) {
      throw new IllegalArgumentException("The File must not be a directory");
    }

    if (!file.getName().toLowerCase().endsWith(EOF)) {
      throw new IllegalArgumentException("The file must be a .tpClub file");
    }

    try {
      final TypedDeserializer<Club> clubTypedDeserializer = new TypedDeserializer<>();
      final Object[] clubs = clubTypedDeserializer.deserializeMany(file);

      System.out.println(clubs.length);
      Arrays.stream(clubs).forEach(System.out::println);

      return new ArrayList<>();
    } catch (final Exception e) {
      e.printStackTrace();
      System.err.println("A CRITICAL DESERIALIZATION ERROR OCCURRED");
      System.exit(1);
      return new ArrayList<>();
    }
  }

  /*
   * Override Methods
   */

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Club club = (Club) o;
    return getPoints() == club.getPoints()
        && getGoals() == club.getGoals()
        && getConceded() == club.getConceded()
        && getClubName().equals(club.getClubName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getClubName(), getPoints(), getGoals(), getConceded());
  }

  @Override
  public String toString() {
    return "Club{"
        + "clubName='" + clubName + '\''
        + ", points=" + points
        + ", goals=" + goals
        + ", conceded=" + conceded
        + '}';
  }
}
