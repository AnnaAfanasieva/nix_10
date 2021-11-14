package ua.com.alevel;

import java.util.Scanner;

public class MathSetRun {

    private static MathSet mathSetWithCleanConstructor;
    private static MathSet mathSetWithCapacity;
    private static MathSet mathSetWithStartArray;
    private static MathSet mathSetWithStartArrays;
    private static MathSet mathSetWithStartMathSet;
    private static MathSet mathSetWithStartMathSets;

    public static void main(String[] args) {
        String separator = "______________________________________________________________________________________________________________";
        System.out.println(separator);
        createMathSets();
        System.out.println(separator + "\n\tОперации с MathSet");

        int numberOfOperation;
        boolean continueProgram = true;
        do {
            showMenu();
            numberOfOperation = 24;
            Scanner in = new Scanner(System.in);
            boolean correctNumberOfMathSet = false;
            while (!correctNumberOfMathSet) {
                try {
                    System.out.print("\nВыберите операцию: ");
                    String numberOfOperationString = in.nextLine();
                    numberOfOperation = Integer.parseInt(numberOfOperationString);
                    if (numberOfOperation >= 0 && numberOfOperation < 23) {
                        correctNumberOfMathSet = true;
                    } else {
                        System.out.println("Ошибка ввода, попробуйте еще раз");
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка ввода, попробуйте еще раз");
                }
            }

            switch (numberOfOperation) {
                case 1:
                    showMathSetsToWork();
                    break;
                case 2:
                    sum();
                    break;
                case 3:
                    addOneNumber();
                    break;
                case 4:
                    addArrayOfNumbers();
                    break;
                case 5:
                    join();
                    break;
                case 6:
                    joinSeveralMathSet();
                    break;
                case 7:
                    intersection();
                    break;
                case 8:
                    intersectionSeveralMathSet();
                    break;
                case 9:
                    sortDesc();
                    break;
                case 10:
                    sortDescFromFirstIndexToLastIndex();
                    break;
                case 11:
                    sortDescFromNumber();
                    break;
                case 12:
                    sortAsc();
                    break;
                case 13:
                    sortAscFromFirstIndexToLastIndex();
                    break;
                case 14:
                    sortAscFromNumber();
                    break;
                case 15:
                    get();
                    break;
                case 16:
                    getMax();
                    break;
                case 17:
                    getMin();
                    break;
                case 18:
                    getAverage();
                    break;
                case 19:
                    getMedian();
                    break;
                case 20:
                    clean();
                    break;
                case 21:
                    cleanSeveralNumbers();
                    break;
                case 22:
                    cut();
                    break; // void cut(int firstIndex, int lastIndex)
                case 0:
                    continueProgram = false;
                    break;
            }
            System.out.println(separator);
        } while (continueProgram);
    }

    private static void createMathSets() {
        System.out.println("\tСоздание MathSet\n");
        System.out.print("1. Создаем пустой MathSet: ");
        mathSetWithCleanConstructor = new MathSet();
        mathSetWithCleanConstructor.print();

        System.out.println("Заполняем MathSet элементами с помощью двух различных методов add: ");
        Number firstNumber = Math.round(Math.random() * 20);
        mathSetWithCleanConstructor.add(firstNumber);
        Number secondNumber = Math.round(Math.random() * 20);
        mathSetWithCleanConstructor.add(secondNumber);
        Number thirdNumber = Math.round(Math.random() * 20);
        mathSetWithCleanConstructor.add(thirdNumber);
        Number[] numbersAddToMathSetFirst = generateArrayWithThreeNumbers();
        mathSetWithCleanConstructor.add(numbersAddToMathSetFirst);
        Number[] numbersAddToMathSetSecond = generateArrayWithThreeNumbers();
        mathSetWithCleanConstructor.add(numbersAddToMathSetSecond);
        System.out.print("Элементы для добавление\n" + firstNumber + " " + secondNumber + " ");
        printArrayOfNumbers(numbersAddToMathSetFirst);
        printArrayOfNumbers(numbersAddToMathSetSecond);
        System.out.print("Итоговый MathSet: ");
        mathSetWithCleanConstructor.print();

        System.out.print("2. Создаем новый пустой MathSet с capacity = 5: ");
        mathSetWithCapacity = new MathSet(5);
        mathSetWithCapacity.print();
        System.out.println("Заполняем MathSet 3 элементами: ");
        numbersAddToMathSetFirst = generateArrayWithThreeNumbers();
        System.out.println("Массив для добавления");
        printArrayOfNumbers(numbersAddToMathSetFirst);
        mathSetWithCapacity.add(numbersAddToMathSetFirst);
        System.out.print("Итоговый MathSet: ");
        mathSetWithCapacity.print();

        System.out.println("3. Создаем новый пустой MathSet и передаем в конструктор массив Number: ");
        numbersAddToMathSetFirst = generateArrayWithThreeNumbers();
        System.out.println("Массив для добавления");
        printArrayOfNumbers(numbersAddToMathSetFirst);
        mathSetWithStartArray = new MathSet(numbersAddToMathSetFirst);
        System.out.print("Итоговый MathSet: ");
        mathSetWithStartArray.print();

        System.out.println("4. Создаем новый пустой MathSet и передаем в конструктор массивы Number: ");
        numbersAddToMathSetFirst = generateArrayWithThreeNumbers();
        numbersAddToMathSetSecond = generateArrayWithThreeNumbers();
        Number[] numbersAddToMathSetThird = generateArrayWithThreeNumbers();
        System.out.print("Массивы для добавления\n1 - ");
        printArrayOfNumbers(numbersAddToMathSetFirst);
        System.out.print("2 - ");
        printArrayOfNumbers(numbersAddToMathSetSecond);
        System.out.print("3 - ");
        printArrayOfNumbers(numbersAddToMathSetThird);
        mathSetWithStartArrays = new MathSet(numbersAddToMathSetFirst, numbersAddToMathSetSecond, numbersAddToMathSetThird);
        System.out.print("Итоговый MathSet: ");
        mathSetWithStartArrays.print();

        System.out.print("5. Создаем новый пустой MathSet и передаем в конструктор готовый MathSet (MathSet №2): ");
        mathSetWithStartMathSet = new MathSet(mathSetWithCapacity);
        mathSetWithStartMathSet.print();

        System.out.print("6. Создаем новый пустой MathSet и передаем в конструктор готовые MathSets (MathSet №2, MathSet3): ");
        mathSetWithStartMathSets = new MathSet(mathSetWithCapacity, mathSetWithStartArray);
        mathSetWithStartMathSets.print();
    }

    private static void showMenu() {
        System.out.println("\n\tВыберите операцию:");
        System.out.println("1  - Вывести текущие состояния MathSet");
        System.out.println("2  - Вывести сумму элементов каждого MathSet");
        System.out.println("3  - Добавить к каждому MathSet по одному рандомному элементу");
        System.out.println("4  - Добавить к каждому MathSet массив из 3 рандомных элементов");
        System.out.println("5  - Объеденить MathSet №1 и MathSet №2");
        System.out.println("6  - Объеденить MathSet №1, MathSet №2 и MathSet №4");
        System.out.println("7  - Найти пересечение MathSet №2 и MathSet №1");
        System.out.println("8  - Найти пересечение MathSet №2, MathSet №5 и MathSet №6");
        System.out.println("9  - Отсортировать MathSets по убыванию");
        System.out.println("10 - Отсортировать MathSets по убыванию с рандомных начального по конечный индекс");
        System.out.println("11 - Отсортировать MathSets по убыванию с рандомного элемента");
        System.out.println("12 - Отсортировать MathSets по возростанию");
        System.out.println("13 - Отсортировать MathSets по возростанию с рандомных начального по конечный индекс");
        System.out.println("14 - Отсортировать MathSets по возростанию с рандомного элемента");
        System.out.println("15 - Вывести элемент из каждого MathSet по рандомному индексу");
        System.out.println("16 - Вывести максимальные элементы MathSets");
        System.out.println("17 - Вывести минимальные элементы MathSets");
        System.out.println("18 - Вывести среднее значение каждого MathSet");
        System.out.println("19 - Вывести медиану каждого MathSet");
        System.out.println("20 - Очистить все MathSets");
        System.out.println("21 - Очистить из каждого MathSet 3 рандомных элемента");
        System.out.println("22 - Вырезать в каждом MathSet элементы с начального по конечный индекс");
        System.out.println("\n0  - Закончить выполнение программы");
    }

    private static void showMathSetsToWork() {
        System.out.println("\n\tMathSets для работы");
        System.out.print("MathSet №1 ");
        mathSetWithCleanConstructor.print();
        System.out.print("MathSet №2 ");
        mathSetWithCapacity.print();
        System.out.print("MathSet №3 ");
        mathSetWithStartArray.print();
        System.out.print("MathSet №4 ");
        mathSetWithStartArrays.print();
        System.out.print("MathSet №5 ");
        mathSetWithStartMathSet.print();
        System.out.print("MathSet №6 ");
        mathSetWithStartMathSets.print();
    }

    private static void sum() {
        System.out.println("\n\tСумма всех элементов MathSet");
        System.out.println("1. Сумма = " + mathSetWithCleanConstructor.sum());
        System.out.println("2. Сумма = " + mathSetWithCapacity.sum());
        System.out.println("3. Сумма = " + mathSetWithStartArray.sum());
        System.out.println("4. Сумма = " + mathSetWithStartArrays.sum());
        System.out.println("5. Сумма = " + mathSetWithStartMathSet.sum());
        System.out.println("6. Сумма = " + mathSetWithStartMathSets.sum());
    }

    private static void addOneNumber() {
        System.out.println("\n\tДобавление рандомного элемента в MathSet");
        Number newElement = Math.round(Math.random() * 20);
        mathSetWithCleanConstructor.add(newElement);
        System.out.print("MathSet №1 после попытки добавить элемент " + newElement + " ");
        mathSetWithCleanConstructor.print();
        newElement = Math.round(Math.random() * 20);
        mathSetWithCapacity.add(newElement);
        System.out.print("MathSet №2 после попытки добавить элемент " + newElement + " ");
        mathSetWithCapacity.print();
        newElement = Math.round(Math.random() * 20);
        mathSetWithStartArray.add(newElement);
        System.out.print("MathSet №3 после попытки добавить элемент " + newElement + " ");
        mathSetWithStartArray.print();
        newElement = Math.round(Math.random() * 20);
        mathSetWithStartArrays.add(newElement);
        System.out.print("MathSet №4 после попытки добавить элемент " + newElement + " ");
        mathSetWithStartArrays.print();
        newElement = Math.round(Math.random() * 20);
        mathSetWithStartMathSet.add(newElement);
        System.out.print("MathSet №5 после попытки добавить элемент " + newElement + " ");
        mathSetWithStartMathSet.print();
        newElement = Math.round(Math.random() * 20);
        mathSetWithStartMathSets.add(newElement);
        System.out.print("MathSet №6 после попытки добавить элемент " + newElement + " ");
        mathSetWithStartMathSets.print();
    }

    private static void addArrayOfNumbers() {
        System.out.println("\n\tДобавление массива с тремя рандомными элементами в MathSet");
        Number[] arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithCleanConstructor.add(arrayWithNewNumbers);
        System.out.print("MathSet №1 после попытки добавить массив элементов ");
        mathSetWithCleanConstructor.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithCapacity.add(arrayWithNewNumbers);
        System.out.print("MathSet №2 после попытки добавить массив элементов ");
        mathSetWithCapacity.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithStartArray.add(arrayWithNewNumbers);
        System.out.print("MathSet №3 после попытки добавить массив элементов ");
        mathSetWithStartArray.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithStartArrays.add(arrayWithNewNumbers);
        System.out.print("MathSet №4 после попытки добавить массив элементов ");
        mathSetWithStartArrays.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithStartMathSet.add(arrayWithNewNumbers);
        System.out.print("MathSet №5 после попытки добавить массив элементов ");
        mathSetWithStartMathSet.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithStartMathSets.add(arrayWithNewNumbers);
        System.out.print("MathSet №6 после попытки добавить массив элементов ");
        mathSetWithStartMathSets.print();
    }

    private static Number[] generateArrayWithThreeNumbers() {
        Number[] generatedArrayWithNumbers = new Number[3];
        for (int i = 0; i < 3; i++) {
            generatedArrayWithNumbers[i] = Math.round(Math.random() * 20);
        }
        return generatedArrayWithNumbers;
    }

    private static void printArrayOfNumbers(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }

    private static void getMax() {
        System.out.println("\n\tМаксимальное значение из MathSet");
        System.out.println("1. Max = " + mathSetWithCleanConstructor.getMax());
        System.out.println("2. Max = " + mathSetWithCapacity.getMax());
        System.out.println("3. Max = " + mathSetWithStartArray.getMax());
        System.out.println("4. Max = " + mathSetWithStartArrays.getMax());
        System.out.println("5. Max = " + mathSetWithStartMathSet.getMax());
        System.out.println("6. Max = " + mathSetWithStartMathSets.getMax());
    }

    private static void getMin() {
        System.out.println("\n\tМинимальное значение из MathSet");
        System.out.println("1. Min = " + mathSetWithCleanConstructor.getMin());
        System.out.println("2. Min = " + mathSetWithCapacity.getMin());
        System.out.println("3. Min = " + mathSetWithStartArray.getMin());
        System.out.println("4. Min = " + mathSetWithStartArrays.getMin());
        System.out.println("5. Min = " + mathSetWithStartMathSet.getMin());
        System.out.println("6. Min = " + mathSetWithStartMathSets.getMin());
    }

    private static void getAverage() {
        System.out.println("\n\tСреднее значение из элементов MathSet");
        System.out.println("1. Min = " + mathSetWithCleanConstructor.getAverage());
        System.out.println("2. Min = " + mathSetWithCapacity.getAverage());
        System.out.println("3. Min = " + mathSetWithStartArray.getAverage());
        System.out.println("4. Min = " + mathSetWithStartArrays.getAverage());
        System.out.println("5. Min = " + mathSetWithStartMathSet.getAverage());
        System.out.println("6. Min = " + mathSetWithStartMathSets.getAverage());
    }

    private static void getMedian() {
        System.out.println("\n\tМедиана из значений MathSet");
        System.out.println("1. Median = " + mathSetWithCleanConstructor.getMedian());
        System.out.println("2. Median = " + mathSetWithCapacity.getMedian());
        System.out.println("3. Median = " + mathSetWithStartArray.getMedian());
        System.out.println("4. Median = " + mathSetWithStartArrays.getMedian());
        System.out.println("5. Median = " + mathSetWithStartMathSet.getMedian());
        System.out.println("6. Median = " + mathSetWithStartMathSets.getMedian());
    }

    private static void get() {
        System.out.println("\n\tНайти элемент по индексу из MathSet");
        int index = (int) (Math.random() * (mathSetWithCleanConstructor.getCapacity() - 1));
        System.out.println("1. Элемент с индексом " + index + " = " + mathSetWithCleanConstructor.get(index));
        index = (int) (Math.random() * (mathSetWithCapacity.getCapacity() - 1));
        System.out.println("2. Элемент с индексом " + index + " = " + mathSetWithCapacity.get(index));
        index = (int) (Math.random() * (mathSetWithStartArray.getCapacity() - 1));
        System.out.println("3. Элемент с индексом " + index + " = " + mathSetWithStartArray.get(index));
        index = (int) (Math.random() * (mathSetWithStartArrays.getCapacity() - 1));
        System.out.println("4. Элемент с индексом " + index + " = " + mathSetWithStartArrays.get(index));
        index = (int) (Math.random() * (mathSetWithStartMathSet.getCapacity() - 1));
        System.out.println("5. Элемент с индексом " + index + " = " + mathSetWithStartMathSet.get(index));
        index = (int) (Math.random() * (mathSetWithStartMathSets.getCapacity() - 1));
        System.out.println("6. Элемент с индексом " + index + " = " + mathSetWithStartMathSets.get(index));
    }

    private static void sortDescFromNumber() {
        System.out.println("\n\tСортировка по убыванию элементов MathSet начиная с элемента");
        Number firstElement = Math.round(Math.random() * 20);
        mathSetWithCleanConstructor.sortDesc(firstElement);
        System.out.print("MathSet №1 после сортировки с элемента " + firstElement + ": ");
        mathSetWithCleanConstructor.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithCapacity.sortDesc(firstElement);
        System.out.print("MathSet №2 после сортировки с элемента " + firstElement + ": ");
        mathSetWithCapacity.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithStartArray.sortDesc(firstElement);
        System.out.print("MathSet №3 после сортировки с элемента " + firstElement + ": ");
        mathSetWithStartArray.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithStartArrays.sortDesc(firstElement);
        System.out.print("MathSet №4 после сортировки с элемента " + firstElement + ": ");
        mathSetWithStartArrays.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithStartMathSet.sortDesc(firstElement);
        System.out.print("MathSet №5 после сортировки с элемента " + firstElement + ": ");
        mathSetWithStartMathSet.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithStartMathSets.sortDesc(firstElement);
        System.out.print("MathSet №6 после сортировки с элемента " + firstElement + ": ");
        mathSetWithStartMathSets.print();
    }

    private static void sortAscFromNumber() {
        System.out.println("\n\tСортировка по возростанию элементов MathSet начиная с элемента");
        Number firstElement = Math.round(Math.random() * 20);
        mathSetWithCleanConstructor.sortAsc(firstElement);
        System.out.print("MathSet №1 после сортировки с элемента " + firstElement + ": ");
        mathSetWithCleanConstructor.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithCapacity.sortAsc(firstElement);
        System.out.print("MathSet №2 после сортировки с элемента " + firstElement + ": ");
        mathSetWithCapacity.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithStartArray.sortAsc(firstElement);
        System.out.print("MathSet №3 после сортировки с элемента " + firstElement + ": ");
        mathSetWithStartArray.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithStartArrays.sortAsc(firstElement);
        System.out.print("MathSet №4 после сортировки с элемента " + firstElement + ": ");
        mathSetWithStartArrays.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithStartMathSet.sortAsc(firstElement);
        System.out.print("MathSet №5 после сортировки с элемента " + firstElement + ": ");
        mathSetWithStartMathSet.print();
        firstElement = Math.round(Math.random() * 20);
        mathSetWithStartMathSets.sortAsc(firstElement);
        System.out.print("MathSet №6 после сортировки с элемента " + firstElement + ": ");
        mathSetWithStartMathSets.print();
    }

    private static void sortAscFromFirstIndexToLastIndex() {
        System.out.println("\n\tСортировка по возростанию элементов MathSet с начального по конечный индекс");
        int firstIndex = (int) (Math.random() * (mathSetWithCleanConstructor.getCapacity() - 1));
        int lastIndex = (int) (Math.random() * (mathSetWithCleanConstructor.getCapacity() - 1));
        mathSetWithCleanConstructor.sortAsc(firstIndex, lastIndex);
        System.out.print("MathSet №1 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithCleanConstructor.print();
        firstIndex = (int) (Math.random() * (mathSetWithCapacity.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithCapacity.getCapacity() - 1));
        mathSetWithCapacity.sortAsc(firstIndex, lastIndex);
        System.out.print("MathSet №2 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithCapacity.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartArray.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartArray.getCapacity() - 1));
        mathSetWithStartArray.sortAsc(firstIndex, lastIndex);
        System.out.print("MathSet №3 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartArray.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartArrays.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartArrays.getCapacity() - 1));
        mathSetWithStartArrays.sortAsc(firstIndex, lastIndex);
        System.out.print("MathSet №4 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartArrays.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartMathSet.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartMathSet.getCapacity() - 1));
        mathSetWithStartMathSet.sortAsc(firstIndex, lastIndex);
        System.out.print("MathSet №5 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartMathSet.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartMathSets.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartMathSets.getCapacity() - 1));
        mathSetWithStartMathSets.sortAsc(firstIndex, lastIndex);
        System.out.print("MathSet №6 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartMathSets.print();
    }

    private static void sortDescFromFirstIndexToLastIndex() {
        System.out.println("\n\tСортировка по убыванию элементов MathSet с начального по конечный индекс");
        int firstIndex = (int) (Math.random() * (mathSetWithCleanConstructor.getCapacity() - 1));
        int lastIndex = (int) (Math.random() * (mathSetWithCleanConstructor.getCapacity() - 1));
        mathSetWithCleanConstructor.sortDesc(firstIndex, lastIndex);
        System.out.print("MathSet №1 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithCleanConstructor.print();
        firstIndex = (int) (Math.random() * (mathSetWithCapacity.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithCapacity.getCapacity() - 1));
        mathSetWithCapacity.sortDesc(firstIndex, lastIndex);
        System.out.print("MathSet №2 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithCapacity.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartArray.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartArray.getCapacity() - 1));
        mathSetWithStartArray.sortDesc(firstIndex, lastIndex);
        System.out.print("MathSet №3 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartArray.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartArrays.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartArrays.getCapacity() - 1));
        mathSetWithStartArrays.sortDesc(firstIndex, lastIndex);
        System.out.print("MathSet №4 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartArrays.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartMathSet.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartMathSet.getCapacity() - 1));
        mathSetWithStartMathSet.sortDesc(firstIndex, lastIndex);
        System.out.print("MathSet №5 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartMathSet.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartMathSets.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartMathSets.getCapacity() - 1));
        mathSetWithStartMathSets.sortDesc(firstIndex, lastIndex);
        System.out.print("MathSet №6 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartMathSets.print();
    }

    private static void sortDesc() {
        System.out.println("\n\tСортировка по убыванию элементов MathSet");
        mathSetWithCleanConstructor.sortDesc();
        System.out.print("MathSet №1 после сортировки ");
        mathSetWithCleanConstructor.print();
        mathSetWithCapacity.sortDesc();
        System.out.print("MathSet №2 после сортировки ");
        mathSetWithCapacity.print();
        mathSetWithStartArray.sortDesc();
        System.out.print("MathSet №3 после сортировки ");
        mathSetWithStartArray.print();
        mathSetWithStartArrays.sortDesc();
        System.out.print("MathSet №4 после сортировки ");
        mathSetWithStartArrays.print();
        mathSetWithStartMathSet.sortDesc();
        System.out.print("MathSet №5 после сортировки ");
        mathSetWithStartMathSet.print();
        mathSetWithStartMathSets.sortDesc();
        System.out.print("MathSet №6 после сортировки ");
        mathSetWithStartMathSets.print();
    }

    private static void sortAsc() {
        System.out.println("\n\tСортировка по возростанию элементов MathSet");
        mathSetWithCleanConstructor.sortAsc();
        System.out.print("MathSet №1 после сортировки ");
        mathSetWithCleanConstructor.print();
        mathSetWithCapacity.sortAsc();
        System.out.print("MathSet №2 после сортировки ");
        mathSetWithCapacity.print();
        mathSetWithStartArray.sortAsc();
        System.out.print("MathSet №3 после сортировки ");
        mathSetWithStartArray.print();
        mathSetWithStartArrays.sortAsc();
        System.out.print("MathSet №4 после сортировки ");
        mathSetWithStartArrays.print();
        mathSetWithStartMathSet.sortAsc();
        System.out.print("MathSet №5 после сортировки ");
        mathSetWithStartMathSet.print();
        mathSetWithStartMathSets.sortAsc();
        System.out.print("MathSet №6 после сортировки ");
        mathSetWithStartMathSets.print();
    }

    private static void clean() {
        System.out.println("\n\tОчистка всех MathSet");
        mathSetWithCleanConstructor.clean();
        System.out.print("MathSet №1 после очистки ");
        mathSetWithCleanConstructor.print();
        mathSetWithCapacity.clean();
        System.out.print("MathSet №2 после очистки ");
        mathSetWithCapacity.print();
        mathSetWithStartArray.clean();
        System.out.print("MathSet №3 после очистки ");
        mathSetWithStartArray.print();
        mathSetWithStartArrays.clean();
        System.out.print("MathSet №4 после очистки ");
        mathSetWithStartArrays.print();
        mathSetWithStartMathSet.clean();
        System.out.print("MathSet №5 после очистки ");
        mathSetWithStartMathSet.print();
        mathSetWithStartMathSets.clean();
        System.out.print("MathSet №6 после очистки ");
        mathSetWithStartMathSets.print();
    }

    private static void join() {
        System.out.print("MathSet №1 ");
        mathSetWithCleanConstructor.print();
        System.out.print("MathSet №2 ");
        mathSetWithCapacity.print();
        mathSetWithCleanConstructor.join(mathSetWithCapacity);
        System.out.print("MathSet №1 после объединения с MathSet №2 ");
        mathSetWithCleanConstructor.print();
    }

    private static void joinSeveralMathSet() {
        System.out.print("MathSet №1 ");
        mathSetWithCleanConstructor.print();
        System.out.print("MathSet №2 ");
        mathSetWithCapacity.print();
        System.out.print("MathSet №4 ");
        mathSetWithStartArrays.print();
        mathSetWithCleanConstructor.join(mathSetWithCapacity, mathSetWithStartArrays);
        System.out.print("MathSet №1 после объединения с MathSet №2 и MathSet №4 ");
        mathSetWithCleanConstructor.print();
    }

    private static void intersection() {
        System.out.print("MathSet №2 ");
        mathSetWithCapacity.print();
        System.out.print("MathSet №1 ");
        mathSetWithCleanConstructor.print();
        mathSetWithCapacity.intersection(mathSetWithCleanConstructor);
        System.out.print("MathSet №2 после пересечения с MathSet №1 ");
        mathSetWithCapacity.print();
    }

    private static void intersectionSeveralMathSet() {
        System.out.print("MathSet №2 ");
        mathSetWithCapacity.print();
        System.out.print("MathSet №5 ");
        mathSetWithStartMathSet.print();
        System.out.print("MathSet №6 ");
        mathSetWithStartMathSets.print();
        mathSetWithCapacity.intersection(mathSetWithStartMathSet, mathSetWithStartMathSets);
        System.out.print("MathSet №2 после пересечения с MathSet №5 и MathSet №6 ");
        mathSetWithCapacity.print();
    }

    private static void cleanSeveralNumbers() {
        System.out.println("\n\tУдаление нескольких рандомных элементов MathSet");
        Number[] arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithCleanConstructor.clean(arrayWithNewNumbers);
        System.out.print("MathSet №1 после удаления элементов ");
        mathSetWithCleanConstructor.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithCapacity.clean(arrayWithNewNumbers);
        System.out.print("MathSet №2 после удаления элементов ");
        mathSetWithCapacity.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithStartArray.clean(arrayWithNewNumbers);
        System.out.print("MathSet №3 после удаления элементов ");
        mathSetWithStartArray.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithStartArrays.clean(arrayWithNewNumbers);
        System.out.print("MathSet №4 после удаления элементовв ");
        mathSetWithStartArrays.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithStartMathSet.clean(arrayWithNewNumbers);
        System.out.print("MathSet №5 после удаления элементов ");
        mathSetWithStartMathSet.print();
        arrayWithNewNumbers = generateArrayWithThreeNumbers();
        System.out.print("Сгенерированный массив: ");
        printArrayOfNumbers(arrayWithNewNumbers);
        mathSetWithStartMathSets.clean(arrayWithNewNumbers);
        System.out.print("MathSet №6 после удаления элементовв ");
        mathSetWithStartMathSets.print();
    }

    private static void cut() {
        System.out.println("\n\tУдаление элементов MathSet с начального по конечный индекс");
        int firstIndex = (int) (Math.random() * (mathSetWithCleanConstructor.getCapacity() - 1));
        int lastIndex = (int) (Math.random() * (mathSetWithCleanConstructor.getCapacity() - 1));
        mathSetWithCleanConstructor.cut(firstIndex, lastIndex);
        System.out.print("MathSet №1 после удаления элементов с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithCleanConstructor.print();
        firstIndex = (int) (Math.random() * (mathSetWithCapacity.getIndexForMathSetWithFinalCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithCapacity.getIndexForMathSetWithFinalCapacity() - 1));
        mathSetWithCapacity.cut(firstIndex, lastIndex);
        System.out.print("MathSet №2 после удаления элементов с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithCapacity.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartArray.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartArray.getCapacity() - 1));
        mathSetWithStartArray.cut(firstIndex, lastIndex);
        System.out.print("MathSet №3 после удаления элементов с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartArray.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartArrays.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartArrays.getCapacity() - 1));
        mathSetWithStartArrays.cut(firstIndex, lastIndex);
        System.out.print("MathSet №4 после удаления элементов с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartArrays.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartMathSet.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartMathSet.getCapacity() - 1));
        mathSetWithStartMathSet.cut(firstIndex, lastIndex);
        System.out.print("MathSet №5 после сортировки с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartMathSet.print();
        firstIndex = (int) (Math.random() * (mathSetWithStartMathSets.getCapacity() - 1));
        lastIndex = (int) (Math.random() * (mathSetWithStartMathSets.getCapacity() - 1));
        mathSetWithStartMathSets.cut(firstIndex, lastIndex);
        System.out.print("MathSet №6 после после удаления элементов с " + firstIndex + " по " + lastIndex + " индексы: ");
        mathSetWithStartMathSets.print();
    }
}