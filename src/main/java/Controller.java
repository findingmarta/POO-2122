public class Controller {
    public static void run() throws InterruptedException {
        Estado estado = new Estado();

        while (true) {
            int opcao = -1;
            while (opcao < 0 || opcao > 7) opcao = Menu.MenuInicial();
            switch (opcao) {
                case 1->ControllerEstado.run(estado);
                case 2-> ControllerCasa.run(estado);
                case 3-> ControllerFornecedores.run(estado);
                case 4-> ControllerSimulacao.run (estado);
                case 0-> System.exit (0);
            }
        }
    }
}
