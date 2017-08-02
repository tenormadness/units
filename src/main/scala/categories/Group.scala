package categories


trait Summable[T] extends  Monoid[T] {

  def minus(l:T, r:T): T
  def plus(l:T, r:T): T = append(l, r)
  def unaryMinus(l: T): T

}
