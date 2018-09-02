package com.villegas.juliana.taller1_julivillegas_controlandroid;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Task extends AsyncTask<String, String, Void> {
    DatagramSocket socket;
    DatagramPacket pack;
    final int PUERTO = 5000;
    InetAddress ip;


    @Override
    protected void onPreExecute() {
        try {

            socket = new DatagramSocket();
            ip = InetAddress.getByName("192.168.1.107");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected Void doInBackground(String... params) {
        enviar(params[0]);
        return null;
    }


    public void enviar(String msj) {
        byte[] baits = msj.getBytes();
        pack = new DatagramPacket(baits, baits.length, ip, PUERTO);
        try {
            socket.send(pack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
