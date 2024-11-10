import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // Create a DatagramSocket for the client
            DatagramSocket clientSocket = new DatagramSocket();

            // Server address and port
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            String message = "Hello, UDP Server!";
            byte[] sendData = message.getBytes();

            // Create a DatagramPacket to send data to the server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Send the data to the server
            clientSocket.send(sendPacket);

            // Receive a response from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + response);

            // Close the socket
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
