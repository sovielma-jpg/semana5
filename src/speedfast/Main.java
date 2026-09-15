package speedfast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // Agregar pedidos
        zonaDeCarga.agregarPedido(new Pedido(1, "Santiago Centro"));
        zonaDeCarga.agregarPedido(new Pedido(2, "Providencia"));
        zonaDeCarga.agregarPedido(new Pedido(3, "Ñuñoa"));
        zonaDeCarga.agregarPedido(new Pedido(4, "Recoleta"));
        zonaDeCarga.agregarPedido(new Pedido(5, "Las Condes"));

        // Crear repartidores
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new Repartidor("Juan", zonaDeCarga));
        executor.execute(new Repartidor("Camila", zonaDeCarga));
        executor.execute(new Repartidor("Pedro", zonaDeCarga));

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Espera a que terminen todos los hilos
        }

        System.out.println("[Zona de carga vacía]");
        System.out.println("✅ Todos los pedidos han sido entregados correctamente.");
    }
}
