package parser.queue_splitter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.command.TreeNode;

public class QueueSplitter {
	Queue<TreeNode> queue;
	List<Queue<TreeNode>> list;
	
	public QueueSplitter(Queue<TreeNode> queue){
		this.queue = new LinkedList<>(queue);
		list = new ArrayList<>();
		fix();
	}
	
	private void fix(){
		Queue<TreeNode> q = new LinkedList<>();
		while(!queue.isEmpty()){
			TreeNode t = queue.remove();
			q.add(t);
			if(t.isRoot()){
				addToList(q);
				q = new LinkedList();
			}
		}
	}
	
	private void addToList(Queue<TreeNode> q){
		list.add(0, q);
	}
	
	public List<Queue<TreeNode>> getList(){
		return list;
	}

}
