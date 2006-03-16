/*
 * Copyright (c) 2004-2006, INRIA
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 * 	- Redistributions of source code must retain the above copyright
 * 	notice, this list of conditions and the following disclaimer.  
 * 	- Redistributions in binary form must reproduce the above copyright
 * 	notice, this list of conditions and the following disclaimer in the
 * 	documentation and/or other materials provided with the distribution.
 * 	- Neither the name of the INRIA nor the names of its
 * 	contributors may be used to endorse or promote products derived from
 * 	this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package analysis;

import analysis.language.*;
import analysis.language.types.*;

import tom.library.strategy.mutraveler.*;

import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.Visitable;
import jjtraveler.VisitFailure;
import java.util.*;


import org._3pq.jgrapht.*;
import org._3pq.jgrapht.edge.*;
import org._3pq.jgrapht.graph.DefaultDirectedGraph;

public class Analyser{

	%include {mutraveler.tom }
	%include {language/language.tom}


	//D�finition des types

	%typeterm ControlFlowGraphList {
		implement { List }
		equals(t1,t2) { t1.equals(t2) } 
		visitor_fwd { analysis.language.languageBasicStrategy }
	}

	%typeterm VariableList {
		implement { List }
		equals(t1,t2) { t1.equals(t2) } 
		visitor_fwd { analysis.language.languageBasicStrategy }
	}

	%typeterm ControlFlowGraph {
		implement {ControlFlowGraph}
		equals(t1,t2) { t1.equals(t2) } 
		visitor_fwd { analysis.language.languageBasicStrategy }
	}


	%typeterm NodeEmilie {
		implement {NodeEmilie}
		equals(t1,t2) { t1.equals(t2) } 
		visitor_fwd { analysis.language.languageBasicStrategy }
	}

	//D�finition des constructeurs

	%op ControlFlowGraph conc(root:ControlFlowGraph,subterm:ControlFlowGraphList) {
		is_fsym(t)  { t instanceof ControlFlowGraph }
		make(root,subterm) { new ControlFlowGraph(root, subterm) }
	}


	%op ControlFlowGraph graph(node:NodeEmilie){
		is_fsym(t)  { t instanceof ControlFlowGraph }
		get_slot(node,t) { t.getRoot() }
		make(node) { new ControlFlowGraph(node) }
	}


	%oparray ControlFlowGraphList list( ControlFlowGraph* ) {
		is_fsym(t)       { t instanceof List }
		make_empty(n)    { new ArrayList(n)       }
		make_append(e,l) { myAdd(e,(ArrayList)l)  }
		get_element(l,n) { (ControlFlowGraph)(l.get(n))  }
		get_size(l)      { l.size()               }
	}

	%oparray VariableList varList( Variable* ) {
		is_fsym(t)       { t instanceof List }
		make_empty(n)    { new ArrayList(n)       }
		make_append(e,l) { myAdd(e,(ArrayList)l)  }
		get_element(l,n) { (Variable)(l.get(n))  }
		get_size(l)      { l.size()               }
	}



	%op NodeEmilie NodeEmilie(node:Node){
		is_fsym(t)  { t instanceof NodeEmilie }
		make(node) { new NodeEmilie(node) }
	}


	/**
	  Classe provisoire d'encapsulation des Node
	  pour �viter le partage maximale des ATerms
	 */

	public class NodeEmilie{ 
		private Node node ;

		public NodeEmilie(Node node){this.node=node;}

		public String toString(){return node.toString();}

		public Node getNode(){return node;}
	}

	/**
	  Rep�rsentation du graphe de flot de contr�le
	  en utilisant la biblioth�que jgrapht
	 */

	class ControlFlowGraph extends DefaultDirectedGraph implements Visitable {

		private NodeEmilie root;
		private List subterms;

		public ControlFlowGraph(ControlFlowGraph first, List graphList)  {
			super();
			root = first.getRoot();
			addAllVertices(first.vertexSet());
			addAllEdges(first.edgeSet()); 

			// il faut ajouter les arretes qui relient first aux root des cfg suivants de subterm
			// Je cherche les feuilles du graphe first
			Iterator iter = first.vertexSet().iterator();
			ArrayList leaves = new ArrayList();
			while(iter.hasNext()){
				NodeEmilie vertex = (NodeEmilie) (iter.next());
				if (first.outDegreeOf(vertex)==0) leaves.add(vertex) ;
			}
			iter = graphList.iterator();
			while(iter.hasNext()){
				ControlFlowGraph next =  ((ControlFlowGraph)iter.next());
				addAllVertices(next.vertexSet());
				addAllEdges(next.edgeSet());
				Iterator iter2 = leaves.iterator();
				while(iter2.hasNext()){
					DirectedEdge e = new DirectedEdge(iter2.next(),next.getRoot());
					addEdge(e);
				}
			}
			iter = outgoingEdgesOf(root).iterator();
			subterms = new ArrayList();
			while(iter.hasNext()){
				NodeEmilie rootNeighbour = (NodeEmilie)(((Edge)iter.next()).getTarget());
				subterms.add(subGraph(rootNeighbour));
			}
		}


		public ControlFlowGraph subGraph(NodeEmilie startNode){
			ControlFlowGraph cfg = new ControlFlowGraph(startNode);
			cfg.addAllVertices(connectedNodes(startNode));
			cfg.addAllEdges(connectedEdges(startNode));
			Iterator iter = outgoingEdgesOf(startNode).iterator();
			List l = new ArrayList();
			while(iter.hasNext()){
				NodeEmilie rootNeighbour = (NodeEmilie)(((Edge)iter.next()).getTarget());
				l.add(subGraph(rootNeighbour));
			}
			cfg.setSubterms(l);
			return cfg;

		}

		public void setSubterms(List l){subterms=l;}

		public List connectedNodes(NodeEmilie startNode){
			Iterator iter = outgoingEdgesOf(startNode).iterator();
			List connectedNodes = new ArrayList();
			while(iter.hasNext()){
				NodeEmilie rootNeighbour = (NodeEmilie)(((Edge)iter.next()).getTarget());
				if(! connectedNodes.contains(rootNeighbour)){
					connectedNodes.add(rootNeighbour);
					connectedNodes.addAll(connectedNodes(rootNeighbour));
				}
			}
			return connectedNodes;
		}

		public List connectedEdges(NodeEmilie startNode){
			List connectedEdges = new ArrayList();
			List connectedNodes = connectedNodes(startNode);
			ListIterator iter = connectedNodes.listIterator();
			while(iter.hasNext()){
				NodeEmilie n = (NodeEmilie) iter.next();
				connectedEdges.addAll(outgoingEdgesOf(n));
			}
			return connectedEdges;
		}

		public ControlFlowGraph(NodeEmilie node){
			super();
			root = node;
			addVertex(node);
			subterms = new ArrayList();
		}


		public NodeEmilie getRoot(){
			return root;
		}  

		public int getChildCount() {
			return subterms.size();
		}

		public Visitable getChildAt(int i) {
			return (ControlFlowGraph) subterms.get(i);
		}

		public Visitable setChildAt(int i, Visitable child) {
			subterms.set(i,child);  
			return this;  
		}


		public boolean verify(VisitableVisitor temporalCond,NodeEmilie n){
			try{
				MuTraveler.init(temporalCond).visit(subGraph(n));
				return true;
			}catch(VisitFailure e){return false;}
		}

	}


	// Main de la classe

	public final static void main(String[] args) {
		Analyser test = new Analyser();
		test.run();
	}

	// M�thode de test des strat�gies

	public void run() {
		Variable var_x = `Name("x");
		Variable var_y = `Name("y");
		Variable var_z = `Name("z");

		InstructionList subject = `concInstruction(If(True,Nop(),Let(var_x,g(a),Let(var_y,g(Var(var_x)),Nop()))),Let(var_z,f(a,b),Nop()));
		VisitableVisitor rule = new Cfg();

		try {
			System.out.println("subject          = " + subject);
			ControlFlowGraph cfg = (ControlFlowGraph)(rule.visit(subject));
			System.out.println("correpsonding cfg = " + cfg);
			System.out.println("deadcode detection...........");

			// On recherche les noeuds qui correspondent a des assign
			Iterator iter = cfg.vertexSet().iterator();
			while(iter.hasNext()){
				NodeEmilie n = (NodeEmilie) (iter.next());
				Node nn = n.getNode();
				%match(Node nn){
					affect(var,_) -> {
						VisitableVisitor isNotUsedStrat = new IsNotUsed(`var);
						VisitableVisitor freeStrat = new Free(`var);
						//test de la cond temporel A(notUsed(var)Ufree(var)) au noeud nn du cfg
						if(cfg.verify(`mu(MuVar("x"),Choice(freeStrat,Sequence(isNotUsedStrat,All(MuVar("x"))))),n)) System.out.println("Variable "+`var+" not used");
					}
				}	
			} 	


		} catch (VisitFailure e) {
			System.out.println("reduction failed on: " + subject);
		}

	}

	// M�thode d'ajout d'un �l�ment dans une liste (utilis�e dans la d�finition des constructeurs de liste)

	private List myAdd(Object e,List l) {
		l.add(e);
		return l;
	}

	/**
	  D�finition des pr�dicats sur un node
	  ---------------------------------------------------------
	 */

	// Pr�dicat isUsed(v:Variable) qui teste si une variable est utilis�e au noeud racine d'un cfg

	class IsNotUsed extends languageBasicStrategy {

		Variable v;

		public IsNotUsed(Variable v) {
			super(`Identity());
			this.v=v;
		}

		public Visitable visit(Visitable arg) throws VisitFailure {
			if(arg instanceof ControlFlowGraph){
				visit(((ControlFlowGraph)arg).getRoot().getNode());
			} 
			if(arg instanceof Node){
				%match(Node arg) {

					beginIf(expr) -> {
						visit(`expr);
					} 
					affect(var,term) -> {
						visit(`term);
					}
					beginWhile(expr) -> {
						visit(`expr);
					}

				}
			}
			if(arg instanceof Term){
				%match(Term arg) {	
					f(arg1 , arg2) -> {
						visit(`arg1);
						visit(`arg2);
					} 

					g(arg) -> {
						visit(`arg);
					}

					Var(var) -> {
						if(`var.equals(v)) return `Fail().visit(arg);
					}

				} 
			}
			if(arg instanceof Expression){
				%match(Expression arg) {
					Negation(arg) -> {
						visit(`arg);
					}

					And(arg1,arg2) -> {
						visit(`arg1);
						visit(`arg2);
					}
					Or(arg1,arg2) -> {
						visit(`arg1);
						visit(`arg2);
					}
					EqualTerm(kid1,kid2) -> {
						visit(`kid1);
						visit(`kid2);
					}
				}
			} 
			return `Identity().visit(arg);
		}


	}
	// Pr�dicat Free(v:Variable) qui teste si une variable est lib�r� au noeud racine d'un cfg
/*
	%strategy Free(v:Variable) extends `Fail() {
		visit ControlFlowGraph {
			cfg -> {
				Node node = `cfg.getRoot().getNode();
				%match(Node node) {
					free(var) -> {
						if(`var.equals(v)) {
							return `cfg;
						}
					}	
				}
			}
		}

	}
*/
	class Free extends languageBasicStrategy {

		Variable v;

		public Free(Variable v) {
			super(`Fail());
			this.v=v;
		}

		public Visitable visit(Visitable arg) throws VisitFailure {
			if(arg instanceof ControlFlowGraph){
				visit(((ControlFlowGraph)arg).getRoot().getNode());
			} 
			if(arg instanceof Node){
				%match(Node arg) {
					free(var) -> {if (`var.equals(v))return `Identity().visit(arg);}	
				}
			}
			return `Fail().visit(arg);
		}
	}


	//Construction du CFG � partir de l'AST

	class Cfg extends languageBasicStrategy{
		public Cfg() {
			super(`Fail());
		}

		public  Visitable visit(Visitable arg) throws VisitFailure { 
			%match(Instruction arg) {
				If(cond,succesInst,failureInst) -> { 
					ControlFlowGraph suc = (ControlFlowGraph)(visit(`succesInst));
					ControlFlowGraph fail= (ControlFlowGraph)(visit(`failureInst));
					NodeEmilie end = new NodeEmilie(`endIf());
					NodeEmilie beginIfNode = new NodeEmilie(`beginIf(cond));
					ControlFlowGraph succesGraph  = `conc(suc,list(graph(end)));
					ControlFlowGraph failureGraph = `conc(fail,list(graph(end)));
					return `conc(graph(beginIfNode),list(succesGraph,failureGraph));} 


					WhileDo(cond,doInst) -> {
						ControlFlowGraph instrGraph = (ControlFlowGraph)(visit(`doInst));
						NodeEmilie beginWhileNode = new NodeEmilie(`beginWhile(cond));
						NodeEmilie endWhileNode = new NodeEmilie(`endWhile());
						NodeEmilie failWhileNode = new NodeEmilie(`failWhile());
						ControlFlowGraph cfg = `conc(graph(beginWhileNode),list(conc(instrGraph,list(conc(graph(endWhileNode),list()))),graph(failWhileNode)));
						cfg.addEdge(new DirectedEdge(endWhileNode,beginWhileNode));
						return cfg;}

						Let(var,term,instr) -> {
							ControlFlowGraph instrGraph = (ControlFlowGraph)(visit(`instr));
							NodeEmilie n1 = new NodeEmilie(`affect(var,term));
							NodeEmilie n2 = new NodeEmilie(`free(var));
							return `conc(graph(n1),list(conc(instrGraph,list(graph(n2)))));}

							LetRef(var,term,instr) -> {
								ControlFlowGraph instrGraph = (ControlFlowGraph)(visit(`instr));
								NodeEmilie n = new NodeEmilie(`affect(var,term));
								return `conc(graph(n),list(instrGraph));}

								LetAssign(var,term,instr) -> {	
									ControlFlowGraph instrGraph = (ControlFlowGraph)(visit(`instr));
									NodeEmilie n = new NodeEmilie(`affect(var,term));
									return `conc(graph(n),list(instrGraph));}

									Nop() -> {NodeEmilie n = new NodeEmilie(`Nil()); return `graph(n);}

			}

			%match(InstructionList arg){
				concInstruction(instr,tail*) -> {
					ControlFlowGraph instrGraph = (ControlFlowGraph)(visit(`instr));
					ControlFlowGraph tailGraph = (ControlFlowGraph)(visit(`tail));
					return `conc(instrGraph,list(tailGraph));}

					concInstruction() -> {NodeEmilie n = new NodeEmilie(`Nil()); return `graph(n);}
			}

			return `Fail().visit(arg);
		}

	}


}
