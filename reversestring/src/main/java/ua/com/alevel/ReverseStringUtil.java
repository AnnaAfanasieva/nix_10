package ua.com.alevel;

public final class ReverseStringUtil {

    private ReverseStringUtil() {
    }

    public static String reverse(String string) {
        char[] arrFromString = string.toCharArray();
        char[] arrayFinalString = new char[arrFromString.length];
        String bufferOneWord = "";
        char[] bufferArrayOneWord;
        int indexInArrayFinalString = 0;

        for (int i = 0; i < arrFromString.length; i++) {
            if (arrFromString[i] != ' ') {
                bufferOneWord = bufferOneWord + arrFromString[i];
            } else {
                bufferArrayOneWord = bufferOneWord.toCharArray();
                for (int j = bufferArrayOneWord.length - 1; j >= 0; j--) {
                    arrayFinalString[indexInArrayFinalString] = bufferArrayOneWord[j];
                    indexInArrayFinalString++;
                }

                if (i != arrFromString.length - 1 || (i == arrFromString.length - 1 && arrFromString[i] == ' ')) {
                    arrayFinalString[indexInArrayFinalString] = ' ';
                    indexInArrayFinalString++;
                    bufferOneWord = "";
                }
            }

            if (i == arrFromString.length - 1 && !bufferOneWord.equals("")) {
                bufferArrayOneWord = bufferOneWord.toCharArray();
                for (int j = bufferArrayOneWord.length - 1; j >= 0; j--) {
                    arrayFinalString[indexInArrayFinalString] = bufferArrayOneWord[j];
                    indexInArrayFinalString++;
                }
            }
        }
        return charArrayToString(arrayFinalString);
    }

    public static String reverse(String string, String substring) {
        char[] arrFromString = string.toCharArray();
        char[] arrFromSubstring = substring.toCharArray();
        int indexOfArrFromString;
        boolean stringContainSubstring = true;
        String reverseSubstring = reverse(substring);
        String finalString = "";

        for (int i = 0; i < arrFromString.length; i++) {
            if (arrFromString[i] == arrFromSubstring[0]) {
                indexOfArrFromString = i;
                for (int j = 0; j < arrFromSubstring.length; j++) {
                    if (j < arrFromString.length && indexOfArrFromString < arrFromString.length && arrFromString[indexOfArrFromString] != arrFromSubstring[j]) {
                        stringContainSubstring = false;
                    }
                    indexOfArrFromString++;
                }

                if (stringContainSubstring) {
                    for (int j = 0; j < i; j++) {
                        finalString += arrFromString[j];
                    }
                    finalString += reverseSubstring;
                    for (int j = i + arrFromSubstring.length; j < arrFromString.length; j++) {
                        finalString += arrFromString[j];
                    }
                    i = arrFromString.length - 1;
                } else {
                    stringContainSubstring = true;
                }
            }
        }

        if (finalString.equals("")) {
            System.out.println("Подстрока в строке не найдена");
            finalString = string;
        }
        return finalString;
    }

    public static String reverse(String string, String startSymbol, String endSymbol) {
        char[] arrFromString = string.toCharArray();
        char[] arrFromStartSymbol = startSymbol.toCharArray();
        char[] arrFromEndSymbol = endSymbol.toCharArray();
        String stringToReverse = "";
        int indexOfArrFromString;
        boolean stringContainStartSymbol = true;
        boolean stringContainEndSymbol = true;
        String finalString = "";
        int indexStartReverseSubstring = arrFromString.length;
        int indexEndReverseSubstring = arrFromString.length;

        for (int i = 0; i < arrFromString.length; i++) {
            if (arrFromString[i] == arrFromStartSymbol[0]) {
                indexOfArrFromString = i;
                for (int j = 0; j < arrFromStartSymbol.length; j++) {
                    if (j < arrFromString.length && indexOfArrFromString < arrFromString.length && arrFromString[indexOfArrFromString] != arrFromStartSymbol[j]) {
                        stringContainStartSymbol = false;
                    }
                    indexOfArrFromString++;
                }

                if (stringContainStartSymbol) {
                    stringToReverse += startSymbol;
                    indexStartReverseSubstring = i;
                    for (int j = indexOfArrFromString; j < arrFromString.length; j++) {
                        if (arrFromString[j] == arrFromEndSymbol[0]) {
                            indexOfArrFromString = j;
                            for (int k = 0; k < arrFromEndSymbol.length; k++) {
                                if (arrFromString[indexOfArrFromString] != arrFromEndSymbol[k]) {
                                    stringContainEndSymbol = false;
                                }
                                indexOfArrFromString++;
                            }

                            if (stringContainEndSymbol) {
                                stringToReverse += endSymbol;
                                indexEndReverseSubstring = indexOfArrFromString;
                                j = arrFromString.length - 1;
                                i = j;
                            } else {
                                stringContainEndSymbol = true;
                                stringToReverse += arrFromString[j];
                            }
                        } else {
                            stringToReverse += arrFromString[j];
                        }

                    }
                } else {
                    stringContainStartSymbol = true;
                }
            }
        }

        if (stringToReverse.equals("")) {
            System.out.println("");
            finalString = string;
        } else {
            for (int i = 0; i < indexStartReverseSubstring; i++) {
                finalString += arrFromString[i];
            }
            finalString += reverse(stringToReverse);
            for (int i = indexEndReverseSubstring; i < arrFromString.length; i++) {
                finalString += arrFromString[i];
            }
        }
        return finalString;
    }

    public static String reverse(String string, int startIndex, int endIndex) {
        char[] arrFromString = string.toCharArray();
        int counterToCycle = 0;
        if (endIndex < startIndex) {
            int bufferStartIndex = startIndex;
            startIndex = endIndex;
            endIndex = bufferStartIndex;
            System.out.println("Начальный индекс больше конечного, поэтому: \nНачальный индекс = " + startIndex + "\nКонечный индекс = " + endIndex);
        }

        System.out.println("Символ по стартовому индексу: " + arrFromString[startIndex]);
        System.out.println("Символ по конечному индексу: " + arrFromString[endIndex]);

        char[] arrWithStartAndEndIndex = new char[endIndex - startIndex + 1];
        for (int i = startIndex; i <= endIndex; i++) {
            arrWithStartAndEndIndex[counterToCycle] = arrFromString[i];
            counterToCycle++;
        }

        String finalString = "";
        for (int i = 0; i < startIndex; i++) {
            finalString += arrFromString[i];
        }
        finalString += reverse(charArrayToString(arrWithStartAndEndIndex));
        for (int i = endIndex + 1; i < arrFromString.length; i++) {
            finalString += arrFromString[i];
        }

        return finalString;
    }

    private static String charArrayToString(char[] arrToString) {
        String finalString = "";
        for (int i = 0; i < arrToString.length; i++) {
            finalString += arrToString[i];
        }
        return finalString;
    }


}
