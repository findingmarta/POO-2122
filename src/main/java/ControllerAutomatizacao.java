public class ControllerAutomatizacao {
    public static void run(Estado estado) throws InterruptedException {
        String file = "src/main/java/automatizacao.txt";
        Menu.clearWindow();
        Automatizacao.parseFile(file,estado);
    }
}
