import org._3pq.jgrapht.Graph;
//import org._3pq.jgrapht.edge.*;
import org._3pq.jgrapht.graph.DefaultDirectedGraph;
import org._3pq.jgrapht.graph.SimpleGraph;

import java.util.*;

import aterm.pure.*;
import gasel2.data.*;
import gasel2.data.types.*;

public final class Gasel2 {
  private dataFactory factory;
  private Graph globalGraph;

  public Gasel2(dataFactory factory) {
    this.factory = factory;
    this.globalGraph = new SimpleGraph();
  }

  public dataFactory getDataFactory() {
    return factory;
  }
  
  private Graph getGraph() {
    return globalGraph;
  }
 
  public static void main( String[] args ) {
    Gasel2 t = new Gasel2(dataFactory.getInstance(new PureFactory()));
    t.run();
  }

  %vas {
  module data
	imports public

	sorts Atom BondType Bond BondList
      
	abstract syntax	
    empty      -> Atom
		C(n:int)   -> Atom
		arC(n:int) -> Atom
		O(n:int)   -> Atom
		arO(n:int) -> Atom
		H(n:int)   -> Atom
		e(n:int)   -> Atom
	
    bond(bondType:BondType,source:Atom,target:Atom) -> Bond
		concBond( Bond* ) -> BondList  

		none   -> BondType
		simple -> BondType
		double -> BondType
		triple -> BondType
		arom   -> BondType
  }

  %typeterm StateList {
    implement { List }
    equals(t1,t2) { t1.equals(t2) } 
  }

  %typeterm State {
    implement { State }
    equals(t1,t2) { t1.equals(t2) } 
  }

  %op State rad(bondtype:BondType,atom:Atom,subterm:StateList) {
    is_fsym(t)  { t instanceof State }
    get_slot(bondtype,t) { t.getBondType() }
    get_slot(atom,t) { t.getAtom() }
    get_slot(subterm,t)  { computeSuccessors(getGraph(), t) }
  }
 
  %oparray StateList conc( State* ) {
    is_fsym(t)       { t instanceof List }
    make_empty(n)    { new ArrayList(n)       }
    make_append(e,l) { myAdd(e,(ArrayList)l)   }
    get_element(l,n) { (State)l.get(n)        }
    get_size(l)      { l.size()                }
  }

  private List myAdd(Object e,List l) {
    l.add(e);
    return l;
  }
  
  private Map labelMap = new HashMap();

  /*
   * add a simple bond to the graph
   * the label is stored in a hashmap
   */
  private void addBond(Atom v1, Atom v2, BondType type) {
    org._3pq.jgrapht.Edge e = new org._3pq.jgrapht.edge.UndirectedEdge(v1,v2);
    labelMap.put(e,`bond(type,v1,v2));
    //System.out.println("add label( " + e + " ) = " + labelMap.get(e)); 
    getGraph().addEdge(e);
  }

  private Bond getBond(org._3pq.jgrapht.Edge e) {
    Bond bond = (Bond)labelMap.get(e);
    if(bond==null) {
      throw new RuntimeException("no associated bond to: " + e); 
    }
    //System.out.println("label( " + e + " ) = " + bond); 
    return bond;
  }

  private BondType getBondType(org._3pq.jgrapht.Edge e) {
    return getBond(e).getBondType();
  }

  /*
   * given a node, compute all its immediate successors with the bond information
   */
  private List computeSuccessors(Graph g, State state) {
    Atom atom = state.getAtom();
    BondList path = state.getPath();
    List res = new LinkedList();
    for(Iterator it=g.edgesOf(atom).iterator() ; it.hasNext() ; ) {
      org._3pq.jgrapht.Edge e = (org._3pq.jgrapht.Edge)it.next();
      Bond b = getBond(e);
      if(path.indexOf(b,0) < 0) { // bond does not occur in path
        Atom successor = (Atom)e.oppositeVertex(atom);
        res.add(new State(`manyBondList(b,path),getBondType(e),successor));
      }
    }
    return res;
  }

  public void run() {
    Graph g = getGraph();

    Atom v1 = `e(1);
    Atom v2 = `C(2);
    Atom v3 = `C(3);
    Atom v4 = `C(4);
    Atom v5 = `C(5);

    // add the vertices
    g.addVertex( v1 );
    g.addVertex( v2 );
    g.addVertex( v3 );
    g.addVertex( v4 );
    g.addVertex( v5 );

    // add edges to create a circuit
    addBond( v1, v2, `simple() );
    addBond( v2, v3, `simple() );
    addBond( v3, v4, `simple() );
    addBond( v4, v5, `simple() );
    addBond( v5, v3, `simple() );

    System.out.println("g = " + g);
    System.out.println("edges of C3 = " + g.edgesOf(v3));
    System.out.println("successors of C3 = " + computeSuccessors(g,new State(`concBond(bond(simple(),v2,v3)),`simple(),v3)));
   
    State state = new State(`emptyBondList(),`none(),v1);

    %match(State state) {
      // e C
      rad(_, e[], conc(_*,rad(simple(), C[],subterm),_*)) -> {
        System.out.println("Bingo 1: " + subterm);
      }
      
      // e C C
      rad(_,e[], conc(_*, rad(simple(),C[],
                          conc(_*, rad(simple(),C[],subterm),_*)),_*)) -> {
        System.out.println("Bingo 2: " + subterm);
      }
      
      // e C C C
      rad(_,e[], conc(_*, rad(simple(),C[],
                          conc(_*, rad(simple(),C[],
                          conc(_*, rad(simple(),C[],subterm),_*)),_*)),_*)) -> {
        System.out.println("Bingo 3: " + subterm);
      }
      
    }

  }
}

class State {
  private BondList path;
  private Atom atom;
  private BondType bond;

  public State(BondList path, BondType bond, Atom atom) {
    this.path = path; 
    this.bond = bond;
    this.atom = atom;
  }

  public BondType getBondType() {
    return bond;
  }

  public BondList getPath() {
    return path;
  }

  public Atom getAtom() {
    return atom;
  }

  public String toString() {
    return "(" + getPath() + "," + getBondType() + "," + getAtom() + ")";
  }
}

