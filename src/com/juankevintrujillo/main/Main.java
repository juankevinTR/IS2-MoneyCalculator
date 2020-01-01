package com.juankevintrujillo.main;

import com.juankevintrujillo.view.Gui;

import javax.swing.*;
import java.io.IOException;

/**
 * @author juankevintr
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui();
            gui.setVisible(true);

            gui.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                }
            });
        });
    }
}
