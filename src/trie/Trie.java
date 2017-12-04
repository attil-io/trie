package trie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Trie<E> {

	private static class Node<E> {
		private Map<E, Node<E>> children = new HashMap<>();
		
		Node<E> addChild(E child) {
			children.putIfAbsent(child, new Node<E>());
			return children.get(child);
		}
		
		Node<E> getChild(E child) {
			return children.get(child);
		}
		
		Node<E> removeChild(E child) {
			return children.remove(child);
		}
		
		boolean hasAtMostOneChild() {
			return children.size() < 2;
		}
	}
	
	private Node<E> root = new Node<>();
	
	public boolean contains(Collection<E> word) {
		Node<E> node = root;
		for (E ch: word) {
			Node<E> next = node.getChild(ch);
			if (next == null) {
				return false;
			} else {
				node = next;
			}
		}
		return true;
	}

	public void add(Collection<E> word) {
		Node<E> node = root;
		for (E ch: word) {
			Node<E> next = node.getChild(ch);
			if (next == null) {
				next = node.addChild(ch);
			}
			node = next;
		}
	}

	public void remove(Collection<E> word) {
		Node<E> node = root;
		Node<E> lastFork = root;
		E childAtLastFork = word.iterator().hasNext() ? word.iterator().next() : null;
		for (E ch: word) {
			if (!node.hasAtMostOneChild()) {
				lastFork = node;
				childAtLastFork = ch;
			}
			node = node.getChild(ch);
			if (node == null) {
				break;
			}
		}
		lastFork.removeChild(childAtLastFork);
	}

}
