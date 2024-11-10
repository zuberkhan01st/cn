import socket

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_address = ('localhost', 12345)

client_socket.connect(server_address)
print("Connected to the server.")

while True:
    try:
    
        print("1. Send a message")
        print("2. Exit")
        choice = input("Enter your choice: ")

        if choice == "1":
        
            message = input("Client: ")
            client_socket.send(message.encode('utf-8'))

            response = client_socket.recv(1024).decode('utf-8')
            print(f"Server: {response}")
        elif choice == "2":
            print('Good Bye!')
            break
        else:
            print("Invalid choice. Please try again.")

    except KeyboardInterrupt:
        break
    
client_socket.close()
