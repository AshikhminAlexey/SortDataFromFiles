import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Settings.Instance.GetSettingsFromArgs(args);

			new Processing(new InsertSortAlgorithm(Settings.Instance), new ConvertArrayToInt(Settings.Instance), Settings.Instance).StartProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }

        promptEnterKey();
    }

    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to close console...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
