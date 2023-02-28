package pl.coderslab;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main01 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int selectOption = 0;
        final int FIRST_OPTION = 1;
        final int SECOND_OPTION = 2;
        final int THIRD_OPTION = 3;
        final int FOURTH_OPTION = 4;
        final int FIVE_OPTION = 5;
        while (selectOption != FIRST_OPTION && selectOption != SECOND_OPTION && selectOption != THIRD_OPTION && selectOption != FOURTH_OPTION && selectOption != FIRST_OPTION) {

            System.out.println(ConsoleColors.YELLOW_BOLD + "---MENADŻER ZADAŃ---");
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "WYBIERZ JEDNĄ Z OPCJI: " + ConsoleColors.RESET);
            System.out.println("1. PODGLĄD ZADAŃ");
            System.out.println("2. DODAJ  ZADANIE");
            System.out.println("3. USUŃ ZADANIE");
            System.out.println(ConsoleColors.GREEN + "4. POMOC" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "5. WYJŚCIE");

            selectOption = scanner.nextInt();
            switch (selectOption) {
                case 1:
                    System.out.println("Wybrano opcje PODGLĄD ZADAŃ");
                    File source = new File("tasks.csv");
                    String[] arr = new String[0];
                    try (Scanner firstSourceScanner = new Scanner(source)) {
                        while (firstSourceScanner.hasNextLine()) {
                            String line = firstSourceScanner.nextLine();
                            arr = Arrays.copyOf(arr, arr.length+1);
                            arr[arr.length-1]=line;



                        }

                    } catch (IOException e) {
                        System.out.println("Nie udało się otworzyć pliku, spróbuj jeszcze raz !");
                    }

                    System.out.println(Arrays.toString(arr));
                    break;
                case 2:
                    System.out.println("Wybrano opcje DODAJ ZADANIE");
                    break;
                case 3:
                    System.out.println("Wybrano opcje USUŃ ZADANIE");
                case 4:
                    break;
                case 5:
                    System.out.println("Wybrano opcje WYJŚCIE");
                    System.out.println(ConsoleColors.RED_BOLD + "DO ZOBACZENIA");

            }

        }
        if (selectOption == FIVE_OPTION) {
            scanner.close();

        }
    }


}
