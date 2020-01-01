package com.juankevintrujillo.main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juankevintr
 */
public class Main {

    public static void main(String[] args) {
	Controller ctrl = new Controller();
	try {
	    ctrl.execute();
	} catch (Exception ex) {
	    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
