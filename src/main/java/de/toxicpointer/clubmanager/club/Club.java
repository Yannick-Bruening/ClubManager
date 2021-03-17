package de.toxicpointer.clubmanager.club;

import de.toxicpointer.clubmanager.utils.serialization.TypedSerializer;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Club implements Serializable {
  public static final String EOF = ".tpclub";
  @Serial
  private static final long serialVersionUID = -1041660496205820884L;
  private final UUID clubUuid = UUID.randomUUID();
  private String clubName;
  private int points;
  private int goals;
  private int conceded;

  public Club() {
    this("");
  }

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

  public void setClubName(final String name) {
    clubName = name;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(final int points) {
    this.points = points;
  }

  public void registerWin() {
    setPoints(getPoints() + 3);
  }

  public void registerDraw() {
    setPoints(getPoints() + 1);
  }

  public int getGoals() {
    return goals;
  }

  public void setGoals(final int goals) {
    this.goals = goals;
  }

  public void addGoals(final int goals) {
    setGoals(getGoals() + goals);
  }

  public int getConceded() {
    return conceded;
  }

  public void setConceded(final int conceded) {
    this.conceded = conceded;
  }

  public void addConceded(final int conceded) {
    setConceded(getConceded() + conceded);
  }

  public int getGoalDiff() {
    return goals - conceded;
  }

  public UUID getClubUuid() {
    return clubUuid;
  }

  public void save(final File file) {
    final TypedSerializer<Club> clubTypedSerializer = new TypedSerializer<>();

    clubTypedSerializer.serialize(this, file, true);
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
