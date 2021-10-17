
package com.mycompany.asm_to_lst;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnthonySandoval
 */
public class Functionality_DIRECT {
    
    int i = 0;
    String operatorSt = "";
    Converter converter = new Converter();
    
    public Functionality_DIRECT(){
        
    }
    
    public CodeLine findDirective(String directive, String text, int currentIndex, int org, List<LABEL> labels, String label){
        i = currentIndex;
        
        if(directive.equals("ORG")){
            return isORG(text, currentIndex);
        }else if(directive.equals("END")){
            return isEND(text, currentIndex, org, labels, label);
        }else if(directive.equals("EQU")){
            return isEQU(text, currentIndex, org, labels, label);
        }else if(directive.equals("START")){
            return isStart(text, currentIndex);
        }else if(directive.equals("DC.B")){
            return isDC_B(text, currentIndex, org);
        }else if(directive.equals("DC.W")){
            return isDC_W(text, currentIndex, org);
        }else if(directive.equals("FCC")){
            return isFCC(text, currentIndex, org);
        }else if(directive.equals("FCB")){
            return isFCB(text, currentIndex, org);
        }else if(directive.equals("BSZ")){
            return isBSZ(text, currentIndex, org, labels, label);
        }else if(directive.equals("ZMB")){
            return isZMB(text, currentIndex, org);
        }else if(directive.equals("FILL")){
            return isFILL(text, currentIndex, org);
        }else{
            System.out.println("Directive not found");
        }
        
        return null;
    }
    
    public String getCurrentValue(String currentValue, String text, int cI){
        for(i = cI+1; i < text.length(); i++){
            if(text.charAt(i) != ' ' && text.charAt(i) != 10){
               currentValue += text.charAt(i); 
            }else{
               break;
            }
        }
        return currentValue;
    }
    
    private boolean existLabel(List<LABEL> labels, String label){
        for(int j = 0; j < labels.size(); j++){
            if(labels.get(j).label.equals(label)){
                return true;
            }
        }
        return false;
    }
    
    public String getCurrentValues(String currentValue, String text, int cI){
        for(i = cI+1; i < text.length(); i++){
            if(text.charAt(i) != 10){
               currentValue += text.charAt(i); 
            }else{
               break;
            }
        }
        return currentValue;
    }
    
    public ArrayList<String> getArrayValues(String currentValue, String text, int cI){
        ArrayList<String> arrayValues = new ArrayList<String>();
        int j = 0;
        char caracter = ' ';
        for(j = cI+1; j < text.length(); j++){
            if(text.charAt(j) != 10 && text.charAt(j) != ',' && text.charAt(j) != ' '){
                currentValue += text.charAt(j);
            }else if(text.charAt(j) == ','){
                if(currentValue.charAt(0) == '´'){
                    caracter = currentValue.charAt(1);
                    arrayValues.add(converter.decimalToHex((int)caracter));
                }else{
                    arrayValues.add(converter.decimalToHex(Integer.parseInt(currentValue)));
                }
                currentValue = "";
            }else if(text.charAt(j) == 10){
                //Comprobaciones para caracteres
                if(currentValue.charAt(0) == '´'){
                    caracter = currentValue.charAt(1);
                    arrayValues.add(converter.decimalToHex((int)caracter));
                }else{
                    arrayValues.add(converter.decimalToHex(Integer.parseInt(currentValue)));
                }
                return arrayValues;
            }
        }
        return arrayValues;
    }
    
    
    public String getConcatArrayValues(ArrayList<String> values, int bytes){
        String value = "";
        String formated = "";
        for(int j = 0; j < values.size(); j++){
            formated = getFormatedHex(values.get(j), bytes);
            value += (formated + " ");
        }
        return value;
    }
    
    public String converter(String value){
        Converter conv = new Converter();
        String v;
        if(value.charAt(0) == '$' || value.charAt(0) == '%' || value.charAt(0) == '@'){
            v = value.substring(1, value.length());
        }else{
            v = value;
        }
        
        if(value.charAt(0) == '$'){
            operatorSt = "$" + v;
            return operatorSt;
        }else if(value.charAt(0) == '%'){
            operatorSt = "$" + conv.binaryToHex(v);
            return operatorSt;
        }else if(value.charAt(0) == '@'){
            operatorSt = "$" + conv.octalToHex(v);
            return operatorSt;
        }else{
            operatorSt = "$" + conv.decimalToHex(Integer.parseInt(v));
            return operatorSt;
        }
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
    
    public String getBSZBytes(int b){
        String bytes = "";
        for(int j = 0; j < b; j++){
            bytes += "00 ";
        }
        return bytes;
    }
    
    public String getFCC_Values(String value){
        String values = "";
        for(int j = 0; j < value.length(); j++){
            if(value.charAt(j) != '/'){
                values += (converter.decimalToHex((int)value.charAt(j)) + " ");
            }
        }
        return values;
    }
    
    public String getFILL_Values(ArrayList<String> arrayValues){
        String values = "";
        for(int j = 0; j < Integer.parseInt(arrayValues.get(1)); j++){
            values += (getFormatedHex(arrayValues.get(0), 1) + " ");
        }
        return values;
    }
    
    public CodeLine isORG(String text, int cI){
        CodeLine cl = new CodeLine();
        String operator = "";
        String currentValue = "";
        String hexOrg = "0x0"; //Cadena para definir la direccion de ORG
        int org = 0; //valor para incrementar la direccion de ORG
       
        currentValue = getCurrentValue(currentValue, text, cI);
        
        operator = currentValue;
        if(currentValue.charAt(0) == '$'){
            currentValue = currentValue.substring(1, currentValue.length());
        }
        
         //Guardo la direccion de ORG
        hexOrg = "0x" + currentValue;
        //Le asigno la direccion al entero para poder sumarle los bytes
        org = Integer.decode(hexOrg);
        
        cl.setContloc(org);
        cl.setMnemORdir("ORG");
        cl.setOperator(operator);
        cl.setOperatorString(operator);
        cl.setCurrentIndex(i);
        
        return cl;
    }
    
    public CodeLine isEND(String text, int cI, int org, List<LABEL> labels, String currentLabel){
        CodeLine cl = new CodeLine();

        cl.setContloc(org);
        cl.setMnemORdir("END");
        cl.setCurrentIndex(i);
        
        /*** Probablemente lo tenga que agregar a todas las directivas (funciones)***/
        if(currentLabel.length() != 0){
            if(!existLabel(labels, currentLabel)){
                labels.add(new LABEL(currentLabel, "$" + getFormatedHex(Integer.toHexString(org), 2)));
            }
        }
        
        return cl;  
    }
    
    public CodeLine isEQU(String text, int cI, int org, List<LABEL> labels, String currentLabel){
        CodeLine cl = new CodeLine();
        String operator = "";
        String opNotSimbol = "";
        String currentValue = "";
        
        currentValue = getCurrentValue(currentValue, text, cI);
        
        operator = converter(currentValue);
        opNotSimbol = operator.substring(1, operator.length());
        
        cl.setContloc(org);
        cl.setMnemORdir("EQU");
        cl.setOperator(operator);
        cl.setOperatorString(operator);
        cl.setCurrentIndex(i);
        
        if(!existLabel(labels, currentLabel)){
            labels.add(new LABEL(currentLabel, "$" + getFormatedHex(opNotSimbol, 2)));
        }
        
        
        return cl;  
    }
    
    public CodeLine isStart(String text, int cI){
        CodeLine cl = new CodeLine();
        String hexOrg = "0x0"; //Cadena para definir la direccion de ORG
        int org = 0; //valor para incrementar la direccion de ORG
       
         //Guardo la direccion de ORG
        hexOrg = "0x" + "0000";
        //Le asigno la direccion al entero para poder sumarle los bytes
        org = Integer.decode(hexOrg);
        
        cl.setContloc(org);
        cl.setMnemORdir("START");
        cl.setCurrentIndex(i);
        
        return cl;
    }
    
    public CodeLine isDC_B(String text, int cI, int org){
        CodeLine cl = new CodeLine();
        ArrayList<String> arrayValues = new ArrayList<String>();
        String values = "";
        int increment = 0;
        
        if(text.charAt(i) == 10){
            values = "00";
            increment = 1;
        }else{
            values = getCurrentValues(values, text, cI);
            arrayValues = getArrayValues("", text, cI);
            increment = arrayValues.size();
        }
        
        cl.setContloc(org + increment);
        cl.setMnemORdir("DC.B");
        cl.setOperator(values);
        cl.setOperatorString(values);
        cl.setCurrentIndex(i);
        cl.setCOP(getConcatArrayValues(arrayValues, 1));
        return cl;
    }
    
    public CodeLine isBSZ(String text, int cI, int org, List<LABEL> labels, String currentLabel){
        CodeLine cl = new CodeLine();
        String value = "";
        
        if(text.charAt(i) == 10){
            value = "";
        }else{
            value = getCurrentValue(value, text, cI);
        }

        cl.setContloc(org + Integer.parseInt(value));
        cl.setMnemORdir("BSZ");
        cl.setOperator(value);
        cl.setOperatorString(value);
        cl.setCurrentIndex(i);
        cl.setCOP(getBSZBytes(Integer.parseInt(value)));
        
        if(currentLabel.length() != 0){
            if(!existLabel(labels, currentLabel)){
                labels.add(new LABEL(currentLabel, "$" + getFormatedHex(Integer.toHexString(org), 2)));
            }
        }
        
        return cl;
    }
    
    public CodeLine isZMB(String text, int cI, int org){
        CodeLine cl = new CodeLine();
        String value = "";
        
        if(text.charAt(i) == 10){
            value = "";
        }else{
            value = getCurrentValue(value, text, cI);
        }

        cl.setContloc(org + Integer.parseInt(value));
        cl.setMnemORdir("ZMB");
        cl.setOperator(value);
        cl.setOperatorString(value);
        cl.setCurrentIndex(i);
        cl.setCOP(getBSZBytes(Integer.parseInt(value)));
        
        return cl;
    }
    
    public CodeLine isFILL(String text, int cI, int org){
        CodeLine cl = new CodeLine();
        ArrayList<String> arrayValues = new ArrayList<String>();
        String values = "";
        
        if(text.charAt(i) == 10){
            values = "";
        }else{
            values = getCurrentValues(values, text, cI);
            arrayValues = getArrayValues("", text, cI);
        }

        cl.setContloc(org + Integer.parseInt(arrayValues.get(1)));
        cl.setMnemORdir("FILL");
        cl.setOperator(values);
        cl.setOperatorString(values);
        cl.setCurrentIndex(i);
        cl.setCOP(getFILL_Values(arrayValues));
        return cl;
    }
   
    public CodeLine isDC_W(String text, int cI, int org){
        CodeLine cl = new CodeLine();
        ArrayList<String> arrayValues = new ArrayList<String>();
        String values = "";
        int increment = 0;
        
        if(text.charAt(i) == 10){
            values = "";
            increment = 2;
        }else{
            values = getCurrentValues(values, text, cI);
            arrayValues = getArrayValues("", text, cI);
            increment = (arrayValues.size()*2);
        }

        cl.setContloc(org + increment);
        cl.setMnemORdir("DC.W");
        cl.setOperator(values);
        cl.setOperatorString(values);
        cl.setCurrentIndex(i);
        cl.setCOP(getConcatArrayValues(arrayValues, 2));
        
        return cl;
    }
    
    public CodeLine isFCC(String text, int cI, int org){
        CodeLine cl = new CodeLine();
        String value = "";
        
        if(text.charAt(i) == 10){
            value = "";
        }else{
            value = getCurrentValue(value, text, cI);
        }
        
        cl.setContloc(org + getFCC_Size(value));
        cl.setMnemORdir("FCC");
        cl.setOperator(value);
        cl.setOperatorString(value);
        cl.setCurrentIndex(i);
        cl.setCOP(getFCC_Values(value));
        return cl;
    }
    
    public int getFCC_Size(String value){
        int cont = 0;
        for(int j = 0; j < value.length(); j++){
            if(value.charAt(j) != '/'){
                cont++;
            }
        }
        return cont;
    }
    
    public CodeLine isFCB(String text, int cI, int org){
        CodeLine cl = new CodeLine();
        ArrayList<String> arrayValues = new ArrayList<String>();
        String value = "";
        
        if(text.charAt(i) == 10){
            value = "";
        }else{
            value = getCurrentValue(value, text, cI);
            arrayValues = getArrayValues("", text, cI);
        }

        cl.setContloc(org + arrayValues.size());
        cl.setMnemORdir("FCB");
        cl.setOperator(value);
        cl.setOperatorString(value);
        cl.setCurrentIndex(i);
        cl.setCOP(getConcatArrayValues(arrayValues, 1));
        
        return cl;
    }
    
}
