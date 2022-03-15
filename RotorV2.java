package dev.kezradley.enigma;

public class RotorV2 {
  private int[] encodingNumerical;
  private int[] encodingNumericalBack;
  private int rollover;
  private int rotorPosition;
  private int ringSetting;
  private int notchPosition;

  /**
   * Constructor initializes all members of <code>RotorV2</code>. All <code>int</code> values may be
   * seen as representing 26 letter alphabet, including array indices.
   */
  public RotorV2(String rotorType, int rotorPosition, int ringSetting) {
    this.rotorPosition = rotorPosition;
    this.ringSetting = ringSetting;
    switch (rotorType) {
      case ("I"):
        this.encodingNumerical =
            new int[] {
              4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1,
              17, 2, 9
            };
        this.notchPosition = 16;
        break;
      case ("II"):
        this.encodingNumerical =
            new int[] {
              0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21,
              14, 4
            };
        this.notchPosition = 4;
        break;
      case ("III"):
        this.encodingNumerical =
            new int[] {
              1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18,
              16, 14
            };
        this.notchPosition = 21;
        break;
      case ("IV"):
        this.encodingNumerical =
            new int[] {
              4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12,
              22, 1
            };
        this.notchPosition = 9;
        break;
      default:
        this.encodingNumerical =
            new int[] {
              21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4,
              2, 10
            };
        this.notchPosition = 25;
        break;
    }
    inverseEncoding(this.encodingNumerical);
  }

  /**
   * Fills the inverse Represetation of the substitution Map <code>encodingNumerical</code>.
   *
   * @param encodingNumerical int array representing substitution map
   */
  private void inverseEncoding(int[] encodingNumerical) {
    this.encodingNumericalBack = new int[encodingNumerical.length];
    for (int i = 0; i < encodingNumerical.length; i++) {
      this.encodingNumericalBack[encodingNumerical[i]] = i;
    }
  }

  /**
   * Simple function to increment <code>rotorPosition</code> while checking if <code>rotorPosition
   * </code> is equal to <code>notchPosition</code>. Should allow for cleaner implementation, should
   * be changed if duality decreases readabilty.
   *
   * @return <code>rotorPosition == notchPosition</code>
   */
  public boolean rotate() {
    rotorPosition = (rotorPosition+1) % 25;
    return rotorPosition == notchPosition;
  }

  /**
   * @param input integer representation of alphabet from 0 to 25
   * @return Corresponding value in substitutionmap after taking <code>ringSetting</code> and <code>
   *     rotorPosition</code> into account.
   */
  public int substitute(int input, int[] encoding) {
    rollover = rotorPosition - ringSetting;
    return (encoding[(input + rollover + 26) % 26] - rollover + 26) % 26;
  }

  public int forward(int input) {
    return substitute(input, this.encodingNumerical);
  }

  public int backward(int input) {
    return substitute(input, this.encodingNumericalBack);
  }
}
