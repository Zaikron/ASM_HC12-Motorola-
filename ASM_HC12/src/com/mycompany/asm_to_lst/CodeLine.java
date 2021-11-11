
package com.mycompany.asm_to_lst;

/**
 *
 * @author AnthonySandoval
 */
public class CodeLine {
    
    int contloc = 0x0;
    String contlocString = "";
    String mnemORdir = "";
    String operator = "";
    String operatorString = "";
    String dirType = "";
    int sizeB = 0;
    String COP = "";
    String label = "";
    int currentIndex = 0;
    MNEMONICO mnemonicoObj = new MNEMONICO();
    String formula = "";
    
    public CodeLine(){
        
    }

    public int getContloc() {
        return contloc;
    }

    public void setContloc(int contloc) {
        this.contloc = contloc;
    }

    public String getMnemORdir() {
        return mnemORdir;
    }

    public void setMnemORdir(String mnemORdir) {
        this.mnemORdir = mnemORdir;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDirType() {
        return dirType;
    }

    public void setDirType(String dirType) {
        this.dirType = dirType;
    }

    public int getSizeB() {
        return sizeB;
    }

    public void setSizeB(int sizeB) {
        this.sizeB = sizeB;
    }

    public String getCOP() {
        return COP;
    }

    public void setCOP(String COP) {
        this.COP = COP;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getOperatorString() {
        return operatorString;
    }

    public void setOperatorString(String operatorString) {
        this.operatorString = operatorString;
    }

    public MNEMONICO getMnemonicoObj() {
        return mnemonicoObj;
    }

    public void setMnemonicoObj(MNEMONICO mnemonicoObj) {
        this.mnemonicoObj = mnemonicoObj;
    }

    public String getContlocString() {
        return contlocString;
    }

    public void setContlocString(String contlocString) {
        this.contlocString = contlocString;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    
    
    
    
}
