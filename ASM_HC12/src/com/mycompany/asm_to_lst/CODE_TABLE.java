
package com.mycompany.asm_to_lst;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AnthonySandoval
 */
public class CODE_TABLE {
    
    List<MNEMONICO> table; //Tabla de los mnemonicos
    List<DIRECTIVE> directives; //Lista de las etiquetas
    List<LABEL> labels;
    Functionality_DIRECT fDirect = new Functionality_DIRECT();
    Functionality_MNEM fMnem;
    int i = 0; //Indice global para recorrer el archivo
    String hexOrg = "0x0"; //Cadena para definir la direccion de ORG
    int org = 0; //valor para incrementar la direccion de ORG
    String operatorSt = "";
    String opNotSimbol = "";
    
    public CODE_TABLE(){
        table = new ArrayList<MNEMONICO>();
        directives = new ArrayList<DIRECTIVE>();
        labels = new ArrayList<LABEL>();
    }
    
    public void initTable(){
        //table.add(new MNEMONICO("ORG", " ", 0, " ", " "));
        //table.add(new MNEMONICO("END", " ", 0, " ", " "));
        
        table.add(new MNEMONICO("ABA", "INH", 2, "18 06", "18 06"));
        //table.add(new MNEMONICO("ABX", "IDX", 2, "1AE5"));
        //table.add(new MNEMONICO("ABY", "IDX", 2, "19ED"));
        
        table.add(new MNEMONICO("ADCA", "IMM", 2, "89 ii", "89"));
        table.add(new MNEMONICO("ADCA", "DIR", 2, "99 dd", "99"));
        table.add(new MNEMONICO("ADCA", "EXT", 3, "B9 hh ll", "B9"));
       
        table.add(new MNEMONICO("ADCB", "IMM", 2, "C9 ii", "C9"));
        table.add(new MNEMONICO("ADCB", "DIR", 2, "D9 dd", "D9"));
        table.add(new MNEMONICO("ADCB", "EXT", 3, "F9 hh ll", "F9"));
        
        table.add(new MNEMONICO("ADDA", "IMM", 2, "8B ii", "8B"));
        table.add(new MNEMONICO("ADDA", "DIR", 2, "9B dd", "9B"));
        table.add(new MNEMONICO("ADDA", "EXT", 3, "BB hh ll", "BB"));
        
        table.add(new MNEMONICO("ADDB", "IMM", 2, "CB ii", "CB"));
        table.add(new MNEMONICO("ADDB", "DIR", 2, "DB dd", "DB"));
        table.add(new MNEMONICO("ADDB", "EXT", 3, "FB hh ll", "FB"));
        
        table.add(new MNEMONICO("ADDD", "IMM", 3, "C3 jj kk", "C3"));
        table.add(new MNEMONICO("ADDD", "DIR", 2, "D3 dd", "D3"));
        table.add(new MNEMONICO("ADDD", "EXT", 3, "F3 hh ll", "F3"));
        
        table.add(new MNEMONICO("BNE", "REL", 2, "26 rr", "26"));
        table.add(new MNEMONICO("LBNE", "REL", 4, "18 26 qq rr", "18 26"));
        table.add(new MNEMONICO("BEQ", "REL", 2, "27 rr", "27"));
        table.add(new MNEMONICO("LBEQ", "REL", 4, "18 27 qq rr", "18 27"));
        table.add(new MNEMONICO("DBEQ", "REL", 3, "04 lb rr", "04"));
    }
    
    public void initLabels(){
        directives.add(new DIRECTIVE("ORG"));
        directives.add(new DIRECTIVE("END"));
        directives.add(new DIRECTIVE("EQU"));
        directives.add(new DIRECTIVE("START"));
        directives.add(new DIRECTIVE("DC.B"));
        directives.add(new DIRECTIVE("DC.W"));
        directives.add(new DIRECTIVE("FCC"));
        directives.add(new DIRECTIVE("FCB"));
        directives.add(new DIRECTIVE("BSZ"));
        directives.add(new DIRECTIVE("ZMB"));
        directives.add(new DIRECTIVE("FILL"));
    }
    
    public String transformToLST(String text, String viewMode, boolean recursiveIndicator){
        
        String currentWord = ""; //palabra actual, mnemonico
        String transformedText = ""; //Texto transformado
        org = 0x0;
        //Se recorre todo el texto
        for(i = 0; i < text.length(); i++){
            //Si se encuentra un caracter diferente a espacio
            if(text.charAt(i) != ' ' && text.charAt(i) != 10){
                //Se empiezan a concatenar los caracteres en la variable currentWord
                currentWord += text.charAt(i);
                //Para que lea la ultima palabra en caso de que no halla salto o espacio
                //Si no lo hay se saldria el programa de for y ya no tomaria en cuenta la ultima palabra
                if(i == text.length()-1){
                    text += "\n";
                }
            }else{ //Si encuentra un espacio o salto significa que se termino la palabra
                //Se comprueba que la palabra sea un mnemonico
                System.out.println("->> " + currentWord);
                if(isMnemonico(currentWord)){
                    transformedText = setMnemonico(text, viewMode, currentWord, transformedText, "");
                }else if(isDirective(currentWord)){
                    transformedText = setDirective(text, viewMode, currentWord, transformedText, "");
                }else if(currentWord.length() != 0){//Entonces sera una etiqueta
                    transformedText = setLabel(text, viewMode, currentWord, transformedText);
                }
                //Reset
                currentWord = "";
            }
        }
        
        if(recursiveIndicator){
            transformedText = "";
            transformedText = transformToLST(text, viewMode, false);
        }
        
        return transformedText;
    }
    
    
    public String setMnemonico(String text, String viewMode, String currentWord, String transformedText, String et){
        
        fMnem = new Functionality_MNEM(table);
        CodeLine cl = fMnem.findMNEM(text, currentWord, i, org, labels, et);

        if(viewMode.equals("LST")){
            //Agrego el nuevo texto transformadon
           transformedText += cl.getContlocString() + " " + cl.getMnemonicoObj().mnemonico + " " 
                   + cl.getOperator() + " " + cl.getMnemonicoObj().dirMode + " (LI=" + 
                   cl.getMnemonicoObj().instructionLong + ")" + "\n"; 
        }else if(viewMode.equals("CONTLOC")){
            transformedText += cl.getContlocString() + "\t" + cl.getMnemonicoObj().operationCodeOnly + " " +
                    getOnlyValue_OPCode(cl.getOperator()) + "\t" + et + "\t" + cl.getMnemonicoObj().mnemonico +
                    "\t" + cl.getOperatorString()+ "\n";
        }

        //Se le suman los bytes utilizados del mnemonico
        i = cl.getCurrentIndex();
        org += cl.getMnemonicoObj().instructionLong;
        
        return transformedText;
    }
    
    public String setDirective(String text, String viewMode, String currentWord, String transformedText, String et){
        CodeLine cl = fDirect.findDirective(currentWord, text, i, org, labels, et);
        String formatCONTLOC = Integer.toHexString(org);

        operatorSt = "$" + formatCONTLOC;

        formatCONTLOC = getFormatedHex(formatCONTLOC, 2);
        if(viewMode.equals("LST")){
            transformedText += formatCONTLOC + " " + cl.getMnemORdir() + " " + 
                cl.getOperator() + "\n";
        }else if(viewMode.equals("CONTLOC")){
            transformedText += formatCONTLOC + "\t" + cl.getCOP() + "\t" + et + "\t"
                + cl.getMnemORdir() + "\t" + cl.getOperator() + "\n";
        }
        
        formatCONTLOC = Integer.toHexString(cl.getContloc());
        i = cl.getCurrentIndex();
        org = cl.getContloc();

        return transformedText;
    }
    
    public String getOnlyValue_OPCode(String value){
        if(value.length() != 0){
            if(isSpecialCaracter(value.charAt(1))){
                return value.substring(2, value.length());
            }else if(isSpecialCaracter(value.charAt(0))){
                return value.substring(1, value.length());
            }else{
                return value;
            }
        }else{
            return "";
        } 
    }
    
    public boolean isSpecialCaracter(char c){
        if(c == '$' || c == '%' || c == '@' || c == '#'){
            return true;
        }
        return false;
    }
    
    public String getFormatedHex(String hex, int bytes){
        
        if(bytes == 2){
            if(hex.length() == 3){
                return ("0" + hex);
            }else if(hex.length() == 2){
                return ("00" + hex);
            }else if(hex.length() == 1){
                return ("000" + hex);
            }else{
                return hex;
            }
        }else if(bytes == 1){
            if(hex.length() == 1){
                return ("0" + hex);
            }else{
                return hex;
            }
        }
        return "00";//pendiente
    }
    
    public String setLabel(String text, String viewMode, String currentWord, String transformedText){
        String wordBefore = getWord(text);

        if(isMnemonico(wordBefore)){
            transformedText = setMnemonico(text, viewMode, wordBefore, transformedText, currentWord);
        }else if(isDirective(wordBefore)){
            transformedText = setDirective(text, viewMode, wordBefore, transformedText, currentWord);
        }
        return transformedText;
    }
    
    
    
   
    //Comprobacion de si existe la palabra en los mnemonicos
    public boolean isMnemonico(String word){
        for(int i = 0; i < table.size(); i++){
            if(table.get(i).mnemonico.equals(word)){
                return true;
            }
        }
        return false;
    }
    
    
    //Comprobacion de si existe la palabra en las directivas
    public boolean isDirective(String word){
        for(int i = 0; i < directives.size(); i++){
            if(directives.get(i).label.equals(word)){
                return true;
            }
        }
        return false;
    }
    
    
    
    public String getWord(String text){
        String word = "";
        
        for(i = i+1; i < text.length(); i++){
            if(text.charAt(i) != ' ' && text.charAt(i) != 10){
               word += text.charAt(i); 
            }else{
                return word;
            }
        }
        
        return word;
    }
    
    
    public String getTABSIM(){
        String text = "";
        for(int j = 0; j < labels.size(); j++){
            text += (labels.get(j).label + "\t" + labels.get(j).direction + "\n");
            //System.out.println("Label: " + labels.get(j).label + "\t" + "CONTLOC: " + labels.get(j).direction);
        }
        return text;
    }
    
}
