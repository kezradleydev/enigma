package dev.kezradley.enigma;

public class Main {

  public static void main(String[] args) {

    String[] rotorNames = new String[] {"II", "I", "III"};
    int[] rotorPositions = new int[] {1, 17, 12};
    int[] ringSettings = new int[] {1, 1, 1};
    String reflector = "UKW-B";
    String plugboardSettings = "bqcrdiejkwmtospxuzgh";
    Enigma enigma =
        new Enigma(rotorNames, rotorPositions, ringSettings, reflector, plugboardSettings);
    System.out.println(enigma.substitute("PEIVKBJYPYHSGI"));
  }
}
