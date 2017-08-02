package categories

import spire.algebra._

/**
  * Created by lucatosatto on 2/21/17.
  */
object AlgebraOps {

  def zero[T](implicit monoid: Monoid[T]): T = monoid.id

//  implicit class MonoidOps[T](l: T)(implicit monoid: Monoid[T]) {
//    def |+|(r: T): T = monoid.op(l,r)
//  }

  implicit class SumOps[T](l: T)(implicit summable: Group[T]) {
    def +(r: T): T = summable.op(l,r)
    def -(r: T): T = summable.opInverse(l,r)
    def unary_- : T = summable.inverse(l)
  }

  implicit class VecorOps[T](l: T)(implicit vec: VectorSpace[T]) {
    def *(r: Double): T = vec.mul(l, r)
    def /(r: Double): T = vec.div(l, r)
  }

  implicit class RingOps[T](l: T)(implicit field: Field[T]) {
    def *(r: T): T = field.times(l, r)
    def /(r: T): T = field.div(l, r)
  }

  implicit class VectorPremul(l: Double) {
    def *[T](r: T)(implicit ev: VectorSpace[T]): T = ev.mul(r, l)
  }

}
