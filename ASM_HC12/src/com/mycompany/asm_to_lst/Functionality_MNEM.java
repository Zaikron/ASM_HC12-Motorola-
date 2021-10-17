
package com.mycompany.asm_to_lst;

import java.util.List;

/**
 *
 * @author AnthonySandoval
 */
public class Functionality_MNEM {
    
    int i = 0;
    int org = 0;
    String operatorSt = "";
    Converter converter = new Converter();
     List<MNEMONICO> table;
    
    public Functionality_MNEM( List<MNEMONICO> table){
        this.table = table;
    }
            
    public CodeLine findMNEM(String text, String mnem, int currentIndex, int org, List<LABEL> labels, String label){
        i = currentIndex;
        this.org = org;
        String mode = "";
        MNEMONICO mnemonico = null;
        
        if(mnem.equals("ABA")){
            mode = getMode(text, i);
            mnemonico = getMnemonico(mnem, mode);
            return ABA(text, labels, label, mnemonico);
        }else if(mnem.equals("ADCA")){
            mode = getMode(text, i);
            mnemonico = getMnemonico(mnem, mode);
            return GENERAL_AXXX(text, labels, label, mnemonico);
        }else if(mnem.equals("ADCB")){
            mode = getMode(text, i);
            mnemonico = getMnemonico(mnem, mode);
            return GENERAL_AXXX(text, labels, label, mnemonico);
        }else if(mnem.equals("ADDA")){
            mode = getMode(text, i);
            mnemonico = getMnemonico(mnem, mode);
            return GENERAL_AXXX(text, labels, label, mnemonico);
        }else if(mnem.equals("ADDB")){
            mode = getMode(text, i);
            mnemonico = getMnemonico(mnem, mode);
            return GENERAL_AXXX(text, labels, label, mnemonico);
        }else if(mnem.equals("ADDD")){
            mode = getMode(text, i);
            mnemonico = getMnemonico(mnem, mode);
            return GENERAL_AXXX(text, labels, label, mnemonico);
        }else if(mnem.equals("BNE")){
            mnemonico = getMnemonico(mnem, "REL");
            return BNE(text, labels, label, mnemonico);
        }else if(mnem.equals("LBNE")){
            mnemonico = getMnemonico(mnem, "REL");
            return LBNE(text, labels, label, mnemonico);
        }else if(mnem.equals("BEQ")){
            mnemonico = getMnemonico(mnem, "REL");
            return BEQ(text, labels, label, mnemonico);
        }else if(mnem.equals("LBEQ")){
            mnemonico = getMnemonico(mnem, "REL");
            return LBEQ(text, labels, label, mnemonico);
        }else if(mnem.equals("DBEQ")){
            mnemonico = getMnemonico(mnem, "REL");
            return DBEQ(text, labels, label, mnemonico);
        }else{
            System.out.println("Mnemonico not found");
        }
        
        return null;
    }
    

    /*** Fnciones para los mnemonicos***/
    
    private CodeLine GENERAL_AXXX(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        String currentValue = getCurrentValue(text);
        String formatValue = converter(currentValue);
        String formatCONTLOC = Integer.toHexString(org);
        formatCONTLOC = getFormatedHex(formatCONTLOC, 2);
        
        if(label.length()!= 0 && formatValue.length()!= 0){
            if(!existLabel(labels, label)){
                labels.add(new LABEL(label, "$" + getFormatedHex(formatCONTLOC, 2)));
            }
        }
        
        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setOperatorString(formatValue);
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        
        if(mnemObj.dirMode.equals("EXT")){
            formatValue.substring(1, formatValue.length());
            cl.setOperator(getFormatedHex(getOnlyValue_OPCode(formatValue), 2));
        }else{
            cl.setOperator(getFormatedHex(getOnlyValue_OPCode(formatValue), 1));
        }
        
        return cl;
    }
    
    private CodeLine ABA(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        String formatCONTLOC = Integer.toHexString(org);
        
        cl.setContloc(org);
        cl.setContlocString(getFormatedHex(formatCONTLOC, 2));
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        return cl;
    }
    
    private CodeLine BNE(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        i++;    //Para que empiece a leer la siguiente palabra
        String formatCONTLOC = Integer.toHexString(org);
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl);

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setOperator(getFormatedHex(Integer.toHexString(operatorRusult), 1));
        cl.setCurrentIndex(i);
        return cl;
    }
    
    private CodeLine BEQ(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        i++;    //Para que empiece a leer la siguiente palabra
        String formatCONTLOC = Integer.toHexString(org);
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl);

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setOperator(getFormatedHex(Integer.toHexString(operatorRusult), 1));
        cl.setCurrentIndex(i);
        return cl;
    }
    
    private CodeLine LBNE(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        i++;    //Para que empiece a leer la siguiente palabra
        String formatCONTLOC = Integer.toHexString(org);
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl);

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setOperator(getFormatedHex(Integer.toHexString(operatorRusult), 2));
        cl.setCurrentIndex(i);
        return cl;
    }
    
    private CodeLine LBEQ(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        i++;    //Para que empiece a leer la siguiente palabra
        String formatCONTLOC = Integer.toHexString(org);
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl);

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setOperator(getFormatedHex(Integer.toHexString(operatorRusult), 2));
        cl.setCurrentIndex(i);
        return cl;
    }
    
    private CodeLine DBEQ(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        String reg = " ";
        String objectCode = "";
        i++;
        reg = getReg(text);
        
        if(text.charAt(i+1) == ' '){
            i++;
        }
        cl.setOperatorString(reg + ", ");
        
        String formatCONTLOC = Integer.toHexString(org);
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl);
        objectCode = getObjectCode(operatorRusult, reg);

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setOperator(objectCode + " " + getFormatedHex(Integer.toHexString(operatorRusult), 1));
        cl.setCurrentIndex(i);

        return cl;
    }
    
    /*** Funciones de utilidad ***/
    
    private int getOperatorOrLabel(String text, List<LABEL> labels, MNEMONICO mnemObj, CodeLine cl){
        String currentValue = getCurrentValue(text);
        int tabsimIndex = isLabel(labels, currentValue);
        int tabsimDirection_Dec = 0;
        int operatorRusult = 0;
        String directionTabsim = "";
        String hexTabsim = "";
        
        cl.setOperatorString(cl.getOperatorString() + currentValue);
        if(tabsimIndex != -1){
            directionTabsim = labels.get(tabsimIndex).direction;
            hexTabsim = "0x" + directionTabsim.substring(1, directionTabsim.length());
            //Le asigno la direccion al entero para poder sumarle los bytes
            tabsimDirection_Dec = Integer.decode(hexTabsim);
            operatorRusult = (tabsimDirection_Dec -(org + mnemObj.instructionLong));
            /*System.out.println("---> " + operatorRusult + " = " + tabsimDirection_Dec + 
                    " - " + (org + mnemObj.instructionLong));*/
            return operatorRusult;
        }else{
            //Si no es una etiqueta, si no se encuentra en el tabsim
            return -1;
        }
    }
    
    private String getReg(String text){

        String reg = "";
        for(i = i; i < text.length(); i++){
            if(text.charAt(i) != ','){
               reg += text.charAt(i); 
            }else{
                i+=2;//se aumenta para pasar a despues de la coma
                return reg;
            }
        }
        return reg;
    }
    
    private String getObjectCode(int dir, String reg){
        if(dir < 0){
            if(reg.equals("A")){
               return "10"; 
            }else if(reg.equals("B")){
               return "11"; 
            }else{
                return "F";
            }
        }else if(dir > 0){
            if(reg.equals("A")){
               return "00"; 
            }else if(reg.equals("B")){
               return "01"; 
            }else{
               return "F";
            }
        }else{
            return "F";
        }
    }
    
    //Se comprueba que el valor actual, despues de mnemonico, sea una etiqueta del tabsim
    private int isLabel(List<LABEL> labels, String currentValue){
        for(int j = 0; j < labels.size(); j++){
            if(labels.get(j).label.equals(currentValue)){
                return j;
            }
        }
        return -1;
    }
    
    private boolean existLabel(List<LABEL> labels, String label){
        for(int j = 0; j < labels.size(); j++){
            if(labels.get(j).label.equals(label)){
                return true;
            }
        }
        return false;
    }
    
    //Comprobacion de modo de direccionamiento
    public String getMode(String text, int currentIndex_Aux){
        
        for(i = i; i < text.length(); i++){

            if(text.charAt(i) == '#'){
                return "IMM";
            }else if(text.charAt(i) == '$' || text.charAt(i) == '%' || text.charAt(i) == '@'){
                if(isExtended(currentIndex_Aux, text)){
                    return "EXT";
                }else{
                    return "DIR";
                }
            }else if(text.charAt(i) >= '0' && text.charAt(i) <= '9'){
                if(isExtended(currentIndex_Aux, text)){
                    return "EXT";
                }else{
                    return "DIR";
                }
            }else if(text.charAt(i) == 10){
                //System.out.println("char: " + text.charAt(i));
                return "INH";//Quiza deba cambiar la condicion
            }
        }
        return null;
    }
    
    //Obtengo el mnemonico de tabla a base de su identificador y modo
    public MNEMONICO getMnemonico(String mnemonico, String mode){
        
        for(int i = 0; i < table.size(); i++){
            if(table.get(i).mnemonico.equals(mnemonico)){
                if(table.get(i).dirMode.equals(mode)){
                    return table.get(i);
                }
            }
        }
       return null; 
    }
    
    public boolean isExtended(int index, String textAux){
        //Se empieza a recorrer de nuevo el texto para obtener el valor del mnemonico
        String currentValue = "";
        for(index = i; index < textAux.length(); index++){
            if(textAux.charAt(index) != ' ' && textAux.charAt(index) != 10){
               currentValue += textAux.charAt(index); 
            }else{
                break;
            }
        }
        
        if(bytes(currentValue) == 1){
            return false;
        }else{
            return true;
        }
        

    }
    
    public int bytes(String value){
            Converter conv = new Converter();
            int v = 0;
            int aux = 0;

            if(value.charAt(0) == '$'){
                v = conv.hexToDecimal(value.substring(1, value.length()));
            }else if(value.charAt(0) == '%'){
                v = conv.binaryToDec(value.substring(1, value.length()));
            }else if(value.charAt(0) == '@'){
                v = conv.octalToDec(value.substring(1, value.length()));
            }else{
                v = Integer.parseInt(value);
            }
            System.out.println("value: " + value);
            System.out.println("buytes: " + v);
            if(v > 255){
                return 2;
            }else{
                return 1;
            }
        }
    
    public String converter(String value){
        Converter conv = new Converter();
        String v;
        int indexValue = 0;
        boolean IMM_Indicator = false;
        if(value.charAt(0) == '$' || value.charAt(0) == '%' || value.charAt(0) == '@'){
            v = value.substring(1, value.length());
        }else if(value.charAt(0) == '#'){
            v = value.substring(1, value.length());
            IMM_Indicator = true;
            indexValue = 1;
            if(value.charAt(1) == '$' || value.charAt(1) == '%' || value.charAt(1) == '@'){
                v = value.substring(2, value.length());
            }else{
                v = value.substring(1, value.length());
            }
        }else{
            v = value;
        }
        
        if(value.charAt(indexValue) == '$'){
            if(IMM_Indicator == true){
                operatorSt = "#" + "$" + v;
            }else{
                operatorSt = "$" + v;
            }
            return operatorSt;
        }else if(value.charAt(indexValue) == '%'){
            if(IMM_Indicator == true){
                operatorSt = "#" + "$" + conv.binaryToHex(v);
            }else{
                operatorSt = "$" + conv.binaryToHex(v);
            }
            return operatorSt;
        }else if(value.charAt(indexValue) == '@'){
            if(IMM_Indicator == true){
                operatorSt = "#" + "$" + conv.octalToHex(v);
            }else{
                operatorSt = "$" + conv.octalToHex(v);
            }
            return operatorSt;
        }else{
            if(IMM_Indicator == true){
                operatorSt = "#" + "$" + conv.decimalToHex(Integer.parseInt(v));
            }else{
                operatorSt = "$" + conv.decimalToHex(Integer.parseInt(v));
            }
            return operatorSt;
        }
    }
    
    
    private String getCurrentValue(String text){
        String currentValue = "";
        for(i = i; i < text.length(); i++){
            if(text.charAt(i) != ' ' && text.charAt(i) != 10){
               currentValue += text.charAt(i); 
            }else{
                return currentValue;
            }
        }
        return currentValue;
    }
    
    
    public String getFormatedHex(String hex, int bytes){
        
        if(bytes == 2){
            if(hex.length() > 4){
                return (hex.substring(hex.length()-4, hex.length()));
            }else{
                if(hex.length() == 3){
                    return ("0" + hex);
                }else if(hex.length() == 2){
                    return ("00" + hex);
                }else if(hex.length() == 1){
                    return ("000" + hex);
                }else{
                    return hex;
                }
            }
            
        }else if(bytes == 1){
            
            if(hex.length() > 2){
                return (hex.substring(hex.length()-2, hex.length()));
            }else{
                if(hex.length() == 1){
                    return ("0" + hex);
                }else{
                    return hex;
                }
            }
        }
        return "00";//pendiente
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
    
}
