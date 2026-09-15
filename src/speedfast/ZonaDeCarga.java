package speedfast;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    private BlockingQueue<Pedido> pedidosPendientes;

    public ZonaDeCarga() {
        pedidosPendientes = new LinkedBlockingQueue<>();
        System.out.println("[Zona de carga inicializada]\n");
    }

    public synchronized void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccionEntrega() + "\n");
    }

    public synchronized Pedido retirarPedido() {
        Pedido p = pedidosPendientes.poll();
        if (p != null && p.getEstado() == EstadoPedido.PENDIENTE) {
            return p;
        }
        return null;
    }

    public boolean estaVacia() {
        return pedidosPendientes.isEmpty();
    }
}
