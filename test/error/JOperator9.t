%op type1 zero(type:type1, type1) {
  fsym { fzero }
  make(t1,t2) { factory.makeAppl(fzero) }
  is_fsym(t) { ((((ATermAppl)t).getAFun()) == fzero)  }
  get_slot(type,t) { t.getType() }
  get_slot(type1,t) { t.getType() }
}

%typeterm type1 {
  implement { String }
  get_fun_sym(t) { t }
  cmp_fun_sym(s1,s2) { s1.equals(s2) }
  get_subterm(t,n) { null }
  equals(t1,t2) { t1.equals(t2) }
}
