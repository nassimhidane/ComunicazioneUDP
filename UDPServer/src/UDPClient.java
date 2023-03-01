//Importiamo le classi necessarie per il funzionamento del programma
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

    //Definisco le costanti PORT, BUFFER_SIZE e SERVER_ADDRESS .
    private static final int PORT = 4445; // porta sulla quale il server e il client si scambiano i pacchetti UDP.
    private static final String SERVER_ADDRESS = "localhost"; // l'indirizzo IP del server al quale il client si connette.
    private static final int BUFFER_SIZE = 1024; // 1024 byte dimensione del buffer utilizzato per ricevere e inviare i dati tramite pacchetti UDP.

    public static void main(String[] args) throws Exception { // indica che il metodo può avere eccezioni
        try{
            
        

        // creo un array di byte di dimensione BUFFER_SIZE.
        byte[] buffer = new byte[BUFFER_SIZE];

        //creo un nuovo socket UDP del client e lo assegna alla variabile clientSocket. 
        //Questo permette di inviare e ricevere pacchetti dati attraverso il socket.
        DatagramSocket clientSocket = new DatagramSocket();

        // Creazione del datagramma per inviare i dati al server
        InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS); //recupera l'indirizzo IP del server a cui inviare i dati
        String requestData = "Ciao dal client!"; //dati da inviare
        byte[] requestBytes = requestData.getBytes();//Questa stringa viene convertita in un array di byte tramite il metodo getBytes() della classe String
        
        //Infine, viene creato un nuovo DatagramPacket con : 
        // - i dati da inviare (requestBytes)
        // - la lunghezza dei dati (requestBytes.length)
        // - l'indirizzo IP del server (serverAddress) 
        // - la porta del server a cui inviare i dati (PORT).
        DatagramPacket sendPacket = new DatagramPacket(requestBytes, requestBytes.length, serverAddress, PORT);

        // Invio dei dati al server
        clientSocket.send(sendPacket);

        // Creazione del datagramma per ricevere i dati dal server
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        clientSocket.receive(receivePacket);

        // Elaborazione dei dati ricevuti dal server
        String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Dati ricevuti dal server: " + receivedData);

        // Chiusura del socket
        

            clientSocket.close();
            
        }
            
          catch (UnknownHostException e) {
            System.err.println("Errore DNS!");
        } catch (SocketException e) {
            System.err.println("Errore nel socket!");
        } catch (IOException e) {
            System.err.println("Errore di I/O!");
        } 

}
    
}
       
