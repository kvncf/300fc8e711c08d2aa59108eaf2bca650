
package edu.eci.pdsw.samples.services;
/**
 * @author 2106913
 */
public class ExcepcionServiciosAlquiler extends Exception {

    public ExcepcionServiciosAlquiler() {
    }

    public ExcepcionServiciosAlquiler(String message) {
        super(message);
    }

    public ExcepcionServiciosAlquiler(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcionServiciosAlquiler(Throwable cause) {
        super(cause);
    }
    
}
