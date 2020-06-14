package com.atguigu.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6666);
        Scanner scanner = new Scanner(System.in);
        OutputStream os = socket.getOutputStream();
        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            os.write(message.getBytes());
        }
        os.close();
        socket.close();
    }

}
