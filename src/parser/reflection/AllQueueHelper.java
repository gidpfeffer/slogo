package parser.reflection;

import java.util.LinkedList;
import java.util.Queue;

import model.command.TreeNode;

public class AllQueueHelper {
	Queue<TreeNode> fixedQueue;

	public AllQueueHelper(Queue<TreeNode> q){
		fixedQueue = new LinkedList<>();
		fix(new LinkedList<>(q));
	}
	
	private void fix(Queue<TreeNode> q){
		while(!q.isEmpty()){
			TreeNode cur = q.remove();
			if(cur.isRoot()){
				fixedQueue.add(cur);
			}
		}
	}
	
	public Queue<TreeNode> getQueue(){
		return fixedQueue;
	}
}
