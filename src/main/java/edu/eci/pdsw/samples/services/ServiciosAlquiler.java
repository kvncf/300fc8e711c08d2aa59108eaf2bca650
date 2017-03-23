package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author 2106913
 */
public interface ServiciosAlquiler {

    
    public abstract int valorMultaRetrasoxDia();
    
    public abstract Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler;

    /**
     * @obj Consultar los items que tenga en su poder un cliente
     * @param idcliente identificador del cliente
     * @return el litado de detalle de los items rentados por el cliente
     * identificado con 'idcliente'
     * @throws ExcepcionServiciosAlquiler si el cliente no esta registrado
     */
    public abstract List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler;

    public abstract List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler;

    public abstract Item consultarItem(int id) throws ExcepcionServiciosAlquiler;

    /**
     * @obj consultar los items que estan disponibles para alquiler
     * @return el listado de items disponibles
     */
    public abstract List<Item> consultarItemsDisponibles();

    /**
     * @obj consultar el valor de la multa del alquiler, dado el id del item
     * alquilado hasta la fecha dada como parametro
     * @param iditem el codigo del item alquilado
     * @param fechaDevolucion la fecha de devolucion del item
     * @return la multa en funcion del numero de dias de retraso. Si el item se
     * entrega en la fecha exacta de entrega, o antes, la multa sera cero.
     * @throws ExcepcionServiciosAlquiler si el item no existe o no esta
     * actualmente alquilado
     */
    public abstract long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler;

    public abstract TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler;

    public abstract List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler;

    /**
     * @obj rejistrar el alkiler de un item
     * @pre numdias >=1
     * @param date fecha de rejistro del alquiler
     * @param docu identificacion de a quien se le cargara el alquiler
     * @param item el identificador del item a alquilar
     * @param numdias el numero de dias que se le prestara el item
     * @pos el item ya no debe estar disponible, y debe estar asignado al
     * cliente
     * @throws ExcepcionXX si el identificador no corresponde con un item, o si
     * el mismo ya esta alquilado
     */
    public abstract void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler;

    public abstract void registrarCliente(Cliente p) throws ExcepcionServiciosAlquiler;

    /**
     * @obj registrar la devolucion de un item
     * @param iditem el item a regresar
     * @pos el item se enuentra disponible para el alquiler y el usuario ya no
     * lo tiene dentro de sus elementos rentados
     * @throws ExcepcionServiciosAlquiler si el item no existe o no se encuentra
     * alquilado
     */
    public abstract void registrarDevolucion(int iditem) throws ExcepcionServiciosAlquiler;

    /**
     * @obj consultar el costo del alquiler de un item
     * @pre numdias >=1
     * @param iditem el codigo del item
     * @param numdias el numero de dias que se va a alquilar
     * @return el costo total del alquiler, teniendo en cuesta el costo diario y
     * el numeo de dias del alquiler
     * @throws ExcepcionServiciosAlquiler si el identificador del item no existe
     */
    public abstract long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler;

    public abstract void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler;

    public abstract void registrarItem(Item i) throws ExcepcionServiciosAlquiler;

    public abstract void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler;

}
