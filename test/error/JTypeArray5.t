%typearray type1 {
    implement { ArrayList }
    get_fun_sym(t)   { ((t instanceof ArrayList)?factory.makeAFun("conc", 1, false):null) }
    cmp_fun_sym(t1,t2) { t1 == t2 }
    get_element(l,n) { ((ArrayList)l).get(n) }
    get_size(l)      { ((ArrayList)l).size() }
  }
