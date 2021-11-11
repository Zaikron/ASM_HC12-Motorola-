
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
        }else if(mnem.equals("IBNE")){
            mnemonico = getMnemonico(mnem, "REL");
            return IBNE(text, labels, label, mnemonico);
        }else if(mnem.equals("JMP")){
            mnemonico = getMnemonico(mnem, "EXT");
            return JMP(text, labels, label, mnemonico);
        }else if(mnem.equals("LDAA")){
            mode = getMode(text, i);
            mnemonico = getMnemonico(mnem, mode);
            return LDAA(text, labels, label, mnemonico);
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
    
    
    private CodeLine LDAA(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        
        if(mnemObj.dirMode.equals("IDX1")){
            return f1(text, labels, label, mnemObj);
        }else if(mnemObj.dirMode.equals("IDX2_9")){
            return f2(text, labels, label, mnemObj, 1);
        }else if(mnemObj.dirMode.equals("IDX2_16")){
            return f2(text, labels, label, mnemObj, 2);
        }else if(mnemObj.dirMode.equals("IDX3")){
            return f3(text, labels, label, mnemObj);
        }else if(mnemObj.dirMode.equals("IDX5")){
            return f5(text, labels, label, mnemObj);
        }else if(mnemObj.dirMode.equals("IDX6")){
            return f6(text, labels, label, mnemObj);
        }else if(mnemObj.dirMode.equals("FDR")){
            return FDR(text, labels, label, mnemObj);
        }else{
            return LDAA_NORMAL(text, labels, label, mnemObj);
        }
        
    }
    
    private CodeLine LDAA_NORMAL(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
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
    
    private CodeLine f1(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        //rr0nnnnn
        CodeLine cl = new CodeLine();
        String formatCONTLOC = Integer.toHexString(org);
        formatCONTLOC = getFormatedHex(formatCONTLOC, 2);
        String rr = "";
        String binary = "0";
        String xb = "0";
        String firstValue = "0";
        String firstValueBinary = "0";
        
        firstValue = getCurrentValue(text); //Primer valor antes de la coma
        if(text.charAt(i+1) == ' '){
            i+=2;
        }else{
           i++; 
        }
        String secondValue = getCurrentValue(text);//Segundo valor despues de la coma (rr)
        
        //Convierto a binario el primer valor, antes de la coma
        firstValueBinary = converter.decimalToBinary(Integer.parseInt(firstValue));
        //Le aplico formato a la cadena para que siempre tenga tamaño 5
        firstValueBinary = getFormatedBinary_5bits(firstValueBinary);
        
        //Obtengo rr
        rr = getRR(secondValue);
        //Concateno todos los reultados individuales para la formula: rr0nnnnn
        binary = rr + "0" + firstValueBinary;
        //Convierto el valor binario concatenado a hexadecimal para el valor de xb
        xb = converter.binaryToHex(binary);
        
        if(label.length()!= 0 && firstValue.length()!= 0){
            if(!existLabel(labels, label)){
                labels.add(new LABEL(label, "$" + getFormatedHex(formatCONTLOC, 2)));
            }
        }
        
        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setOperator(xb);
        cl.setOperatorString(firstValue + ", " + secondValue);
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        cl.setFormula("(F1)");
        
        return cl;
    }
    
    private CodeLine f2(String text, List<LABEL> labels, String label, MNEMONICO mnemObj, int bytes){
        //111rr0zs
        CodeLine cl = new CodeLine();
        String formatCONTLOC = Integer.toHexString(org);
        formatCONTLOC = getFormatedHex(formatCONTLOC, 2);
        String rr = "";
        String binary = "0";
        String xb = "0";
        String firstValue = "0";
        String firstValueHex = "0";
        char s = '0';
        char z = '0';
        
        firstValue = getCurrentValue(text); //Primer valor antes de la coma
        if(text.charAt(i+1) == ' '){
            i+=2;
        }else{
           i++; 
        }
        String secondValue = getCurrentValue(text);//Segundo valor despues de la coma (rr)
        
        rr = getRR(secondValue); //Obtengo rr
        s = getS(Integer.parseInt(firstValue)); //positivo o negativo
        z = getZ(Integer.parseInt(firstValue)); //1 byte o 2 bytes
        binary = "111" + rr + "0" + z + s; //Concateno respecto a la formula 2: 111rr0zs
        xb = converter.binaryToHex(binary); //Convierto a Hex
        //Obtengo el primer valor, antes de la coma, pero en hexadecimal
        firstValueHex = converter.decimalToHex(Integer.parseInt(firstValue));
        firstValueHex = getFormatedHex(firstValueHex, bytes);
        
        if(label.length()!= 0 && firstValue.length()!= 0){
            if(!existLabel(labels, label)){
                labels.add(new LABEL(label, "$" + getFormatedHex(formatCONTLOC, 2)));
            }
        }
        
        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setOperator(xb + " " + firstValueHex);
        cl.setOperatorString(firstValue + ", " + secondValue);
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        cl.setFormula("(F2, " + bytes + "b)");
        
        return cl;
    }
    
    private CodeLine f3(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        //111rr011
        CodeLine cl = new CodeLine();
        String formatCONTLOC = Integer.toHexString(org);
        formatCONTLOC = getFormatedHex(formatCONTLOC, 2);
        String rr = "";
        String binary = "0";
        String xb = "0";
        String firstValue = "0";
        String firstValueHex = "0";
        
        i++; //Para pasar del '['
        firstValue = getCurrentValue(text); //Primer valor antes de la coma
        if(text.charAt(i+1) == ' '){
            i+=2;
        }else{
           i++; 
        }
        String secondValue = getCurrentValue(text);//Segundo valor despues de la coma (rr)
        
        rr = getRR(secondValue); //Obtengo rr
        binary = "111" + rr + "011"; //Concateno respecto a la formula 3: 111rr011
        xb = converter.binaryToHex(binary); //Convierto a Hex
        
        firstValueHex = converter.decimalToHex(Integer.parseInt(firstValue));
        firstValueHex = getFormatedHex(firstValueHex, 2);
        
        if(label.length()!= 0 && firstValue.length()!= 0){
            if(!existLabel(labels, label)){
                labels.add(new LABEL(label, "$" + getFormatedHex(formatCONTLOC, 2)));
            }
        }
        
        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setOperator(xb + " " + firstValueHex);
        cl.setOperatorString("["+firstValue + ", " + secondValue+"]");
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        cl.setFormula("(F3)");
        
        return cl;
    }
    
    private CodeLine f5(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        //111rr1aa
        CodeLine cl = new CodeLine();
        String formatCONTLOC = Integer.toHexString(org);
        formatCONTLOC = getFormatedHex(formatCONTLOC, 2);
        String rr = "";
        String aa = "";
        String binary = "0";
        String xb = "0";
        String firstValue = "0";
        
        firstValue = getCurrentValue(text); //Primer valor antes de la coma
        if(text.charAt(i+1) == ' '){
            i+=2;
        }else{
           i++; 
        }
        String secondValue = getCurrentValue(text);//Segundo valor despues de la coma (rr)
        
        //Obtengo rr
        rr = getRR(secondValue);
        //Obtengo aa
        aa = getAA(firstValue);
        //Concateno todos los reultados individuales para la formula 5: 111rr1aa
        binary = "111" + rr + "1" + aa;
        //Convierto el valor binario concatenado a hexadecimal para el valor de xb
        xb = converter.binaryToHex(binary);
        
        if(label.length()!= 0 && firstValue.length()!= 0){
            if(!existLabel(labels, label)){
                labels.add(new LABEL(label, "$" + getFormatedHex(formatCONTLOC, 2)));
            }
        }
        
        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setOperator(xb);
        cl.setOperatorString(firstValue + ", " + secondValue);
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        cl.setFormula("(F5)");
        
        return cl;
    }
    
    private CodeLine f6(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        //111rr111
        CodeLine cl = new CodeLine();
        String formatCONTLOC = Integer.toHexString(org);
        formatCONTLOC = getFormatedHex(formatCONTLOC, 2);
        String rr = "";
        String binary = "0";
        String xb = "0";
        String firstValue = "0";
        
        i++; //Para pasar del '['
        firstValue = getCurrentValue(text); //Primer valor antes de la coma
        if(text.charAt(i+1) == ' '){
            i+=2;
        }else{
           i++; 
        }
        String secondValue = getCurrentValue(text);//Segundo valor despues de la coma (rr)
        
        rr = getRR(secondValue); //Obtengo rr
        binary = "111" + rr + "111"; //Concateno respecto a la formula 6: 111rr111
        xb = converter.binaryToHex(binary); //Convierto a Hex
        
        
        if(label.length()!= 0 && firstValue.length()!= 0){
            if(!existLabel(labels, label)){
                labels.add(new LABEL(label, "$" + getFormatedHex(formatCONTLOC, 2)));
            }
        }
        
        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setOperator(xb);
        cl.setOperatorString("["+firstValue + ", " + secondValue+"]");
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        cl.setFormula("(F6)");
        
        return cl;
    }
    
    
    private CodeLine FDR(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        //111rr011
        CodeLine cl = new CodeLine();
        String formatCONTLOC = Integer.toHexString(org);
        formatCONTLOC = getFormatedHex(formatCONTLOC, 2);
        String rr = "";
        String binary = "0";
        String xb = "0";
        String firstValue = "0";
        String firstValueHex = "0";
        
        firstValue = getCurrentValue(text); //Primer valor antes de la coma
        if(text.charAt(i+1) == ' '){
            i+=2;
        }else{
           i++; 
        }
        String secondValue = getCurrentValue(text);//Segundo valor despues de la coma (rr)

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setOperator("FDR");
        cl.setOperatorString(firstValue + ", " + secondValue+"]");
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        cl.setFormula("(FDR)");
        
        return cl;
    }
    
    
    private String getRR(String value){
        if(value.equals("X")){
            return "00";
        }else if(value.equals("Y")){
            return "01";
        }else if(value.equals("SP")){
            return "10";
        }else if(value.equals("PC")){
            return "11";
        }else{
            return "ZZ";
        }
    }
    
    private String getAA(String value){
        if(value.equals("A")){
            return "00";
        }else if(value.equals("B")){
            return "01";
        }else if(value.equals("D")){
            return "10";
        }else{
            return "ZZ";
        }
    }
    
    //Positivo o negativo
    private char getS(int value){
        if(value >= 0){
            return '0';
        }else{
            return '1';
        }
    }
    
    //1 byte o 2 bytes
    private char getZ(int value){
        if(value >= -256 && value <= 255){
            return '0';
        }else{
            return '1';
        }
    }
    
    private String getFormatedBinary_5bits(String value){
        int repetitions = 5 - value.length();
        
        if(repetitions == 4){
            return "0000" + value;
        }else if(repetitions == 3){
            return "000" + value;
        }else if(repetitions == 2){
            return "00" + value;
        }else if(repetitions == 1){
            return "0" + value;
        }else if(repetitions < 0){
            return value.substring(value.length()-5, value.length());
        }else{
            return value;
        }
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
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl, true);
        
        if(!(operatorRusult >= -128 && operatorRusult <= 127)){
            mnemObj = getMnemonico("BNE", "FDR");
            cl.setOperator("FDR");
        }else{
            cl.setOperator(getFormatedHex(Integer.toHexString(operatorRusult), 1));
        }

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setCurrentIndex(i);
        return cl;
    }
    
    
    private CodeLine JMP(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        i++;    //Para que empiece a leer la siguiente palabra
        String formatCONTLOC = Integer.toHexString(org);
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl, false);

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setOperator(getFormatedHex(Integer.toHexString(operatorRusult), 2));
        cl.setCurrentIndex(i);
        return cl;
    }
    
    
    private CodeLine BEQ(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
        CodeLine cl = new CodeLine();
        i++;    //Para que empiece a leer la siguiente palabra
        String formatCONTLOC = Integer.toHexString(org);
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl, true);

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
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl, true);

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
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl, true);

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
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl, true);
        objectCode = getObjectCode_DBEQ(operatorRusult, reg);

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setOperator(objectCode + " " + getFormatedHex(Integer.toHexString(operatorRusult), 1));
        cl.setCurrentIndex(i);

        return cl;
    }
    
    private CodeLine IBNE(String text, List<LABEL> labels, String label, MNEMONICO mnemObj){
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
        int operatorRusult = getOperatorOrLabel(text, labels, mnemObj, cl, true);
        objectCode = getObjectCode_IBNE(operatorRusult, reg);

        cl.setContloc(org);
        cl.setContlocString(formatCONTLOC);
        cl.setMnemonicoObj(mnemObj);
        cl.setOperator(objectCode + " " + getFormatedHex(Integer.toHexString(operatorRusult), 1));
        cl.setCurrentIndex(i);

        return cl;
    }
    
    /*** Funciones de utilidad ***/
    
    private int getOperatorOrLabel(String text, List<LABEL> labels, MNEMONICO mnemObj, CodeLine cl, boolean resta){
        String currentValue = getCurrentValue(text);
        int tabsimIndex = isLabel(labels, currentValue);
        int tabsimDirection_Dec = 0;
        int operatorRusult = 0;
        String directionTabsim = "";
        String hexTabsim = "";
        
        cl.setOperatorString(cl.getOperatorString() + currentValue);
        
        if(resta == true){
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
        }else{
            System.out.println("--> " + currentValue);
            tabsimIndex = isLabel(labels, currentValue);
            if(tabsimIndex == -1){
                return 0;
            }else{
                directionTabsim = labels.get(tabsimIndex).direction;
                hexTabsim = "0x" + directionTabsim.substring(1, directionTabsim.length());
                tabsimDirection_Dec = Integer.decode(hexTabsim);
                return tabsimDirection_Dec;
            }
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
    
    private String getObjectCode_DBEQ(int dir, String reg){
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
    
    private String getObjectCode_IBNE(int dir, String reg){
        if(dir < 0){
            if(reg.equals("A")){
               return "B0"; 
            }else if(reg.equals("B")){
               return "B1"; 
            }else{
                return "F";
            }
        }else if(dir > 0){
            if(reg.equals("A")){
               return "A0"; 
            }else if(reg.equals("B")){
               return "A1"; 
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
            }else if(text.charAt(i) >= '0' && text.charAt(i) <= '9' || text.charAt(i) == '-'){
                if(isIDX(text, currentIndex_Aux)){
                    return getIDXmode_f1_f2(text, currentIndex_Aux);
                }else{
                    if(isExtended(currentIndex_Aux, text)){
                        return "EXT";
                    }else{
                        return "DIR";
                    }
                }
            }else if(text.charAt(i) == 10){
                //System.out.println("char: " + text.charAt(i));
                return "INH";//Quiza deba cambiar la condicion
            }else if(text.charAt(i) == '[' || text.charAt(i) == 'A' || 
                    text.charAt(i) == 'B' || text.charAt(i) == 'D'){
                return getIDXmode_f3_f5_f6(text, currentIndex_Aux);
            }
        }
        return null;
    }
    
    private boolean isIDX(String text, int index){
        int j = 0;
        for(j = index; text.charAt(j) != 10; j++){
            if(text.charAt(j) == ','){
                return true;
            }
        }
        return false;
    }
    
    private String getIDXmode_f1_f2(String text, int index){
        int j = 0;
        int firstValueInt = 0;
        String firstValue = "";
        String secondValue = "";
        
        for(j = index+1; text.charAt(j) != ','; j++){
            firstValue += text.charAt(j);
        }
        
        firstValueInt = Integer.parseInt(firstValue);
        if(firstValueInt >= -16 && firstValueInt <= 15){
            System.out.println("Es IDX F1");
            return "IDX1";
        }else if(firstValueInt >= -256 && firstValueInt <= 255){
            System.out.println("Es IDX F2 1b");
            return "IDX2_9";
        }else{
            System.out.println("Es IDX F2 2b");
            return "IDX2_16";
        }
    }
    
    private String getIDXmode_f3_f5_f6(String text, int index){
        int j = 0;
        int firstValueInt = 0;
        String firstValue = "";
        
        if(text.charAt(index+1) == '['){
            if(text.charAt(index+2) == '-'){
                System.out.println("FDR: Negativo");
                return "FDR";
            }else{
                for(j = index+2; text.charAt(j) != ','; j++){
                    firstValue += text.charAt(j);
                }
                if(firstValue.equals("D")){
                    System.out.println("Es IDX F6");
                    return "IDX6";
                }else if(firstValue.equals("A") || firstValue.equals("B")){
                    System.out.println("FDR f6");
                    return "FDR";
                }else{
                    firstValueInt = Integer.parseInt(firstValue);
                    if(firstValueInt < 0 || firstValueInt > 65535){
                        System.out.println("FDR: Negativo o mayor");
                        return "FDR";
                    }else{
                        System.out.println("Es IDX F3");
                        return "IDX3";
                    }
                }
            }
        }else if(text.charAt(index+1) == 'A' || text.charAt(index+1) == 'B' || text.charAt(index+1) == 'D'){
            System.out.println("Es IDX F5");
            return "IDX5";
        }else{
            System.out.println("FDR");
            return "FDR";
        }
        
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
            if(text.charAt(i) != ' ' && text.charAt(i) != 10 
                    && text.charAt(i) != ',' && text.charAt(i) != ']'){
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
