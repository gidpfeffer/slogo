package parser.reflection;

import java.lang.reflect.Constructor;
import java.util.List;

import controller.SLogoException;
import model.command.TreeNode;
import model.turtle.State;

public class ClassGenerator {
	private TreeNode generated;
	private PackageLocationHandler PLH;
	private TurtleCommandHandler TCH;
	
	public ClassGenerator(){
		PLH = new PackageLocationHandler();
		TCH = new TurtleCommandHandler();
	}
		
	public void generate(String className, List<TreeNode> list, State t) {
			Class<?> clazz = getClass(className);
			makeAdvancedClass(clazz, list, t, TCH.isTurtleCommand(className));
	}
	
	protected Class<?> getClass(String className){
		Class<?> clazz;
		try {
			clazz = Class.forName(PLH.getLoc(className) + className);
			return clazz;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new SLogoException("invalid syntax: " + className);
	}
	
	public void generate(String className, Double num, State t) {
		Class<?> clazz = getClass(className);
		makeAdvancedClass(clazz, num, t, TCH.isTurtleCommand(className));
	}

	private void makeAdvancedClass(Class<?> clazz, List<TreeNode>list, State t, boolean isT) {
		try {
			if(isT){
				Constructor<?> ctor = clazz.getDeclaredConstructor(List.class, State.class);
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
	
	private void makeAdvancedClass(Class<?> clazz, Double num, State t, boolean isT) {
		try{
			if(isT){
				Constructor<?> ctor = clazz.getDeclaredConstructor(double.class, State.class);
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
