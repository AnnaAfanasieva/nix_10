package ua.com.alevel;

import java.util.Objects;

public final class MathSet {

    private Number[] numbers;
    private int capacity;
    private boolean hasConstantSize = false;
    private int indexForMathSetWithFinalCapacity = 0;

    public MathSet() {
        capacity = 0;
        numbers = new Number[capacity];
    }

    public MathSet(int capacity) {
        this.capacity = capacity;
        hasConstantSize = true;
        numbers = new Number[capacity];
    }

    public MathSet(Number[] numbers) {
        this.numbers = arrayOfUniqueNumbers(numbers);
        capacity = this.numbers.length;
    }

    public MathSet(Number[]... numbers) {
        capacity = 0;
        for (int i = 0; i < numbers.length; i++) {
            capacity += numbers[i].length;
        }
        Number[] allNumbers = new Number[capacity];
        int indexInArrayAllNumbers = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                allNumbers[indexInArrayAllNumbers] = numbers[i][j];
                indexInArrayAllNumbers++;
            }
        }
        this.numbers = arrayOfUniqueNumbers(allNumbers);
        capacity = this.numbers.length;
    }

    public MathSet(MathSet mathSet) {
        this.numbers = mathSet.toArrayWithoutNull();
        capacity = numbers.length;
    }

    public MathSet(MathSet... mathSets) {
        this.numbers = mathSets[0].toArrayWithoutNull();
        this.capacity = this.numbers.length;
        for (int i = 1; i < mathSets.length; i++) {
            join(mathSets[i]);
        }
    }

    public void print() {
        StringBuffer allNumbers = new StringBuffer();
        allNumbers.append("[");
        for (int i = 0; i < capacity; i++) {
            allNumbers.append(numbers[i] + " ");
        }
        allNumbers = new StringBuffer(allNumbers.toString().trim() + "]");
        System.out.println(allNumbers);
    }

    private Number[] arrayOfUniqueNumbers(Number[] numbers) {
        Number[] arrayWithUniqueNumbers = new Number[numbers.length];
        int indexArrayWithUniqueNumbers = 0;
        boolean isUniqueNumber = true;
        if (numbers.length == 0) {
            return new Number[0];
        } else {
            arrayWithUniqueNumbers[0] = numbers[0];
            indexArrayWithUniqueNumbers++;
        }
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (Objects.equals(numbers[i], numbers[j])) {
                    isUniqueNumber = false;
                }
            }
            if (isUniqueNumber) {
                arrayWithUniqueNumbers[indexArrayWithUniqueNumbers] = numbers[i];
                indexArrayWithUniqueNumbers++;
            }
            isUniqueNumber = true;
        }

        int capacity = 0;
        for (int i = 0; i < indexArrayWithUniqueNumbers; i++) {
            if (arrayWithUniqueNumbers[i] != null) {
                capacity++;
            }
        }

        Number[] finalArrayWithUniqueNumbers = new Number[capacity];
        for (int i = 0; i < capacity; i++) {
            finalArrayWithUniqueNumbers[i] = arrayWithUniqueNumbers[i];
        }

        return finalArrayWithUniqueNumbers;
    }

    private boolean isUniqueNumber(Number number) {
        boolean isUniqueNumber = true;
        if (hasConstantSize) {
            for (int i = 0; i < indexForMathSetWithFinalCapacity; i++) {
                if (number.equals(numbers[i])) {
                    isUniqueNumber = false;
                }
            }
        } else {
            for (int i = 0; i < capacity; i++) {
                if (number.equals(numbers[i])) {
                    isUniqueNumber = false;
                }
            }
        }
        return isUniqueNumber;
    }

    public void add(Number newNumber) {
        Number[] arrayWithNewNumber;
        if (hasConstantSize && capacity == indexForMathSetWithFinalCapacity) {
            System.out.println("MathSet переполнен");
        } else if (hasConstantSize) {
            if (isUniqueNumber(newNumber)) {
                numbers[indexForMathSetWithFinalCapacity] = newNumber;
                indexForMathSetWithFinalCapacity++;
            }
        } else {
            if (isUniqueNumber(newNumber)) {
                arrayWithNewNumber = new Number[capacity + 1];
                for (int i = 0; i < capacity; i++) {
                    arrayWithNewNumber[i] = numbers[i];
                }
                arrayWithNewNumber[capacity] = newNumber;
                capacity++;
                copyArray(arrayWithNewNumber);
            }
        }
    }

    private void copyArray(Number[] arrayToCopy) {
        if (capacity == arrayToCopy.length - 1) {
            capacity++;
        }
        numbers = new Number[capacity];
        for (int i = 0; i < capacity; i++) {
            numbers[i] = arrayToCopy[i];
        }
    }

    public void add(Number... numbers) {
        for (int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }
    }

    public void join(MathSet mathSet) {
        int capacity;
        if (hasConstantSize) {
            capacity = indexForMathSetWithFinalCapacity;
        } else {
            capacity = this.capacity;
        }
        int mathSetNumbersLength;
        if (mathSet.hasConstantSize) {
            mathSetNumbersLength = mathSet.indexForMathSetWithFinalCapacity;
        } else {
            mathSetNumbersLength = mathSet.numbers.length;
        }
        for (int i = 0; i < mathSetNumbersLength; i++) {
            if (hasConstantSize && capacity == this.capacity) {
                System.out.println("MathSet переполнен");
            } else {
                add(mathSet.numbers[i]);
                capacity++;
            }
        }
    }

    public void join(MathSet... mathSets) {
        for (int i = 0; i < mathSets.length; i++) {
            join(mathSets[i]);
        }
    }

    public void intersection(MathSet mathSet) {
        int newCapacity;
        if (hasConstantSize) {
            newCapacity = this.capacity;
        } else {
            newCapacity = capacity + mathSet.capacity;
        }
        Number[] arrayIntersection = new Number[newCapacity];
        newCapacity = 0;
        for (int i = 0; i < mathSet.capacity; i++) {
            if (!isUniqueNumber(mathSet.numbers[i])) {
                if (hasConstantSize && newCapacity == this.capacity) {
                    System.out.println("MathSet переполнен");
                } else {
                    arrayIntersection[newCapacity] = mathSet.numbers[i];
                    newCapacity++;
                }
            }
        }
        if (!hasConstantSize) {
            this.capacity = newCapacity;
        }
        this.numbers = new Number[this.capacity];
        for (int i = 0; i < this.capacity; i++) {
            this.numbers[i] = arrayIntersection[i];
        }
    }

    public void intersection(MathSet... mathSets) {
        for (int i = 0; i < mathSets.length; i++) {
            intersection(mathSets[i]);
        }
    }

    public void sortDesc() {
        sortDesc(numbers);
    }

    private Number[] sortDesc(Number[] numbers) {
        boolean sorted = false;
        Number bufferNumber;
        float firstNumber;
        float secondNumber;
        int lengthOfNumbers = 0;
        if (hasConstantSize) {
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] != null) {
                    lengthOfNumbers++;
                }
            }
        } else {
            lengthOfNumbers = numbers.length;
        }
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < lengthOfNumbers - 1; i++) {
                firstNumber = Float.parseFloat(numbers[i].toString());
                secondNumber = Float.parseFloat(numbers[i + 1].toString());
                if (firstNumber < secondNumber) {
                    bufferNumber = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = bufferNumber;
                    sorted = false;
                }
            }
        }
        return numbers;
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        sortWithFirstAndLastIndex(false, firstIndex, lastIndex);
    }

    private void sortWithFirstAndLastIndex(boolean isSortAsc, int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex) {
            int bufferIndex = firstIndex;
            firstIndex = lastIndex;
            lastIndex = bufferIndex;
        }

        if (hasConstantSize && lastIndex >= indexForMathSetWithFinalCapacity) {
            lastIndex = indexForMathSetWithFinalCapacity - 1;
        } else if (!hasConstantSize && lastIndex >= capacity) {
            lastIndex = capacity - 1;
        }

        if (hasConstantSize && firstIndex >= indexForMathSetWithFinalCapacity) {
            firstIndex = indexForMathSetWithFinalCapacity - 1;
        }

        if (firstIndex < 0) {
            firstIndex = 0;
        }

        Number[] arrayToSort = toArray(firstIndex, lastIndex);
        if (isSortAsc) {
            arrayToSort = sortAsc(arrayToSort);
        } else {
            arrayToSort = sortDesc(arrayToSort);
        }
        int indexArrayToSort = 0;
        for (int i = firstIndex; i <= lastIndex; i++) {
            numbers[i] = arrayToSort[indexArrayToSort];
            indexArrayToSort++;
        }
    }

    private void sortWithFirstNumber(boolean isSortAsc, Number value) {
        int firstIndex = capacity;
        for (int i = 0; i < capacity; i++) {
            if (value.equals(numbers[i])) {
                firstIndex = i;
                i = capacity;
            }
        }
        if (firstIndex == capacity) {
            System.out.println("Такого элемента в списке нет");
        }
        int indexLastNumberInArrayNumbers = capacity - 1;
        if (firstIndex < indexLastNumberInArrayNumbers) {
            sortWithFirstAndLastIndex(isSortAsc, firstIndex, indexLastNumberInArrayNumbers);
        }
    }

    public void sortDesc(Number value) {
        sortWithFirstNumber(false, value);
    }

    public void sortAsc() {
        sortAsc(numbers);
    }

    private Number[] sortAsc(Number[] numbers) {
        boolean sorted = false;
        Number bufferNumber;
        float firstNumber;
        float secondNumber;
        int lengthOfNumbers = 0;
        if (hasConstantSize) {
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] != null) {
                    lengthOfNumbers++;
                }
            }
        } else {
            lengthOfNumbers = numbers.length;
        }
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < lengthOfNumbers - 1; i++) {
                firstNumber = Float.parseFloat(numbers[i].toString());
                secondNumber = Float.parseFloat(numbers[i + 1].toString());
                if (firstNumber > secondNumber) {
                    bufferNumber = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = bufferNumber;
                    sorted = false;
                }
            }
        }
        return numbers;
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        sortWithFirstAndLastIndex(true, firstIndex, lastIndex);
    }

    public void sortAsc(Number value) {
        sortWithFirstNumber(true, value);
    }

    public Number get(int index) {
        Number requiredNumber = null;
        try {
            requiredNumber = numbers[index];
        } catch (IndexOutOfBoundsException e) {

        }
        return requiredNumber;
    }

    public Number getMax() {
        Number maxValue = null;
        if (capacity == 0 || numbers[0] == null) {
            return maxValue;
        }
        maxValue = numbers[0];
        for (int i = 1; i < capacity; i++) {
            if (numbers[i] != null && Float.parseFloat(maxValue.toString()) < Float.parseFloat(numbers[i].toString())) {
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    public Number getMin() {
        Number minValue = null;
        if (capacity == 0 || numbers[0] == null) {
            return minValue;
        }
        minValue = numbers[0];
        for (int i = 1; i < capacity; i++) {
            if (numbers[i] != null && Float.parseFloat(minValue.toString()) > Float.parseFloat(numbers[i].toString())) {
                minValue = numbers[i];
            }
        }
        return minValue;
    }

    public Number getAverage() {
        float sumOfElements = 0;
        int amountOfNumbers = 0;
        for (int i = 0; i < capacity; i++) {
            if (numbers[i] != null) {
                sumOfElements += Float.parseFloat(numbers[i].toString());
                amountOfNumbers++;
            }
        }
        return sumOfElements / amountOfNumbers;
    }

    public Number sum() {
        float sumOfElements = 0;
        for (int i = 0; i < capacity; i++) {
            if (numbers[i] != null) {
                sumOfElements += Float.parseFloat(numbers[i].toString());
            }
        }
        return sumOfElements;
    }

    public Number getMedian() {
        int length;
        Number[] copyCurrentNumbers;
        if (hasConstantSize) {
            length = indexForMathSetWithFinalCapacity;
            copyCurrentNumbers = new Number[length];
            for (int i = 0; i < length; i++) {
                copyCurrentNumbers[i] = numbers[i];
            }
        } else {
            length = capacity;
            copyCurrentNumbers = numbers;
        }
        copyCurrentNumbers = sortAsc(copyCurrentNumbers);
        int indexOfMiddleElement = length / 2;
        ;
        Number median;
        if (length % 2 != 0) {
            median = copyCurrentNumbers[indexOfMiddleElement];
        } else {
            float firstNumber = Float.parseFloat(copyCurrentNumbers[indexOfMiddleElement - 1].toString());
            float secondNumber = Float.parseFloat(copyCurrentNumbers[indexOfMiddleElement].toString());
            median = (firstNumber + secondNumber) / 2;
        }
        return median;
    }

    public Number[] toArray() {
        return numbers;
    }

    public Number[] toArrayWithoutNull() {
        int numberOfNotNullNumbers = 0;
        for (int i = 0; i < capacity; i++) {
            if (numbers[i] != null) {
                numberOfNotNullNumbers++;
            }
        }
        Number[] arrayWithoutNull = new Number[numberOfNotNullNumbers];
        int indexArrayWithoutNull = 0;
        for (int i = 0; i < numberOfNotNullNumbers; i++) {
            arrayWithoutNull[indexArrayWithoutNull] = numbers[i];
            indexArrayWithoutNull++;
        }
        return arrayWithoutNull;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] numbersToArray;
        if (firstIndex > lastIndex) {
            int bufferIndex = firstIndex;
            firstIndex = lastIndex;
            lastIndex = bufferIndex;
        }

        if (lastIndex >= capacity) {
            lastIndex = capacity - 1;
        }
        if (firstIndex > capacity - 1) {
            return new Number[0];
        } else if (firstIndex < 0) {
            firstIndex = 0;
        }

        int lengthOfNewArray = lastIndex - firstIndex + 1;

        numbersToArray = new Number[lengthOfNewArray];
        int indexNewArray = 0;
        for (int i = firstIndex; i <= lastIndex; i++) {
            numbersToArray[indexNewArray] = numbers[i];
            indexNewArray++;
        }
        return numbersToArray;
    }

    //Обрезает существующий массив с начального по конечный индекс
    public void cut(int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex) {
            int bufferIndex = firstIndex;
            firstIndex = lastIndex;
            lastIndex = bufferIndex;
        }

        int capacity;
        if (hasConstantSize) {
            capacity = indexForMathSetWithFinalCapacity;
        } else {
            capacity = this.capacity;
        }
        if (lastIndex >= capacity) {
            lastIndex = capacity - 1;
        }
        if (firstIndex > capacity - 1) {
            clean();
        } else if (firstIndex < 0) {
            firstIndex = 0;
        }

        Number[] cutArray = toArray(firstIndex, lastIndex);
        if (hasConstantSize) {
            indexForMathSetWithFinalCapacity = numbers.length;
            for (int i = 0; i < cutArray.length; i++) {
                numbers[i] = cutArray[i];
            }
            for (int i = cutArray.length; i < this.capacity; i++) {
                numbers[i] = null;
            }
        } else {
            numbers = cutArray;
            this.capacity = numbers.length;
        }
    }

    //20. MathSet cut(int firstIndex, int lastIndex)
    public MathSet cutAndCreateNewObject(int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex) {
            int bufferIndex = firstIndex;
            firstIndex = lastIndex;
            lastIndex = bufferIndex;
        }
        int capacity;
        if (hasConstantSize) {
            capacity = indexForMathSetWithFinalCapacity;
        } else {
            capacity = this.capacity;
        }
        if (lastIndex >= capacity) {
            lastIndex = capacity - 1;
        }
        if (firstIndex > capacity - 1) {
            clean();
        } else if (firstIndex < 0) {
            firstIndex = 0;
        }
        Number[] cutArray = toArray(firstIndex, lastIndex);
        return new MathSet(cutArray);
    }

    public void clean() {
        if (hasConstantSize) {
            for (int i = 0; i < indexForMathSetWithFinalCapacity; i++) {
                numbers[i] = null;
            }
            indexForMathSetWithFinalCapacity = 0;
        } else {
            capacity = 0;
            numbers = new Number[capacity];
        }
    }

    public void clean(Number[] numbers) {
        int capacity;
        if (hasConstantSize) {
            capacity = indexForMathSetWithFinalCapacity;
        } else {
            capacity = this.capacity;
        }

        for (int i = 0; i < capacity; i++) {
            if (this.numbers[i] != null) {
                for (int j = 0; j < numbers.length; j++) {
                    if (numbers[j] != null && numbers[j].equals(this.numbers[i])) {
                        for (int k = i; k < capacity - 1; k++) {
                            this.numbers[k] = this.numbers[k + 1];
                        }
                        capacity--;
                        if (hasConstantSize) {
                            this.numbers[capacity] = null;
                        }
                        j = numbers.length;
                    }
                }
            }
        }

        if (hasConstantSize) {
            indexForMathSetWithFinalCapacity = capacity;
        } else {
            this.capacity = capacity;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getIndexForMathSetWithFinalCapacity() {
        return indexForMathSetWithFinalCapacity;
    }
}
