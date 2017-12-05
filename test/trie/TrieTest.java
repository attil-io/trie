package trie;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TrieTest {

	private Trie<Character> underTest;
	
	@Before
	public void setUp() {
		underTest = new Trie<>();
	}
	
	@Test
	public void emptyWordShouldNotBeContainedByDefault() {
		assertThat(underTest.contains(string2Coll("")), is(false));
	}	
	
	@Test
	public void emptyTrieShouldNotContainWord() {
		assertThat(underTest.contains(string2Coll("hello")), is(false));
	}

	@Test
	public void addZeroLengthWordShouldNotCauseException() {
		underTest.add(string2Coll(""));
		assertThat(underTest.contains(string2Coll("")), is(true));
	}
	
	@Test
	public void wordShouldBeContainedIfAdded() {
		underTest.add(string2Coll("hello"));
		assertThat(underTest.contains(string2Coll("hello")), is(true));
	}

	@Test
	public void wordAddedTwiceShouldBeContained() {
		underTest.add(string2Coll("hello"));
		underTest.add(string2Coll("hello"));
		assertThat(underTest.contains(string2Coll("hello")), is(true));
	}
	
	@Test
	public void wordShouldNotBeContainedIfRemoved() {
		underTest.add(string2Coll("hello"));
		underTest.remove(string2Coll("hello"));
		assertThat(underTest.contains(string2Coll("hello")), is(false));
	}

	@Test
	public void wordAddedTwiceAndRemovedShouldBeContained() {
		underTest.add(string2Coll("hello"));
		underTest.add(string2Coll("hello"));
		underTest.remove(string2Coll("hello"));
		assertThat(underTest.contains(string2Coll("hello")), is(false));
	}	
	
	@Test
	public void removingNonExistingWordShouldNotCauseException() {
		underTest.remove(string2Coll("hello"));
		assertThat(underTest.contains(string2Coll("hello")), is(false));
	}

	@Test
	public void removingOneWordShouldNotRemoveAnother() {
		underTest.add(string2Coll("hello"));
		underTest.add(string2Coll("help"));
		underTest.remove(string2Coll("help"));
		assertThat(underTest.contains(string2Coll("help")), is(false));
		assertThat(underTest.contains(string2Coll("hello")), is(true));
	}

	@Test
	public void removingSubstringShouldNotRemoveContainerString() {
		underTest.add(string2Coll("hello"));
		underTest.add(string2Coll("hell"));
		underTest.remove(string2Coll("hell"));
		assertThat(underTest.contains(string2Coll("hell")), is(false));
		assertThat(underTest.contains(string2Coll("hello")), is(true));
	}

	@Test
	public void removingContainerStringShouldNotRemoveSubString() {
		underTest.add(string2Coll("hello"));
		underTest.add(string2Coll("hell"));
		underTest.remove(string2Coll("hello"));
		assertThat(underTest.contains(string2Coll("hell")), is(true));
		assertThat(underTest.contains(string2Coll("hello")), is(false));
	}

	@Test
	public void removingZeroLengthWordShouldNotCauseException() {
		underTest.remove(string2Coll(""));
		assertThat(underTest.contains(string2Coll("")), is(false));
	}	
	
	private static Iterable<Character> string2Coll(String str) {
		List<Character> ret = new ArrayList<>(str.length());
		for (char ch: str.toCharArray()) {
			ret.add(ch);
		}
		return ret;
	}
}
