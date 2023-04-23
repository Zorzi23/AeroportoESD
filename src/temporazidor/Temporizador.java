package temporazidor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Temporizador {

    //#region Atributos/Constantes
    
    private ScheduledExecutorService executor;
    
    //#endregion

    //#region Métodos Publicos
    
    public void iniciar() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::mensagem, 0, 1, TimeUnit.SECONDS);
    }
    
    public void parar() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public void mensagem() {
        System.out.println("Passou um segundo.");
    }

    //#endregion
}
