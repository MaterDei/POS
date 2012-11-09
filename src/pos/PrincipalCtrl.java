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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author JAVIER
 */
public class PrincipalCtrl {
    
    void facturar(int codigo, int h, int perro, int cerv) throws IOException, JSONException,Exception{
     JSONObject pedido = new JSONObject();
        String[] productos = new String[]{"Hamburguesa","Perro", "Cerveza"};

        int[] cantidades = new int[]{h, perro, cerv};

        pedido.put("cantidades", cantidades);
        pedido.put("productos", productos);


        URL address = new URL(
                "http://localhost:8080/InventarioFacturacion/resources/Facturar?codigoFactura="+codigo+"&productos=" + pedido.toString());
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
               
                    JSONArray insumo = recibido.getJSONArray("nombreInsumo");
                    if(insumo.length()!=0){
                    System.out.println("Necesario notificar...");
                    }
                
                             
                
            } catch (Exception ex) {
                 
                System.out.println("Se produjo algún error.....");
            }




        }
    
    
    }
    
}
