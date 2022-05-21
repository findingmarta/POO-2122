public class Controller {
    public static void run() throws InterruptedException {
        Estado estado = new Estado();

        while (true) {
            int opcao = -1;
            while (opcao < 0 || opcao > 7) opcao = Menu.MenuInicial();
            switch (opcao) {
                case 1->ControllerEstado.run(estado);
                case 2-> ControllerCasa.run(estado);
                case 3-> {
                    if(!estado.getFornecedores().isEmpty())
                        ControllerFornecedores.run(estado);
                    else {
                        Menu.erros(23);
                        Thread.sleep(3000);
                    }
                }
                case 4-> {
                    if(!estado.getCasas().isEmpty() && !estado.getFornecedores().isEmpty())
                        ControllerSimulacao.run(estado);
                    else {
                        Menu.erros(23);
                        Thread.sleep(3000);
                    }
                }
                case 5-> {
                    if(!estado.getCasas().isEmpty() && !estado.getFornecedores().isEmpty())
                        ControllerAutomatizacao.run(estado);
                    else {
                        Menu.erros(23);
                        Thread.sleep(3000);
                    }
                }
                case 0-> System.exit (0);
                default -> Controller.run ();
            }
        }
    }
}
