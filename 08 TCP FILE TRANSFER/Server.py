import socket
import os

SERVER_IP = '127.0.0.1'
SERVER_PORT = 12345

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((SERVER_IP, SERVER_PORT))
server_socket.listen(5)

print("Server is listening for incoming connections...")

while True:
    client_socket, client_address = server_socket.accept()
    print(f"Accepted connection from {client_address}")

    filename = client_socket.recv(1024).decode()

    if os.path.isfile(filename):
        with open(filename, 'rb') as file:
            data = file.read(1024)
            while data:
                client_socket.send(data)
                data = file.read(1024)
        print(f"File '{filename}' sent successfully.")
    else:
        client_socket.send(b"File not found")
        print(f"File '{filename}' not found on the server.")
    
    client_socket.close()
