package parser.reflectiontest;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import model.Constant;
import model.TreeNode;

public class ClassGenerator {
	TreeNode generated;

	public ClassGenerator(String className, List<TreeNode> list) {
		Class<?> clazz;
		try {
			clazz = Class.forName("model." + className);
			makeAdvancedClass(clazz, list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ClassGenerator(String className, Double num) {
		Class<?> clazz;
		try {
			clazz = Class.forName("model." + className);
			makeAdvancedClass(clazz, num);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void makeAdvancedClass(Class<?> clazz, List<TreeNode>list) {
		try {
			// the more robust way
			Constructor<?> ctor = clazz.getDeclaredConstructor(List.class);
			Object o = ctor.newInstance(list);
			generated = (TreeNode) clazz.cast(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeAdvancedClass(Class<?> clazz, Double num) {
		try {
			// the more robust way
			Constructor<?> ctor = clazz.getDeclaredConstructor(double.class);
			Object o = ctor.newInstance(num);
			generated = (TreeNode) clazz.cast(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TreeNode getGenerated(){
		return generated;
	}
}
