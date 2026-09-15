package speedfast;

public class Repartidor implements Runnable {
    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public ZonaDeCarga getZonaDeCarga() { return zonaDeCarga; }
    public void setZonaDeCarga(ZonaDeCarga zonaDeCarga) { this.zonaDeCarga = zonaDeCarga; }

    @Override
    public void run() {
        while (!zonaDeCarga.estaVacia()) {
            Pedido pedido = zonaDeCarga.retirarPedido();
            if (pedido != null) {
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                System.out.println("[Repartidor - " + nombre + "] Retirando pedido #" + pedido.getId() + " ...");
                System.out.println("[Repartidor - " + nombre + "] Estado: EN_REPARTO");

                try {
                    Thread.sleep(2000); // Simula tiempo de entrega
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                pedido.setEstado(EstadoPedido.ENTREGADO);
                System.out.println("[Repartidor - " + nombre + "] Entregando pedido #" + pedido.getId() + " ...");
                System.out.println("[Repartidor - " + nombre + "] Estado: ENTREGADO\n");
            }
        }
    }
}
