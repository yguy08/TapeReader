package com.tickercash.tapereader.window;

import com.tickercash.tapereader.gui.ServerTUI;

public class ServerStarter {
 
    public static void main(String[] args) throws Exception {
        ServerTUI server = new ServerTUI();
        server.init();
    }
}