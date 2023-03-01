////Importiamo le classi necessarie per il funzionamento del programma
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPServer {

    private static final int PORT = 4445; // porta sulla quale il server e il client si scambiano i pacchetti UDP.
    private static final int BUFFER_SIZE = 1024; // 1024 byte dimensione del buffer utilizzato per ricevere e inviare i dati tramite pacchetti UDP.

    public static void main(String[] args)  throws Exception {
        try {

        // creo un array di byte di dimensione BUFFER_SIZE.
        byte[] buffer = new byte[BUFFER_SIZE];

       //creo un nuovo socket UDP del server e lo assegna alla variabile serverSocket. 
       //Questo permette di inviare e ricevere pacchetti dati attraverso il socket.
        DatagramSocket serverSocket = new DatagramSocket(PORT);

        System.out.println("Ciao Nassim sono online");
        //In questo loop, il server rimane in ascolto di eventuali pacchetti inviati dal client. 
        //Quando riceve un pacchetto, il server elabora i dati ricevuti dal client, invia 
        //una risposta e torna in attesa di un altro pacchetto.
        while (true) {

            // Creazione del datagramma per ricevere i dati dal client
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);

            // Elaborazione dei dati ricevuti dal client
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Dati ricevuti dal client: " + receivedData);

            // Creazione del datagramma per inviare i dati al client
            InetAddress clientAddress = receivePacket.getAddress(); // recupero l'indirizzo IP del client che ha inviato i dati
            int clientPort = receivePacket.getPort(); // recupero la porta sulla quale il client ha inviato i dati
            
            //creo una stringa di risposta da inviare al client , 
            //la converto in un array di byte utilizzando il metodo getBytes(), 
            //quindi creo un nuovo pacchetto utilizzando l'indirizzo IP e la porta del client come destinazione. 
            //Infine, il pacchetto viene inviato al client utilizzando il metodo send() dell'oggetto DatagramSocket.
            String responseData = "Ciao dal server!";
            byte[] responseBytes = responseData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);

            // Invio dei dati al client
            serverSocket.send(sendPacket);

        }
        
        } catch (UnknownHostException e) {
            System.err.println("Errore DNS!");
        } catch (SocketException e) {
            System.err.println("Errore nel socket!");
        } catch (IOException e) {
            System.err.println("Errore di I/O!");
        } 
    
        
    }
}
