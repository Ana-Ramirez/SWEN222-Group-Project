package saveLoad;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;

public class SaveLoadUnitTests {
	
	@Test
	public void testSaveLoad() {
		String input = "Lewis sucks (:";
		TestClass testIn = new TestClass(input);
		Save save = new Save(testIn);
		save.saveGame();
		Load load = new Load("game_save");
		TestClass testOut = (TestClass)load.loadGame();
		String output = testOut.getStr();
		
		assertEquals(input, output);
	}
	
	//======================  external tests  ======================
	/**
	 * Canceling load
	 */
	@Test (expected = NullPointerException.class)
	public void externalTest1(){
		Load load = new Load(null);
		TestClass testout = (TestClass)load.loadGame();
	}
	
	/**
	 * Test old game overwritten when saved over
	 */
	@Test
	public void externalTest2(){
		String oldInput = "Tim sucks";
		TestClass testIn = new TestClass(oldInput);
		Save save = new Save(testIn);
		save.saveGame();
		
		String newInput = "Lewis is kewl";
		TestClass testInNew = new TestClass(oldInput);
		Save saveNew = new Save(testInNew);
		save.saveGame();
		
		Load load = new Load("game_save");
		TestClass testOut = (TestClass)load.loadGame();
		String output = testOut.getStr();
		
		assertEquals(newInput, output);
	}
	
	
}
class TestClass implements Serializable{

	private String str;
	
	public TestClass(String str){
		this.str = str;
	}
	
	public String getStr() {
		return str;
	}
}
