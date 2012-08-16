package enumerator;

import java.math.BigInteger;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.ONE;
import fj.F;
import fj.F2;
import fj.Function;
import fj.P1;
import fj.P2;
import fj.function.BigIntegers;
import fj.function.Integers;

public class Enumeration<A> {
	public LazyList<Finite<A>> cacheParts;
	public P1<LazyList<Finite<A>>> p1;

	private Enumeration(LazyList<Finite<A>> p) {
		this.cacheParts = p;
	}

	public Enumeration(P1<LazyList<Finite<A>>> p1) {
		this.p1 = p1;
	}
	
	public LazyList<Finite<A>> parts() {
		if(cacheParts==null) {
			cacheParts = p1._1();
		}
		return cacheParts; 
	}
	
	public static <A> Enumeration<A> empty() {
		return new Enumeration<A>(LazyList.<Finite<A>>nil());
	}

	public static <A> Enumeration<A> singleton(A x) {
		return new Enumeration<A>(LazyList.singleton(Finite.singleton(x)));
	}
	
	private static <A> A index(LazyList<Finite<A>> ps, BigInteger i) {
		while (!ps.isEmpty()) {
			Finite<A> head = ps.head();
			BigInteger card = head.getCard();
			if (i.compareTo(card)<0) {
				return head.get(i);
			} else {
				ps = ps.tail();
				i = i.subtract(card);
			}
		}
		throw new RuntimeException("index " + i + " out of range");
/*
		if (ps.isEmpty()) {
			throw new RuntimeException("index " + i + " out of range");
		}
		
		Finite<A> head = ps.head();
		BigInteger card = head.getCard();
		if (i.compareTo(card)<0) {
			return head.get(i);
		}
		return index(ps.tail(), i.subtract(card));
		*/
	}

	public A get(BigInteger i) {
		return index(parts(), i);
	}

	public Enumeration<A> plus(final Enumeration<A> other) {
		return new Enumeration<A>(
				new P1<LazyList<Finite<A>>>() {
					public LazyList<Finite<A>> _1() { 
						return zipPlus(Enumeration.this.parts(),other.parts()); }
				});
	}
	
	private static <A> LazyList<Finite<A>> zipPlus(final LazyList<Finite<A>> xs, final LazyList<Finite<A>> ys) {
		if(xs.isEmpty() || ys.isEmpty()) {
			return xs.append(ys);
		}
		return LazyList.fromPair(new P2<Finite<A>,LazyList<Finite<A>>>() {
			public Finite<A> _1() { return xs.head().plus(ys.head()); }
			public LazyList<Finite<A>> _2() { return zipPlus(xs.tail(),ys.tail()); }
		});
	}
	
	
	public <B> Enumeration<B> map(final F<A,B> f) {
		final F<Finite<A>,Finite<B>> g = new F<Finite<A>,Finite<B>>() {
			public Finite<B> f(Finite<A> x) {
				return x.map(f);
			}
		};
		//return new Enumeration<B>(parts.map(g));
		return new Enumeration<B>(
				new P1<LazyList<Finite<B>>>() {
					public LazyList<Finite<B>> _1() { 
						return Enumeration.this.parts().map(g); }
				});
	}
	
	public Enumeration<A> pay() {
		return new Enumeration<A>(
				new P1<LazyList<Finite<A>>>() {
					public LazyList<Finite<A>> _1() { 
						return LazyList.<Finite<A>>fromPair(
								new P2<Finite<A>,LazyList<Finite<A>>>() {
									public Finite<A> _1() { return Finite.<A>empty(); }
									public LazyList<Finite<A>> _2() { return Enumeration.this.parts(); }
								});
					}
				}
				);
	}
	
	public <B> Enumeration<P2<A,B>> times(final Enumeration<B> other) {
		//return new Enumeration<P2<A,B>>(prod(this.parts, other.parts.reversals()));
		return new Enumeration<P2<A,B>>(
				new P1<LazyList<Finite<P2<A,B>>>>() {
					public LazyList<Finite<P2<A,B>>> _1() {
						return prod(Enumeration.this.parts(), other.parts().reversals()); }
					});
	}

	 private static <A,B> LazyList<Finite<P2<A,B>>> prod(LazyList<Finite<A>> xs, final LazyList<LazyList<Finite<B>>> ys) {
		 if(xs.isEmpty() || ys.isEmpty()) {
			 return LazyList.nil();
		 }
		 return goY(xs,ys);
	 }
	 
	 private static <A,B> LazyList<Finite<P2<A,B>>> goY(final LazyList<Finite<A>> xs, final LazyList<LazyList<Finite<B>>> rys) {
		 return LazyList.fromPair(new P2<Finite<P2<A,B>>, LazyList<Finite<P2<A,B>>>>() {
			 public Finite<P2<A,B>> _1() { return conv(xs,rys.head()); }
			 public LazyList<Finite<P2<A,B>>> _2() { return (rys.tail().isEmpty())?goX(xs,rys.head()):goY(xs, rys.tail()); }
		 });
	 }
	
	private static <A,B> LazyList<Finite<P2<A,B>>> goX(final LazyList<Finite<A>> xs, final LazyList<Finite<B>> ry) {
		F<LazyList<Finite<A>>, Finite<P2<A,B>>> fs = new F<LazyList<Finite<A>>, Finite<P2<A,B>>>() {
			public Finite<P2<A,B>> f(LazyList<Finite<A>> x) {
				return conv(x,ry);
			} 
		};
		return xs.tail().tails().map(fs);
	}
	
	private static <A,B> Finite<P2<A,B>> conv(final LazyList<Finite<A>> xs, final LazyList<Finite<B>> ys) {
		F<Finite<A>, BigInteger> cardA = new F<Finite<A>, BigInteger>() { public BigInteger f(Finite<A> x) { return x.getCard(); } };
		F<Finite<B>, BigInteger> cardB = new F<Finite<B>, BigInteger>() { public BigInteger f(Finite<B> x) { return x.getCard(); } };
        LazyList<BigInteger> xsCards = xs.map(cardA);
		LazyList<BigInteger> ysCards = ys.map(cardB);
		//F<BigInteger, F<BigInteger, BigInteger>> multiply = Function.curry(
		//		new F2<BigInteger, BigInteger, BigInteger>() { public BigInteger f(BigInteger a, BigInteger b) { return a.multiply(b); } });
		//F<BigInteger, F<BigInteger, BigInteger>> add = Function.curry(
		//		new F2<BigInteger, BigInteger, BigInteger>() { public BigInteger f(BigInteger a, BigInteger b) { return a.add(b); } });

		LazyList<BigInteger> cardsProducts = xsCards.zipWith(ysCards,BigIntegers.multiply);
		BigInteger newCard = cardsProducts.foldLeft(ZERO,BigIntegers.add);
		F<BigInteger, P2<A,B>> newIndexer = new F<BigInteger,P2<A,B>>() { 
			public P2<A,B> f(BigInteger i) {
				Finite<P2<A,B>> unionOfProducts = xs.zipWith(ys, Finite.<A,B>functorTimes())
				.foldLeft(Finite.<P2<A,B>>empty(), Finite.<P2<A,B>>functorPlus()); 
				return unionOfProducts.get(i);
			} 
		};

		return new Finite<P2<A,B>>(newCard,newIndexer);
	}

	public static <A,B> Enumeration<B> apply(final Enumeration<F<A,B>> subject, final Enumeration<A> other) {
		F<P2<F<A,B>,A>,B> pair = new F<P2<F<A,B>,A>,B>() {
			public B f(P2<F<A,B>,A> p) {
				return p._1().f(p._2());
			}
		};
		return subject.times(other).map(pair);
	}
	
	public static <E> Enumeration<E> fix(final F<Enumeration<E>,Enumeration<E>> f) {
		Enumeration<E> e = new Enumeration<E>((LazyList<Finite<E>>)null);
		final Enumeration<E> res = f.f(e);
		e.p1 = new P1<LazyList<Finite<E>>>() {
			public LazyList<Finite<E>> _1() { return res.cacheParts; }
		};
		//e.cacheParts = res.cacheParts;

		return res;
	}
	
}
