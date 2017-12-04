package trie;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class TrieTest {

	@Test
	public void emptyTrieShouldNotContainWord() {
		Trie trie = new Trie();
		assertThat(trie.contains("hello"), is(false));
	}

	@Test
	public void wordShouldBeContainedIfAdded() {
		Trie trie = new Trie();
		trie.add("hello");
		assertThat(trie.contains("hello"), is(true));
	}

	@Test
	public void wordShouldNotBeContainedIfRemoved() {
		Trie trie = new Trie();
		trie.add("hello");
		trie.remove("hello");
		assertThat(trie.contains("hello"), is(false));
	}

	@Test
	public void removingNonExistingWordShouldNotCauseException() {
		Trie trie = new Trie();
		trie.remove("hello");
		assertThat(trie.contains("hello"), is(false));
	}

	@Test
	public void removingOneWordShouldNotRemoveAnother() {
		Trie trie = new Trie();
		trie.add("hello");
		trie.add("help");
		trie.remove("help");
		assertThat(trie.contains("help"), is(false));
		assertThat(trie.contains("hello"), is(true));
	}

}
