package categories

import spire.algebra._

/**
  * Created by lucatosatto on 2/21/17.
  */
object SimpleAlgebraOps {

  def zero[T](implicit monoid: Monoid[T]): T = monoid.empty

  implicit class SumOps[T](l: T)(implicit summable: Group[T]) {
    def +(r: T): T = summable.combine(l,r)
    def -(r: T): T = summable.remove(l,r)
    def unary_- : T = summable.inverse(l)
  }

}
