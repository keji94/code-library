package com.keji.codelibrary.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 阻塞io
 *
 * Copyright (c) 2019 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author qinjiao
 * @since 2019/3/7$
 */
public class BlokingIo {

    public void serve(int port) throws IOException {
        //将服务器绑定到指定端口
        final ServerSocket socket = new ServerSocket(port);

        try {
            while (true) {
                //接受连接
                final Socket clientSocket = socket.accept();
                System.out.println("Accepter connection from" + clientSocket);
                new Thread(() -> {
                    OutputStream out;
                    try {
                        out = clientSocket.getOutputStream();
                        out.write("Hi\r\n".getBytes(Charset.forName("UTF-8")));
                        out.flush();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            //ignore on close
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
