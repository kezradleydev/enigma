package dev.kezradley.enigma;


public class Plugboard {
  int[] numericalEndcoding;

  public Plugboard(String plugboardSettings) {
    plugboardSettings = plugboardSettings.toUpperCase();
    numericalEndcoding = new int[26];
    for (int i = 0; i < numericalEndcoding.length; i++) {
      numericalEndcoding[i] = i;
    }
    for (int i = 0; i < plugboardSettings.length(); i++) {
      numericalEndcoding[plugboardSettings.charAt(i) - 65] = plugboardSettings.charAt(i + 1) - 65;
      numericalEndcoding[plugboardSettings.charAt(i + 1) - 65] = plugboardSettings.charAt(i) - 65;
      i++;
    }
  }
  public int substitute(int input){
    return numericalEndcoding[input];
  }
}
