
# Laboratory work 1
Performed by **Schidu Luca**

Language of choice: **Java**

---

* send ``UDP`` or/and ``TCP`` messages

**Server.java** - accepts a ``TCP`` connection and waits for incoming messages from the connected ``client``

```java
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

```java
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
-----
- port scanning of a given IP (with possibility of including a range of ports; ex: 1-100)

```java
public static void main(String[] args) {
        long portCount = IntStream.range(from, to).filter(i -> {
            try {
                Socket socket = new Socket("localhost", i);
                return true;
            } catch (IOException e) {
                return false;
            }
        }).count();

        System.out.println(portCount);
    }

```
-----
- send file over network (image)

ServerFile.java
```java
 public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);

        Socket socket = serverSocket.accept();

        OutputStream outputStream = socket.getOutputStream();
        BufferedImage image = ImageIO.read(new File("/home/lschidu/Downloads/rick.jpg"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.flush();
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();

    }
 ```
 
 ClientFile.java
 
 ```java
  public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        InputStream inputStream = socket.getInputStream();

        byte[] sizeAr = new byte[4];
        inputStream.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

        System.out.println(size);
        byte[] imageAr = new byte[size];
        inputStream.read(imageAr);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

        ImageIO.write(image, "jpg", new File("/home/lschidu/Desktop/rick.jpg"));

    }
 
 ```
        

----

- send messaged on fixed intervals

ClientFixedInterval.java
```java
public static void main(String[] args) {

        ObjectOutputStream writer = null;
        try {
            Socket socket = new Socket("localhost",  8080);
            writer = new ObjectOutputStream(socket.getOutputStream());

            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                writer.writeUTF("Hello");
                writer.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

```
