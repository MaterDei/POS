/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pos;


import java.io.IOException;
import java.util.Observer;
import org.json.JSONException;



/**
 *
 * @author JAVIER
 */
public class PrincipalCtrl {
    
    Observer vista;
    
    PrincipalCtrl(Observer o){
        vista=o;
    }
    
    void facturar(int codigo, int h, int perro, int cerv) throws IOException, JSONException,Exception{
        
    Factura facturita = new Factura();
    facturita.addObserver(vista);
    facturita.facturar(codigo, h, perro, cerv);
    
     
    
    
    }

public void setObserver(Observer ventana) {
        vista = ventana;   }
    
    
}
