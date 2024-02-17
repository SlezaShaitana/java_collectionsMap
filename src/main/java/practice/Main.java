package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        String menu = "Введите номер, имя или команду:";
        String input;
        String name;
        String phone;
        String command = "";
        boolean itIsNumber = false;
        boolean itIsWord = false;

        while (true) {
            clean();
            System.out.println(menu);
            input = scanner.nextLine().trim();
            System.out.println(input);
            for (int i = 0; i < input.length(); i++) {
                if (Character.isDigit(input.charAt(i))) {
                    itIsNumber = true;
                } else {
                    itIsWord = true;
                }
            }
        }

    }
    public static void clean() {
        String input = "";
        String name = "";
        String phone = "";
    }
}
