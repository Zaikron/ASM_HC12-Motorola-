
package com.mycompany.asm_to_lst;

/**
 *
 * @author AnthonySandoval
 */
public class Converter {
    
    public Converter(){
        
    }
    
    public int hexToDecimal(String hex){
        int dec = Integer.parseInt(hex, 16);
        return (int)dec;
    }
    
    public String decimalToHex(int dec){
        return Integer.toHexString(dec);
    }
    
    public String octalToHex(String oct){
        //return Integer.toOctalString(oct);
        
        int dec = Integer.parseInt(oct, 8);
        return decimalToHex((int)dec);
    }
    
    public String binaryToHex(String bin){
        int dec = Integer.parseInt(bin, 2);
        return decimalToHex((int)dec);
    }
    
    public int binaryToDec(String bin){
        return Integer.parseInt(bin, 2);
    }
    
    public int octalToDec(String oct){
        return Integer.parseInt(oct, 8);
    }
}
