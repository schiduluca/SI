
# Laboratory work 1
Performed by **Schidu Luca**

Language of choice: **Java**

---

* send ``UDP`` or/and ``TCP`` messages

**Server.java** - accepts a ``TCP`` connection and waits for incoming messages from the connected ``client``

```
 public static void main(String[] args) {

        ObjectInputStream reader;
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            Socket accept = serverSocket.accept();
            reader = new ObjectInputStream(accept.getInputStream());
            System.out.println("User connected.");

            listenForMessages(reader, accept);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listenForMessages(ObjectInputStream reader, Socket accept) throws IOException {
        while (!accept.isClosed()) {
            String s = reader.readUTF();
            System.out.println("Message received: " + s);
        }
    }
```


**Client.java** - connects to ``server`` and sends string messages read from the keyboard.

```
 public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ObjectOutputStream writer = null;
        try {
            Socket socket = new Socket("localhost",  8081);
            writer = new ObjectOutputStream(socket.getOutputStream());
            
            while (true) {
                String line = scanner.nextLine();
                writer.writeUTF(line);
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
