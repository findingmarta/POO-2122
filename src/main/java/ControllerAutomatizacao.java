public class ControllerAutomatizacao {
    public static void run(Estado estado) {
        String file = "src/main/java/automatizacao.txt";
        Menu.clearWindow();
        Automatizacao.parseFile(file,estado);
    }
}
