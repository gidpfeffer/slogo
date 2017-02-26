package parser.reflectiontest;

import java.lang.reflect.Constructor;
import java.util.List;

import model.command.TreeNode;
import model.turtle.TurtleState;

public class ClassGenerator {
	private TreeNode generated;
	private PackageLocationHandler PLH;
	
	public ClassGenerator(){
		PLH = new PackageLocationHandler();
	}
		
	public void generate(String className, List<TreeNode> list, TurtleState t) {
		Class<?> clazz;
		try {
			clazz = Class.forName(PLH.getLoc(className) + className);
			makeAdvancedClass(clazz, list, t);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generate(String className, Double num, TurtleState t) {
		Class<?> clazz;
		try {
			clazz = Class.forName(PLH.getLoc(className) + className);
			makeAdvancedClass(clazz, num, t);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void makeAdvancedClass(Class<?> clazz, List<TreeNode>list, TurtleState t) {
		try {
			// the more robust way
			Constructor<?> ctor = clazz.getDeclaredConstructor(List.class, TurtleState.class);
			Object o = ctor.newInstance(list, t);
			generated = (TreeNode) clazz.cast(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeAdvancedClass(Class<?> clazz, Double num, TurtleState t) {
		try {
			// the more robust way
			Constructor<?> ctor = clazz.getDeclaredConstructor(double.class, TurtleState.class);
			Object o = ctor.newInstance(num, t);
			generated = (TreeNode) clazz.cast(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TreeNode getGenerated(){
		return generated;
	}
}
