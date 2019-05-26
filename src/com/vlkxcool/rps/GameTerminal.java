package com.vlkxcool.rps;

import com.vlkxcool.rps.type.HandShape;

import java.util.Scanner;
import java.util.function.Consumer;

class GameTerminal {

    private static final String QUIT_COMMAND = "q";

    void showMessage(String message) {
        System.out.println(message);
    }

    void observeHumanCommands(Consumer<HandShape> humanChoiceObserver) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String input = in.nextLine();

            if (QUIT_COMMAND.equals(input)) {
                break;
            }

            try {
                humanChoiceObserver.accept(HandShape.getByAlias(input));
            } catch (Exception e) {
                System.out.println("Invalid choice");
            }
        }

        in.close();
    }
}
