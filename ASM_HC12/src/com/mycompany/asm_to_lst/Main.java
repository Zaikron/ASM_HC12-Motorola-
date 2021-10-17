
package com.mycompany.asm_to_lst;

import com.mycompany.asm_to_lst.Frames.EditorFrame;

/**
 *
 * @author AnthonySandoval
 */
public class Main {
    public static void main(String[] args) {
        
        EditorFrame ef = new EditorFrame();
        ef.setVisible(true);
        ef.setLocationRelativeTo(null);
        
        Converter conv = new Converter();
        
        //System.out.println("ocTOhex: " + conv.octalToHex("34"));
        //System.out.println("decTOhex: " + conv.decimalToHex(254));
        //System.out.println("binTOhex: " + conv.binaryToHex("01111111"));
        
        //System.out.println("hexTOdec: " + conv.hexToDecimal("FF"));
    }
}
