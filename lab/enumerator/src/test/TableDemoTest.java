package test;


import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Ignore;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import examples.adt.SimpleTable;
import examples.adt.TableDemo;
import examples.adt.table.types.Elem;
import examples.adt.table.types.Key;
import examples.adt.table.types.Table;
import examples.adt.table.types.Val;
import tom.library.enumerator.Enumeration;
import tom.library.theory.Enum;
import tom.library.theory.RandomCheck;
import tom.library.theory.RandomForAll;
import tom.library.theory.TomCheck;
import tom.library.theory.TomForAll;

@RunWith(TomCheck.class)
public class TableDemoTest {
	@Enum public static Enumeration<Table> enumTable = Table.getEnumeration();
	@Enum public static Enumeration<Elem> enumElem = Elem.getEnumeration();
	@Enum public static Enumeration<Key> enumKey = Key.getEnumeration();
	@Enum public static Enumeration<Val> enumVal = Val.getEnumeration();
	
	@Ignore
	@Theory
	public void testAddValue(
			@TomForAll @RandomCheck(minSampleSize=20, sampleSize = 30) Table table, 
			@RandomForAll(sampleSize = 30) Key key, 
			@RandomForAll(sampleSize = 30) Val val) throws Exception {
		Table result = TableDemo.evaluate(table);
		Table add = TableDemo.add(result, key, val);
		Val v = TableDemo.value(add, key);
		assertThat(v, equalTo(val));
	}	
	
	@Ignore
	@Theory
	public void testAddValueWith2keys(
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Table table, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key2, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Val val) throws Exception {
		Table result = TableDemo.evaluate(table);
		
		assumeThat(result.isempty(), equalTo(false));
		assumeThat(TableDemo.has(result, key2), equalTo(true));
		assumeThat(key, not(equalTo(key2)));
		
		Table add = TableDemo.add(table, key, val);
		Val v = TableDemo.value(add, key2);
		Val v2 = TableDemo.value(table, key2);
		assertThat(v, equalTo(v2));
	}
	
	@Ignore
	@Theory
	public void testDeleteAdd(
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Table table, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key,  
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Val val) throws Exception {
		Table result = TableDemo.evaluate(table);
		assumeThat(TableDemo.has(result, key), equalTo(false));
		Table t = TableDemo.remove(TableDemo.add(result, key, val), key);
		assertThat(t, equalTo(result));
	}
	
	@Ignore
	@Theory
	public void testDeleteAddWith2Keys(
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Table table, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key2, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Val val) throws Exception {
		
		Table result = TableDemo.evaluate(table);
		assumeThat(result.isempty(), equalTo(false));
		assumeThat(TableDemo.has(result, key2), equalTo(true));
		assumeThat(key, not(equalTo(key2)));
		
		Table t = TableDemo.remove(TableDemo.add(result, key, val), key2);
		Table t2 = TableDemo.add(TableDemo.remove(result, key2), key, val);
		assertThat(t, equalTo(t2));
	}
	
	@Ignore
	@Theory
	public void testHas(
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Table table, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Val val) throws Exception {
		
		Table result = TableDemo.evaluate(table);
		Table t = TableDemo.add(result, key, val);
		assertThat(TableDemo.has(t, key), equalTo(true));
	}
	
	@Ignore
	@Theory
	public void testHasWith2Keys(
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Table table, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key2, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Val val) throws Exception {
		Table result = TableDemo.evaluate(table);
		assumeThat(result.isempty(), equalTo(false));
		assumeThat(key, not(equalTo(key2)));
		
		assertThat(TableDemo.has(TableDemo.add(result, key, val), key2), equalTo(TableDemo.has(result, key2)));
	}
	
	@Ignore
	@Theory
	public void testEvaluate(
			@TomForAll @RandomCheck(minSampleSize=980, sampleSize = 1000) Table table) throws Exception {
		System.out.println("table: " + table);
		Table result = TableDemo.evaluate(table);
		System.out.println("result: " + result);
	}
	
	// comparing two implementations
	@Ignore
	@Theory
	public void testComparisonAddValue(
			@TomForAll @RandomCheck(minSampleSize=20, sampleSize = 30) Table table, 
			@RandomForAll(sampleSize = 30) Key key, 
			@RandomForAll(sampleSize = 30) Val val) throws Exception {
		Table result = TableDemo.evaluate(table);
		Table add = TableDemo.add(result, key, val);
		Val v = TableDemo.value(add, key);
		assertThat("1",v, equalTo(val));
		
		SimpleTable st = new SimpleTable();
		st.fromTable(table);
		st.add(key, val);
		Val v2 = st.getValue(key);
		assertThat("2",v2, equalTo(val));
		
		assertThat(TableDemo.size(add), equalTo((Integer) st.size()));
	}
	
	@Ignore
	@Theory
	public void testComparisonAddValueWith2keys(
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Table table, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key2, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Val val) throws Exception {
		Table result = TableDemo.evaluate(table);
		
		assumeThat(result.isempty(), equalTo(false));
		assumeThat(TableDemo.has(result, key2), equalTo(true));
		assumeThat(key, not(equalTo(key2)));
		
		Table add = TableDemo.add(result, key, val);
		Val v = TableDemo.value(add, key2);
		Val v2 = TableDemo.value(result, key2);
		assertThat("Table 1", v, equalTo(v2));
		
		SimpleTable st = new SimpleTable();
		st.fromTable(table);
		st.add(key, val);
		Val vSt1 = st.getValue(key2);
		
		SimpleTable st2 = new SimpleTable();
		st2.fromTable(table);
		Val vSt2 = st2.getValue(key2);
		
		assertThat("Table 2", vSt1, equalTo(vSt2));
	}
	
	@Ignore
	@Theory
	public void testComparisonDeleteAddWith2Keys(
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Table table, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key2, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Val val) throws Exception {
		
		Table result = TableDemo.evaluate(table);
		assumeThat(result.isempty(), equalTo(false));
		assumeThat(TableDemo.has(result, key2), equalTo(true));
		assumeThat(key, not(equalTo(key2)));
		
		Table t = TableDemo.remove(TableDemo.add(result, key, val), key2);
		Table t2 = TableDemo.add(TableDemo.remove(result, key2), key, val);
		assertThat("1",t, equalTo(t2));
		
		SimpleTable st = new SimpleTable();
		st.fromTable(result);
		st.add(key, val);
		st.remove(key2);
		
		SimpleTable st2 = new SimpleTable();
		st2.fromTable(result);
		st2.remove(key2);
		st2.add(key, val);
		
		assertThat(st2.size(), is(equalTo(st.size())));
		for (Elem e : st.getData()) {
			assertThat("2",st2.has(e.getKey()), is(equalTo(true)));
		}
	}
	
	
	@Theory
	public void testComparisonDeleteAdd(
			@TomForAll @RandomCheck(minSampleSize=990, sampleSize = 1000) Table table, 
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Key key,  
			@TomForAll @RandomCheck(minSampleSize=0, sampleSize = 10) Val val) throws Exception {
		Table result = TableDemo.evaluate(table);
		
		assumeThat(TableDemo.has(result, key), equalTo(false));
		
		Table t = TableDemo.remove(TableDemo.add(result, key, val), key);
		assertThat("1",t, equalTo(result));
		
		SimpleTable st = new SimpleTable();
		st.fromTable(result);
		st.add(key, val);
		st.remove(key);
		assertThat("2", st.toTable(), equalTo(result));
		
		assertThat("3",st.toTable(), equalTo(t));
	}
}
