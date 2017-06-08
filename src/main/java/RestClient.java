import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class RestClient {
    public static void main(String[] args) {
        float[] longitudeAyer = {123, 12, 15, 16, 32, 16, 17, 72, 76, 10};
        float[] latitudeAyer = {10, 51, 71, 86, 68, 14, 61, 56, 12, 34};
        float[] longitudeHoy = {41, 61, 86, 12, 61, 71, 86, 12, 51, 16};
        float[] latitudeHoy = {61, 17, 12, 61, 13, 71, 13, 16, 13, 14};

        try {                   // DAtos para hoy

            int contador = 0;
            while(contador < 10){

                DefaultHttpClient httpClient = new DefaultHttpClient();

                HttpPost postRequest = new HttpPost(
                        "http://localhost:8080/band");

                ArrayList<NameValuePair> postParameters;

                postParameters = new ArrayList<NameValuePair>();
                Random rand = new Random();

                Integer  step = rand.nextInt(50) + 1;
                Integer bpms = rand.nextInt(39)+52;
                Integer distances = rand.nextInt(4)+1;
                Integer calories =rand.nextInt(20)+5;

                Float latitude = latitudeHoy[contador];
                Float longitude = longitudeHoy[contador];

                //latitude += 10000; <---------------- Agregar para que no se guarde la locacion
                //longitude += 10000; <---------------- Agregar para que no se guarde la locacion
                // Por lo menos debe existe una locacion pequeña Ej: (10,10) para que las lineas funcionen

                // En el servidor aparecera "No se guardo una locacion"

                postParameters.add(new BasicNameValuePair("steps",step.toString()));
                postParameters.add(new BasicNameValuePair("bpm", bpms.toString()));
                postParameters.add(new BasicNameValuePair("distance", distances.toString()));
                postParameters.add(new BasicNameValuePair("latitude", latitude.toString()));
                postParameters.add(new BasicNameValuePair("longitude", longitude.toString()));
                postParameters.add(new BasicNameValuePair("calories", calories.toString()));
                postParameters.add(new BasicNameValuePair("fecha_registro", new Date().toString()));
                postParameters.add(new BasicNameValuePair("user", "2"));

                postRequest.setEntity(new UrlEncodedFormEntity(postParameters));
                postRequest.addHeader("accept", "application/json");

                HttpResponse response = httpClient.execute(postRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                httpClient.getConnectionManager().shutdown();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++;
            }
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        try {           // <-------------------------------------- Datos para Ayer

            int contador = 0;
            while(contador < 10){

                DefaultHttpClient httpClient = new DefaultHttpClient();

                HttpPost postRequest = new HttpPost(
                        "http://localhost:8080/band");

                ArrayList<NameValuePair> postParameters;

                postParameters = new ArrayList<NameValuePair>();
                Random rand = new Random();

                Integer  step = rand.nextInt(50) + 1;
                Integer bpms = rand.nextInt(39)+52;
                Integer distances = rand.nextInt(4)+1;
                Integer calories =rand.nextInt(20)+5;

                Float latitude = latitudeAyer[contador];
                Float longitude = longitudeAyer[contador];

                //latitude += 10000; <---------------- Agregar para que no se guarde la locacion
                //longitude += 10000; <---------------- Agregar para que no se guarde la locacion
                // Por lo menos debe existe una locacion pequeña Ej: (10,10) para que las lineas funcionen

                // En el servidor aparecera "No se guardo una locacion"

                postParameters.add(new BasicNameValuePair("steps",step.toString()));
                postParameters.add(new BasicNameValuePair("bpm", bpms.toString()));
                postParameters.add(new BasicNameValuePair("distance", distances.toString()));
                postParameters.add(new BasicNameValuePair("latitude", latitude.toString()));
                postParameters.add(new BasicNameValuePair("longitude", longitude.toString()));
                postParameters.add(new BasicNameValuePair("calories", calories.toString()));
                postParameters.add(new BasicNameValuePair("fecha_registro", new Date(System.currentTimeMillis()-24*60*60*1000).toString()));
                postParameters.add(new BasicNameValuePair("user", "2"));

                postRequest.setEntity(new UrlEncodedFormEntity(postParameters));
                postRequest.addHeader("accept", "application/json");

                HttpResponse response = httpClient.execute(postRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                httpClient.getConnectionManager().shutdown();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++;
            }
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }



    }
}