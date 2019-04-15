package com.ai.utils;

import java.lang.reflect.Field;

public class GenTransform {

    
    public static void gen(Class<?> dest, Class<?> source) throws ClassNotFoundException {
        String destobjName = toLowerCaseFirstOne(dest.getSimpleName());
        String sourceObjName = toLowerCaseFirstOne(source.getSimpleName());
        Field[] fields = dest.getDeclaredFields();
        System.out.println("\tpublic static "+toUpperCaseFirstOne(destobjName) +" transform" 
        + "("+source.getSimpleName()+" "+sourceObjName+"){");
        
        System.out.println("\t\tif("+sourceObjName+" == null) {");
        System.out.println("\t\t\treturn null;");
        System.out.println("\t\t}");
        
        System.out.println("\t\t"+toUpperCaseFirstOne(destobjName)+" "+destobjName+" = new "  + toUpperCaseFirstOne(destobjName)+"();");
        for (Field f : fields) {
            if ("serialVersionUID".equals(f.getName())) {
                continue;
            }
            String upperFieldName = toUpperCaseFirstOne(f.getName());
            System.out.println("\t\t"+destobjName + ".set" + upperFieldName + "("+sourceObjName+".get"+upperFieldName+"());");
           
        }
        System.out.println("\t\treturn "+destobjName+";");
        System.out.println("\t}\n");
    }
    
    /**
     * 首字母转小写 
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 判断object是否为基本类型
     */
    public static boolean isBaseType(Class<?> clazz) {
        if (clazz.equals(java.lang.Integer.class) || clazz.equals(java.lang.Byte.class)
                || clazz.equals(java.lang.Long.class) || clazz.equals(java.lang.Double.class)
                || clazz.equals(java.lang.Float.class) || clazz.equals(java.lang.Character.class)
                || clazz.equals(java.lang.Short.class) || clazz.equals(java.lang.Boolean.class)
                || clazz.equals(java.lang.String.class)) {
            return true;
        }
        return false;
    }
}
