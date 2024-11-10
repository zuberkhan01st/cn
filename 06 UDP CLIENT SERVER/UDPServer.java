import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // Create a DatagramSocket to listen on a specific port
            int port = 9876;
            DatagramSocket serverSocket = new DatagramSocket(port);

            System.out.println("UDP Server is running on port " + port);

            byte[] receiveData = new byte[1024];

            while (true) {
                // Create a DatagramPacket to receive data
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive data from the client
                serverSocket.receive(receivePacket);

                // Extract the received message and its source address and port
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String clientAddress = receivePacket.getAddress().getHostAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received from " + clientAddress + ":" + clientPort + ": " + message);

                // You can process the received message here

                // Respond to the client (optional)
                String response = "Server received: " + message;
                byte[] responseData = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
