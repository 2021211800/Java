package 蓝山02;

import org.junit.Test;

public class LinkedList<T> {
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>(1);
		for(int i = 2; i < 10; i++)
			list.add(i);
		System.out.println("反转前");
		list.traverse();//1 2 3 4 5 6 7 8 9
		list.reverse();
		System.out.println("反转后");
		list.traverse();//9 8 7 6 5 4 3 2 1
		list.delete(10);//删除的对象不存在
		list.search(10);//查找的对象不存在
		list.add(10);//添加成功
		list.delete(10);//删除成功
	}
	
	int size;
	Node<T> head;
	
	public LinkedList(){
		
	}
	
	public LinkedList(T headInfo) {
		this.head = new Node<T>(headInfo);
		size = 1;
	}
	
	public void add(T info) {
		if(size == 0) {
			head = new Node<T>(info);
			size++;
			return;
		}
		Node<T> temp = head;
		Node<T> node = new Node<T>(info);
		while(temp.next != null) temp = temp.next;
		temp.next = node;
		node.pre = temp;
		System.out.println("添加成功");
		size++;	
	}
	
	public void delete(T info) {
		try {
			if(size == 0) throw new Exception("链表为空");
			Node<T> temp = head;
			while(temp != null) {
				if(temp.info == info) {
					if(temp.pre == null) {
						head = temp.next;
						temp.next.pre = null;
					}else if(temp.next == null) {
						temp.pre.next = null;						
					}else {
						temp.pre.next = temp.next;
						temp.next.pre = temp.pre;	
					}
					System.out.println("删除成功");
					size--;
					break;
				}
				temp = temp.next;
			}
			if(temp == null) throw new Exception("删除的对象不存在");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public Node<T> search(T info) {
		try {
			if(size == 0) throw new Exception("链表为空");
			Node<T> temp = head;
			while(temp != null) {
				if(temp.info == info) {
					return temp;
				}
				temp = temp.next;
			}
			if(temp == null) throw new Exception("查找的对象不存在");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void traverse() {
		try{
			if(size == 0) throw new Exception("链表为空");
			Node<T> temp = head;
			while(temp != null) {
				System.out.println(temp.info);
				temp = temp.next;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void reverse() {
		try {
			if(size == 0) throw new Exception("链表为空");
			Node<T> temp = head;
			while(temp != null) {
				Node<T> next = temp.next;
				temp.next = temp.pre;
				temp.pre = next;
				if(temp.pre == null) head = temp;
				temp = next;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void test() {
		
	}
}

class Node<T> {
	Node<T> pre;
	Node<T> next;
	T info;
	
	public Node(T info) {
		this.info = info;
	}
} 