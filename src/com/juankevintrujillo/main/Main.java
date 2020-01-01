package com.juankevintrujillo.main;

import com.juankevintrujillo.view.Gui;

import javax.swing.*;

/**
 * @author juankevintr
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui();
            gui.setVisible(true);
        });
    }
}
