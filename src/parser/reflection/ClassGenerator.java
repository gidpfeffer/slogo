package parser.reflection;

import java.lang.reflect.Constructor;
import java.util.List;

import controller.SLogoException;
import model.command.TreeNode;
import model.turtle.State;

public class ClassGenerator {
	private PackageLocationHandler PLH;
	private TurtleCommandHandler TCH;
	
	public ClassGenerator(){
		PLH = new PackageLocationHandler();
		TCH = new TurtleCommandHandler();
	}
	
	public TreeNode generate(String className, List<TreeNode> list, State t) {
		Class<?> clazz = getClass(className);
		Constructor<?> ctor = makeConstructor(clazz, List.class, TCH.isTurtleCommand(className));
		return makeCommandClass(clazz, list, t, TCH.isTurtleCommand(className), ctor);
	}
	
	public TreeNode generate(String className, Double num, State t) {
		Class<?> clazz = getClass(className);
		Constructor<?> ctor = makeConstructor(clazz, double.class, TCH.isTurtleCommand(className));
		return makeConstantClass(clazz, num, t, TCH.isTurtleCommand(className), ctor);
	}
	
	private TreeNode makeConstantClass(Class<?> clazz, double num, State t,
			boolean isTurtleCommand, Constructor<?> ctor){
		try{
			if(isTurtleCommand){
				return (TreeNode) clazz.cast(ctor.newInstance(num, t));
			}
			else{
				return (TreeNode) clazz.cast(ctor.newInstance(num));
			}
		} catch (Exception e){
			throw new SLogoException("invalid syntax: " + clazz.getClass());
		}
	}
	
	private TreeNode makeCommandClass(Class<?> clazz, List<TreeNode> list, State t,
			boolean isTurtleCommand, Constructor<?> ctor){
		try{
			if(isTurtleCommand){
				return (TreeNode) clazz.cast(ctor.newInstance(list, t));
			}
			else{
				return (TreeNode) clazz.cast(ctor.newInstance(list));
			}
		} catch (Exception e){
			throw new SLogoException("invalid syntax: " + clazz.getClass());
		}
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
	
	private Constructor<?> makeConstructor(Class<?> clazz, Class theClass, boolean isT) {
		Constructor<?> ctor;
		try {
			if(isT){
				ctor = clazz.getDeclaredConstructor(theClass, State.class);
			}
			else{
				ctor = clazz.getDeclaredConstructor(theClass);
			}
			return ctor;
		} 
		catch (Exception e) {
			throw new SLogoException("Invalid command");
		}
	}
}
