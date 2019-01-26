package com.ai.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GenSetInvoker {

    public static void gen(Class<?> clazz) throws ClassNotFoundException {
        String objName = toLowerCaseFirstOne(clazz.getSimpleName());
        Field[] fields = clazz.getDeclaredFields();
        List<Field> typeList = new ArrayList<>();
        System.out.println("\tprivate static void build"+toUpperCaseFirstOne(objName)  + "(){");
        for (Field f : fields) {
            if ("serialVersionUID".equals(f.getName())) {
                continue;
            }
            String upperFieldName = toUpperCaseFirstOne(f.getName());
            if(isBaseType(f.getType())) {
                System.out.println("\t\t"+objName + ".set" + upperFieldName + "(null);");
            } else {
                System.out.println("\t\t"+objName + ".set" + upperFieldName + "(this.build"+upperFieldName+"());"); 
                typeList.add(f);
            }
        }
        System.out.println("\t}");
        for(Field f : typeList) {
            Class<?> cls = f.getType();
            
            
            Type type = f.getGenericType();
            if(f.getGenericType() != null && !(type instanceof Class)) {
                String actualClassType = ((ParameterizedType)type).getActualTypeArguments()[0].getTypeName();
                String typeName = actualClassType.substring(actualClassType.lastIndexOf(".")+1);
                cls = Class.forName(actualClassType);
                if(isBaseType(cls)) {
                    System.out.println("\tprivate void build"+toUpperCaseFirstOne(f.getName())+f.getType().getSimpleName() + "(){");
                }else {
                    System.out.println("\tprivate void build"+toUpperCaseFirstOne(typeName)+f.getType().getSimpleName() + "(){");
                }
               
            }else {
                String objName2 = toLowerCaseFirstOne(cls.getSimpleName());
                System.out.println("\tprivate void build"+toUpperCaseFirstOne(objName2)+ "(){");
            }
            
            Field[] fields2 = cls.getDeclaredFields();
            for (Field fd: fields2) {
                if ("serialVersionUID".equals(fd.getName())) {
                    continue;
                }
                String upperFieldName = toUpperCaseFirstOne(fd.getName());
                if(isBaseType(fd.getType())) {
                    System.out.println("\t\t"+toLowerCaseFirstOne(f.getName())+ ".set" + upperFieldName + "(null);");
                } else {
                    System.out.println("\t\t"+toLowerCaseFirstOne(cls.getSimpleName()) + ".set" + upperFieldName + "(this.build"+upperFieldName+"());"); 
                }
            }
            System.out.println("\t}\n");
        }
        
    }

    /**
     * 
     * @Title: toLowerCaseFirstOne @Description: 首字母转小写 @param @param
     * s @param @return @return String 返回类型 @throws
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 
     * @Title: toUpperCaseFirstOne @Description: 首字母转大写 @param @param
     * s @param @return @return String 返回类型 @throws
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 
     * @Title: isBaseType @Description: 判断object是否为基本类型 @param @param
     * object @param @return @return boolean 返回类型 @throws
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
