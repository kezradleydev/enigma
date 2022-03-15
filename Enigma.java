package dev.kezradley.enigma;

public class Enigma {
  RotorV2 rotorFast;
  RotorV2 rotorMiddle;
  RotorV2 rotorSlow;
  Reflector reflector;
  Plugboard plugboard;

  public Enigma(
      String[] rotorNames,
      int[] rotorPositions,
      int[] ringSettings,
      String reflectorName,
      String plugboardSettings) {
    rotorFast = new RotorV2(rotorNames[0], rotorPositions[0], ringSettings[0]);
    rotorMiddle = new RotorV2(rotorNames[1], rotorPositions[1], ringSettings[1]);
    rotorSlow = new RotorV2(rotorNames[2], rotorPositions[2], ringSettings[2]);
    reflector = new Reflector(reflectorName);
    plugboard = new Plugboard(plugboardSettings);
  }

  char substitute(char input) {
    int inputInt = input - 65;
    if (rotorFast.rotate()) {
      if (rotorMiddle.rotate()) {
        rotorSlow.rotate();
      }
    }
    inputInt = plugboard.substitute(inputInt);

    inputInt = rotorFast.forward(inputInt);
    inputInt = rotorMiddle.forward(inputInt);
    inputInt = rotorSlow.forward(inputInt);

    inputInt = reflector.substitute(inputInt);

    inputInt = rotorSlow.backward(inputInt);
    inputInt = rotorMiddle.backward(inputInt);
    inputInt = rotorFast.backward(inputInt);

    inputInt = plugboard.substitute(inputInt);

    return (char) (inputInt + 65);
  }

  String substitute(String input) {
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      output.append(substitute(input.charAt(i)));
    }
    return output.toString();
  }
}
