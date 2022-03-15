package dev.kezradley.enigma;

public class Rotor {

  private final int SHIFT = 65;
  int rollover;
  String wiringAlphabetical;
  private int[] wiringNumerical;
  private int[] wiringNumericalReverse;
  private int notch;
  private int position;
  private int offset;

  public Rotor(String rotorName, int initialPosition, int offset) {

    switch (rotorName) {
      case "I":
        wiringAlphabetical = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        this.notch = 'Q' - SHIFT;
        break;
      case "II":
        wiringAlphabetical = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
        this.notch = 'E' - SHIFT;
        break;
      case "III":
        wiringAlphabetical = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
        this.notch = 'V' - SHIFT;
        break;
      case "IV":
        wiringAlphabetical = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
        this.notch = 'J' - SHIFT;
        break;
      default:
        wiringAlphabetical = "VZBRGITYUPSDNHLXAWMJQOFECK";
        this.notch = 'Z' - SHIFT;
        break;
    }
    this.position = initialPosition;
    this.offset = offset;
    this.wiringNumerical = new int[wiringAlphabetical.length()];
    this.wiringNumericalReverse = new int[wiringNumerical.length];
    translateWiring(wiringAlphabetical);
    reverseWiring(this.wiringNumerical);
  }

  private void translateWiring(String alphabetical) {
    for (int i = 0; i < alphabetical.length(); i++) {
      this.wiringNumerical[i] = alphabetical.charAt(i) - SHIFT;
    }
  }

  private void reverseWiring(int[] wiring) {
    int j = wiring.length - 1;
    for (int i = 0; i < wiring.length; i++) {
      this.wiringNumericalReverse[i] = wiring[j];
      j--;
    }
  }

  public void rotate() {
    position = ++position % 25;
  }

  public int substitute(int input) {
    rollover = (input - offset + 25) % 25;
    return wiringNumerical[(rollover + position) % 25];
  }

  public int substituteReverse(int input) {
    rollover = (input - offset + 25) % 25;
    return wiringNumericalReverse[(rollover + position) % 25];
  }

  public boolean isAtNotch() {
    return position == notch;
  }

  // debug stuff
  public int getNotch() {
    return notch;
  }

  public int getPosition() {
    return position;
  }

  public int[] getWiringNumerical() {
    return wiringNumerical;
  }

  public int[] getWiringNumericalReverse() {
    return wiringNumericalReverse;
  }
}
