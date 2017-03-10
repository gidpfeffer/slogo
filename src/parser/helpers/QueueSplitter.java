package parser.helpers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.command.TreeNode;

public class QueueSplitter {
	Queue<TreeNode> queue;
	Queue<TreeNode> newQueue;
	List<Queue<TreeNode>> list;
	
	public QueueSplitter(Queue<TreeNode> queue){
		this.queue = new LinkedList<>(queue);
		list = new ArrayList<>();
		newQueue = new LinkedList<>();
		fix();
		remakeQueue();
	}
	
	private void fix(){
		Queue<TreeNode> q = new LinkedList<>();
		while(!queue.isEmpty()){
			TreeNode t = queue.remove();
			q.add(t);
			if(t.isRoot()){
				addToList(q);
				q = new LinkedList<>();
			}
		}
	}
	
	private void remakeQueue(){
		for(Queue<TreeNode> q : list){
			while(!q.isEmpty()){
				newQueue.add(q.remove());
			}
		}
	}
	
	private void addToList(Queue<TreeNode> q){
		list.add(0,q);
	}
	
	public Queue<TreeNode> getQueue(){
		return newQueue;
	}

}
