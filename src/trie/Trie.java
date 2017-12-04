package trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

	private static class Node {
		public final Character value;
		private Map<Character, Node> children = new HashMap<Character, Trie.Node>();
		
		Node() {
			value = null;
		}
		
		Node(char value) {
			this.value = value;
		}
		
		Node addChild(char child) {
			children.putIfAbsent(child, new Node(child));
			return children.get(child);
		}
		
		Node getChild(char child) {
			return children.get(child);
		}
		
		Node removeChild(char child) {
			return children.remove(child);
		}
		
		boolean hasAtMostOneChild() {
			return children.size() < 2;
		}
	}
	
	private Node root = new Node();
	
	public boolean contains(String word) {
		Node node = root;
		for (char ch: word.toCharArray()) {
			Node next = node.getChild(ch);
			if (next == null) {
				return false;
			} else {
				node = next;
			}
		}
		return true;
	}

	public void add(String word) {
		Node node = root;
		for (char ch: word.toCharArray()) {
			Node next = node.getChild(ch);
			if (next == null) {
				next = node.addChild(ch);
			}
			node = next;
		}
	}

	public void remove(String word) {
		Node node = root;
		Node lastFork = root;
		int lastForkPos = 0;
		int pos = 0;
		for (char ch: word.toCharArray()) {
			if (!node.hasAtMostOneChild()) {
				lastFork = node;
				lastForkPos = pos;
			}
			node = node.getChild(ch);
			++pos;
			if (node == null) {
				break;
			}
		}
		lastFork.removeChild(word.charAt(lastForkPos));
	}

}
