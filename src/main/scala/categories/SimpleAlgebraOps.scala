package categories

import algebra.ring.AdditiveCommutativeGroup
import spire.algebra._

/**
  * Created by lucatosatto on 2/21/17.
  */
object SimpleAlgebraOps {

  def zero[T](implicit monoid: AdditiveCommutativeGroup[T]): T = monoid.zero

  implicit class SumOps[T](l: T)(implicit summable: AdditiveCommutativeGroup[T]) {
    def +(r: T): T = summable.plus(l,r)
    def -(r: T): T = summable.minus(l,r)
    def unary_- : T = summable.negate(l)
  }

}
