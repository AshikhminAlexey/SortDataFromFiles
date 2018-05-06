public class Main {
    public static void main(String[] args) {

        try {
            Settings.Instance.GetSettingsFromArgs(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
			new Processing(new InsertSortAlgorithm(Settings.Instance), new ConvertArrayToInt(Settings.Instance), Settings.Instance).StartProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
