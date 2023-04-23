import aeroporto.*;
import listas_lineares.*;

public class App {
    public static void main(String[] args) throws Exception {

        Aeroporto oGaleao = new Aeroporto("Galeão", 2);        

        ControllerAeroporto.getInstance().gerenciar(oGaleao);

        System.out.println(oGaleao.getPistas());
    }
}
 