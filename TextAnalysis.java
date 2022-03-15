package dev.kezradley.bomba;

public class TextAnalysis {
  private final int[] charOccurences;
  private String text;
  private double indexOfCoincidence;

  public double getIndexOfCoincidence() {
    if (indexOfCoincidence > 0) {
      return indexOfCoincidence;
    }
    else return indexOfCoincidence();
  }

  public TextAnalysis(String text) {
    charOccurences = new int[26];
    this.text = cleanString(text);
    indexOfCoincidence();
    countCharOccurences();
  }

  public void setText(String text) {
    this.text = cleanString(text);
  }

  private void countCharOccurences() {
    for (int i = 0; i < text.length(); i++) {
      charOccurences[(text.charAt(i) - 65)]++;
    }
  }

  private String cleanString(String text) {
    text = text.toUpperCase();
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) >= 65 && text.charAt(i) <= 90) {
        stringBuilder.append(text.charAt(i));
      }
    }
    return stringBuilder.toString();
  }

  private double indexOfCoincidence() {
    double sum = 0.0D;
    for (int i : charOccurences) {
      sum += i * (i - 1);
    }
    return indexOfCoincidence = sum / (text.length() * (text.length() - 1));
  }
}
