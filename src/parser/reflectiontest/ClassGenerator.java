package parser.reflectiontest;

import java.lang.reflect.Constructor;
import java.util.List;

import model.command.TreeNode;
import model.command.TurtleCommand;
import model.turtle.TurtleState;

public class ClassGenerator {
	private TreeNode generated;
	private PackageLocationHandler PLH;
	private TurtleCommandHandler TCH;
	
	public ClassGenerator(){
		PLH = new PackageLocationHandler();
		TCH = new TurtleCommandHandler();
	}
		
	public void generate(String className, List<TreeNode> list, TurtleState t) {
			Class<?> clazz = getClass(className);
			makeAdvancedClass(clazz, list, t, TCH.isTurtleCommand(className));
	}
	
	private Class<?> getClass(String className){
		Class<?> clazz;
		try {
			clazz = Class.forName(PLH.getLoc(className) + className);
			return clazz;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new IllegalStateException("invalid class name");
	}
	
	public void generate(String className, Double num, TurtleState t) {
		Class<?> clazz = getClass(className);
		makeAdvancedClass(clazz, num, t, TCH.isTurtleCommand(className));
	}

	private void makeAdvancedClass(Class<?> clazz, List<TreeNode>list, TurtleState t, boolean isT) {
		try {
			if(isT){
				Constructor<?> ctor = clazz.getDeclaredConstructor(List.class, TurtleState.class);
				Object o = ctor.newInstance(list, t);
				generated = (TreeNode) clazz.cast(o);
			}
			else{
				Constructor<?> ctor = clazz.getDeclaredConstructor(List.class);
				Object o = ctor.newInstance(list);
				generated = (TreeNode) clazz.cast(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeAdvancedClass(Class<?> clazz, Double num, TurtleState t, boolean isT) {
		try{
			if(isT){
				Constructor<?> ctor = clazz.getDeclaredConstructor(double.class, TurtleState.class);
				Object o = ctor.newInstance(num, t);
				generated = (TreeNode) clazz.cast(o);
			}
			else{
				Constructor<?> ctor = clazz.getDeclaredConstructor(double.class);
				Object o = ctor.newInstance(num);
				generated = (TreeNode) clazz.cast(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TreeNode getGenerated(){
		return generated;
	}
}
