package com.dn.gyl.zsj.chxz;

public class Test {
    public static void main(String[] args) {
        System.out.println("ceshi");
        String zyx1="001,002";
        String zyx="";
        if(!"".equals(zyx1)){
            String[] str=zyx1.split(",");
            for(int i=0;i<str.length;i++){
                System.out.println(str[i]);
            }
        };
    }
}
