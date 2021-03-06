/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pos;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author JAVIER
 */
public class Factura extends Observable {
    
    private String total;
    private int bandera;
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTotal() {
        return total;
    }

    public int getBandera() {
        return bandera;
    }

    public void setBandera(int bandera) {
        this.bandera = bandera;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public void Factura(){}
    
    
    public void facturar (int codigo, int h, int perro, int cerv) throws JSONException, MalformedURLException, IOException{
        
        super.setChanged(); 
        JSONObject pedido = new JSONObject();
        String[] productos = new String[]{"Hamburguesa", "Perro",  "Cerveza"};


        int[] cantidades = new int[]{h, perro, cerv};

        pedido.put("cantidades", cantidades);
        pedido.put("productos", productos);


        URL address = new URL(
                "http://localhost:8080/InventarioFacturacion/resources/Facturar?codigoFactura=Perro&productos=" + pedido.toString());
        URLConnection urlConnection = address.openConnection();
        try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()))) {
            String responseLine;
            String ayuda = "";
            while ((responseLine = in.readLine()) != null) {
                ayuda = ayuda + responseLine;
                System.out.println(responseLine);
            }
            in.close();

            try {
                JSONObject recibido = new JSONObject(ayuda);
                
                    
               
                    JSONArray precio = recibido.getJSONArray("precio");
                    JSONArray nombreInsumo = recibido.getJSONArray("nombreInsumo");
                    
                    System.out.println(precio.getString(0));
                    
                    this.setTotal(precio.getString(0));
                    
                    System.out.println("precio " + total);
                    
                    //setChanged();
                    bandera=0;
                    
                    notifyObservers();
                    
                    
                    
                    if(nombreInsumo.length()!=0){
                    bandera=1;
                        
                    System.out.println("Necesario notificar...");
                    }
                
                             
                
            } catch (Exception ex) {
                 
                bandera=2;
                System.out.println("Se produjo algún error.....");
                
                
            }




        }
    
    }
    
}
