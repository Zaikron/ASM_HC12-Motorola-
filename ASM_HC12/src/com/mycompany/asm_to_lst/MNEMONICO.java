
package com.mycompany.asm_to_lst;

/**
 *
 * @author AnthonySandoval
 */
public class MNEMONICO {
    
    public String mnemonico = "";
    public String dirMode = "";
    public int instructionLong = 0;
    public String operationCode = "";
    public String operationCodeOnly = "";
    
    
    public MNEMONICO(String mne, String dirM, int insL, String opC, String opCOnly){
        this.mnemonico = mne;
        this.dirMode = dirM;
        this.instructionLong = insL;
        this.operationCode = opC;
        this.operationCodeOnly = opCOnly;
    }
    
    public MNEMONICO(){
        
    }
    
}
