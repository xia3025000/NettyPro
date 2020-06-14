package com.atguigu.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 7000);
        Scanner scanner = new Scanner(System.in);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread2 start");
                    byte[] bytes = new byte[1024];
                    while (true) {
                        int read = is.read(bytes);
                        if (read == -1) {
                            break;
                        }
                        System.out.println(new String(bytes, 0, read));
                    }
                    System.out.println("Thread2 end");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            os.write(message.getBytes());
        }
        os.close();
        socket.close();
    }

}
