////Importiamo le classi necessarie per il funzionamento del programma
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPServer {

    private static final int PORT = 4445;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws Exception {

        byte[] buffer = new byte[BUFFER_SIZE];

        // Creazione del socket UDP
        DatagramSocket serverSocket = new DatagramSocket(PORT);

        while (true) {

            // Creazione del datagramma per ricevere i dati dal client
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);

            // Elaborazione dei dati ricevuti dal client
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Dati ricevuti dal client: " + receivedData);

            // Creazione del datagramma per inviare i dati al client
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String responseData = "Ciao dal server!";
            byte[] responseBytes = responseData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);

            // Invio dei dati al client
            serverSocket.send(sendPacket);

        }
    }
}
