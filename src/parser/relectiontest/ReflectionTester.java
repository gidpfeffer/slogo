package parser.relectiontest;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import model.Constant;
import model.Forward;
import model.TreeNode;

public class ReflectionTester {
	Forward fd = new Forward((Arrays.asList(new TreeNode[] {new Constant(50)})));
	
	public static void main(String args[]) throws ClassNotFoundException{
        Class<?> clazz = Class.forName("model.Constant");
        makeBasicClass(clazz);
        clazz = Class.forName("model.Forward");
        makeAdvancedClass(clazz);
	}
	
	public static void makeBasicClass(Class<?> clazz){
        try {
            // the more robust way
            Constructor<?> ctor = clazz.getDeclaredConstructor(double.class);
            Constant c = (Constant) ctor.newInstance(5.0);
            System.out.println("Printing: " + c.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void makeAdvancedClass(Class<?> clazz){
        try {
            // the more robust way
            Constructor<?> ctor = clazz.getDeclaredConstructor(List.class);
            Object o = ctor.newInstance(Arrays.asList(new TreeNode[] {new Constant(50)}));
            Forward f = (Forward) o;
            System.out.println("Printing: " + f.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
