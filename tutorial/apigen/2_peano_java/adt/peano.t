%typeterm String{
  implement { String}
  get_fun_sym(t) {t}
  cmp_fun_sym(s1,s2) { s1.equals(s2)}
  get_subterm(t,n) {null}
  equals(t1,t2) {t1.equals(t2)}
}

%typeterm Integer{
  implement { Integer}
  get_fun_sym(t) {t}
  cmp_fun_sym(s1,s2) { s1.equals(s2)}
  get_subterm(t,n) {null}
  equals(t1,t2) {t1.equals(t2)}
}

%typeterm Double{
  implement { Double}
  get_fun_sym(t) {t}
  cmp_fun_sym(s1,s2) { s1.equals(s2)}
  get_subterm(t,n) {null}
  equals(t1,t2) {t1.equals(t2)}
}

%typeterm ATerm{
  implement { ATerm}
  get_fun_sym(t) {((t instanceof ATermAppl)?((ATermAppl)t).getAFun():null)}
  cmp_fun_sym(s1,s2) { s1==s2}
  get_subterm(t,n) {(((ATermAppl)t).getArgument(n))}
  equals(t1,t2) {t1.equals(t2)}
}

%typelist ATermList{
  implement { ATermList}
  get_fun_sym(t) {((t instanceof ATermList)?getPeanoFactory().makeAFun("conc",1,false):null)}
  cmp_fun_sym(s1,s2) { s1==s2}
  equals(t1,t2) {t1.equals(t2)}
  get_head(l) {l.getFirst()}
  get_tail(l) {l.getNext()}
  is_empty(l) {l.isEmpty()}
}

%typeterm Nat{
  implement { Nat}
  get_fun_sym(t) {null}
  cmp_fun_sym(s1,s2) { false}
  get_subterm(t,n) {null}
  equals(t1,t2) {t1.equals(t2)}
}

%op Nat zero {
  fsym {}
  is_fsym(t) { (t!= null) &&t.isZero()}
  make() { getPeanoFactory().makeNat_Zero()}
}

%op Nat suc(pred:Nat) {
  fsym {}
  is_fsym(t) { (t!= null) &&t.isSuc()}
  get_slot(pred,t) { t.getPred()}
  make(t0) { getPeanoFactory().makeNat_Suc(t0)}
}

