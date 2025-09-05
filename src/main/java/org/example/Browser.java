package org.example;

public enum Browser{
    CHROME("chrome"),
    EDGE("edge"),
    FIREFOX("firefox");
    private final String browsername;
    Browser(String browsername){
        this.browsername=browsername;
    }
    public String getBrowserName(){
        return browsername;
    }

}
