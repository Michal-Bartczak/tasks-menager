package pl.coderslab;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final String regexDataFormat = "(20[0-2][0-9]|2030)-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
        int selectOption = 0;
        // tworzymy metodę do pobierania danych z pliku
        String[] arrayBox = new String[0];
        String readLine = "";
        File source = new File("tasks.csv");
        Path sourceA = Paths.get("tasks.csv");
        if (!source.exists()) {
            try {
                Files.createFile(sourceA);
            } catch (IOException e) {
                System.out.println("Nie udało się utworzć pliku");
            }
        }
        arrayBox = readFile(arrayBox, readLine, source);
        // wyświetlamy użytkownikowi możliwe opcje do wyboru(menu)
        boolean check = true;
        while (check == true) {
            menuView();
            Scanner scannerNavi = new Scanner(System.in);
            int inputOption = 0;
            try {
                inputOption = scannerNavi.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Aby przejść dalej wpisz liczbę 1-5");
            }
            switch (inputOption) {
                case 1:
                    caseFirstView(arrayBox);
                    break;
                case 2:
                    String[] tasks = new String[0];
                    String task = "";
                    arrayBox = caseTwoView(tasks, task, scannerNavi, regexDataFormat, arrayBox);
                    break;
                case 3:
                    System.out.println(ConsoleColors.BLUE + "Wybrano opcję USUŃ ZADANIE" + ConsoleColors.RESET);
                    System.out.println("Wskaż które zadanie chcesz usunąć: ");
                    scannerNavi.nextLine();
                    int remove = 0;
                    while (true) {
                        try {
                            remove = scannerNavi.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Podaj liczbę !");
                        }
                        arrayBox = caseThreeView(remove, arrayBox, scannerNavi);
                        System.out.println(ConsoleColors.BLUE + "USUNIĘTO WYBRANE ZADANIE !" + ConsoleColors.RESET);
                        break;
                    }
                    break;
                case 4:
                    helpView();
                    break;
                case 5:
                    writeToFile(scannerNavi, arrayBox);
                    check = false;
                    break;
            }
        }
    }

    public static String[] readFile(String[] arrayBox, String readLine, File source) {


        try (Scanner scannerToReadFile = new Scanner(source)) {
            while (scannerToReadFile.hasNextLine()) {
                readLine = scannerToReadFile.nextLine();
                arrayBox = Arrays.copyOf(arrayBox, arrayBox.length + 1);
                arrayBox[arrayBox.length - 1] = readLine;

            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się otworzyć pliku");
        }
        return arrayBox;
    }

    public static void menuView() {
        System.out.println(ConsoleColors.YELLOW_BOLD + "---MENADŻER ZADAŃ---");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "WYBIERZ JEDNĄ Z OPCJI: " + ConsoleColors.RESET);
        System.out.println("1. PODGLĄD ZADAŃ");
        System.out.println("2. DODAJ  ZADANIE");
        System.out.println("3. USUŃ ZADANIE");
        System.out.println(ConsoleColors.GREEN + "4. POMOC" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED_BOLD + "5. WYJŚCIE" + ConsoleColors.RESET);
    }

    public static void caseFirstView(String[] arrayBox) {
        System.out.println(ConsoleColors.BLUE + "Wybrano opcje PODGLĄD ZADAŃ" + ConsoleColors.RESET);
        for (String line : arrayBox)
            System.out.println(line);
    }

    public static String[] caseTwoView(String[] tasks, String task, Scanner scannerNavi, String regexDataFormat, String[] arrayBox) {
        System.out.println(ConsoleColors.BLUE + "Wybrano opcję DODAJ ZADANIE" + ConsoleColors.RESET);
        System.out.println("Wpisz opis zadania które chcesz dopisać do listy");
        scannerNavi.nextLine();
        task = scannerNavi.nextLine();
        tasks = maxArr(tasks, task);
        System.out.println("Wpisz date FORMAT: ROK-MIESIĄC-DZIEŃ");
        task = scannerNavi.nextLine();
        while (true) {
            if (!task.matches(regexDataFormat)) {
                System.out.println("Niepoprawny format, spróbuj ponownie !");
                task = scannerNavi.nextLine();
            } else {
                break;
            }
        }
        tasks = maxArr(tasks, task);
        System.out.println("Wpisz czy zadanie jest ważne-true, mniej ważne-false !");
        task = scannerNavi.nextLine();
        tasks = maxArr(tasks, task);
        String join = String.join(", ", tasks);
        arrayBox = Arrays.copyOf(arrayBox, arrayBox.length + 1);
        arrayBox[arrayBox.length - 1] = join;
        System.out.println(ConsoleColors.BLUE + "DODANO NOWE ZADANIE !" + ConsoleColors.RESET);
        System.out.println(join);
        return arrayBox;
    }

    public static String[] maxArr(String[] tasks, String task) {
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = task;
        return tasks;
    }

    public static String[] caseThreeView(int remove, String[] arrayBox, Scanner scannerNavi) {
        if (remove == 0 || remove > arrayBox.length) {
            System.out.println("Zadanie o podanej liczbie nie istnieje, spróbuj ponownie");
            scannerNavi.nextLine();
        } else {
            int realRemove = remove - 1;
            String[] newArrBox = new String[arrayBox.length - 1];
            int index = 0;
            for (int i = 0; i < arrayBox.length; i++, index++) {
                if (i < realRemove) {
                    newArrBox[i] = arrayBox[index];
                } else if (i == realRemove) {

                } else {
                    newArrBox[i - 1] = arrayBox[index];
                }
            }
            arrayBox = newArrBox;

        }
        return arrayBox;
    }

    public static void helpView() {
        System.out.println(ConsoleColors.BLUE + "Wybrałeś opcję POMOC " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT +
                "---MENAGER ZADAŃ---" + ConsoleColors.RESET + "\n" +
                "Prosty program służący do zapisywania" + "\n" +
                "zadań, z jego pomocą dodasz nowe zadanie," + "\n" +
                "usuniesz niepotrzebne oraz podejrzysz już" + "\n" +
                "istniejące, zapisywane na pliku." + "\n" + ConsoleColors.BLUE +
                "Nawigowanie odbywa się za pomocą klawiszy" + "\n" + ConsoleColors.RESET +
                ConsoleColors.GREEN + "POSTĘPUJ ZGODNIE Z POLECENIAMI" + ConsoleColors.RESET);
    }

    public static void writeToFile(Scanner scannerNavi, String[] arrayBox) {
        System.out.println(ConsoleColors.RED_BOLD + "Wybrałeś opcję WYJŚĆIE" + ConsoleColors.RESET);

        System.out.println(ConsoleColors.BLUE + "DO ZOBACZENIA WKRÓTCE :)" + ConsoleColors.RESET);
        scannerNavi.close();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.csv"))) {
            for (String element : arrayBox) {
                writer.write(element);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku");

        }
    }
}











