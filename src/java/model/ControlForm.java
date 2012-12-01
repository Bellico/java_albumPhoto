package model;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class ControlForm {

    HttpServletRequest request;
    private HashMap<String, String> form = new HashMap<String, String>();

    public ControlForm(HttpServletRequest request) {
        this.request = request;
    }
    
    private void ControlManager(){
        
    }
    
    public void check(String field, String type){
       }
    
        public void check(String field, String type,String mSuccess, String mError ){
        
    }
}
