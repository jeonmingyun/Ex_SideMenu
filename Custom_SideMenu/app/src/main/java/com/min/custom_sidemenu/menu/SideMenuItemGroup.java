package com.min.custom_sidemenu.menu;

import java.util.HashMap;

public class SideMenuItemGroup {
    public String groupTxt;
    public HashMap<String, String> childMap;

    public SideMenuItemGroup(String name){
        this.groupTxt = name;
        this.childMap = new HashMap<>();
    }
}