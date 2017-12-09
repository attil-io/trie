package trie;

import java.util.HashMap;
import java.util.Map;

public class Trie<E> {

	private static class Node<E> {
		private Map<E, Node<E>> children = new HashMap<>();
		private boolean isLeaf = false;
		
		void setLeaf(boolean leaf) {
			isLeaf = leaf;
		}
		
		boolean isLeaf() {
			return isLeaf;
		}
		
		Node<E> addChild(E child) {
			children.putIfAbsent(child, new Node<E>());
			return children.get(child);
		}
		
		Node<E> getChild(E child) {
			return children.get(child);
		}
		
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Node)) {
				return false;
			}
			Node<?> node = (Node<?>) o;
			return (this.isLeaf == node.isLeaf) && (children.equals(node.children)); 
		}
	}
	
	private Node<E> root = new Node<>();
	
	public boolean contains(Iterable<E> word) {
		Node<E> node = root;
		for (E ch: word) {
			Node<E> next = node.getChild(ch);
			if (next == null) {
				return false;
			} else {
				node = next;
			}
		}
		return node.isLeaf();
	}

	public void add(Iterable<E> word) {
		Node<E> node = root;
		for (E ch: word) {
			Node<E> next = node.getChild(ch);
			if (next == null) {
				next = node.addChild(ch);
			}
			node = next;
		}
		node.setLeaf(true);
	}

	public void remove(Iterable<E> word) {
		Node<E> node = root;
		for (E ch: word) {
			node = node.getChild(ch);
			if (node == null) {
				return;
			}
		}
		node.setLeaf(false);
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Trie)) {
			return false;
		}
		Trie<?> otherTrie = (Trie<?>) other;
		return root.equals(otherTrie.root);
	}

}
