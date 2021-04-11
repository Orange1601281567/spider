package com.system.model;


public enum OperationUnit {
    /**
     * 被操作的单元
     */
    UNKNOWN("unknown"),
    SYSTEM("system"),
	CASE("case"),
	CHECK("check");
	
 
    private String value;
 
    OperationUnit(String value) {
        this.value = value;
    }
 
    public String getValue() {
        return value;
    }
 
    public void setValue(String value) {
        this.value = value;
    }
}