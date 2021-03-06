package de.toxicpointer.clubmanager.utils.cli;

import java.util.Arrays;

public enum ChosenAction {
  CREATE_CLUB('A'),
  LIST_CLUBS('T'),
  CREATE_GAME_PAIRS('G'),
  LIST_GAME_PAIRS('S'),
  GAME_RESULT_REGISTER('E'),
  END('B'),
  NONE(Character.MIN_VALUE);

  final char option;

  ChosenAction(final char option) {
    this.option = option;
  }

  public static ChosenAction getByOption(final char option) {
    final String s = new String(new char[]{option});

    return Arrays.stream(ChosenAction.values())
        .filter(chosenAction -> chosenAction.option == s.toUpperCase().charAt(0))
        .findFirst()
        .orElse(NONE);
  }
}
