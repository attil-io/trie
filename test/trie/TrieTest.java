package trie;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

public class TrieTest {

	@Test
	public void emptyTrieShouldNotContainWord() {
		Trie<Character> trie = new Trie<>();
		assertThat(trie.contains(string2Coll("hello")), is(false));
	}

	@Test
	public void wordShouldBeContainedIfAdded() {
		Trie<Character> trie = new Trie<>();
		trie.add(string2Coll("hello"));
		assertThat(trie.contains(string2Coll("hello")), is(true));
	}

	@Test
	public void wordShouldNotBeContainedIfRemoved() {
		Trie<Character> trie = new Trie<>();
		trie.add(string2Coll("hello"));
		trie.remove(string2Coll("hello"));
		assertThat(trie.contains(string2Coll("hello")), is(false));
	}

	@Test
	public void removingNonExistingWordShouldNotCauseException() {
		Trie<Character> trie = new Trie<>();
		trie.remove(string2Coll("hello"));
		assertThat(trie.contains(string2Coll("hello")), is(false));
	}

	@Test
	public void removingOneWordShouldNotRemoveAnother() {
		Trie<Character> trie = new Trie<Character>();
		trie.add(string2Coll("hello"));
		trie.add(string2Coll("help"));
		trie.remove(string2Coll("help"));
		assertThat(trie.contains(string2Coll("help")), is(false));
		assertThat(trie.contains(string2Coll("hello")), is(true));
	}

	private static Collection<Character> string2Coll(String str) {
		Collection<Character> ret = new ArrayList<>(str.length());
		for (char ch: str.toCharArray()) {
			ret.add(ch);
		}
		return ret;
	}
}
