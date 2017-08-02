package categories

import spire.algebra._

/**
  * Created by lucatosatto on 2/21/17.
  */
object AlgebraOps {

  def zero[T](implicit monoid: Monoid[T]): T = monoid.empty

  implicit class SumOps[T](l: T)(implicit summable: Group[T]) {
    def +(r: T): T = summable.combine(l,r)
    def -(r: T): T = summable.remove(l,r)
    def unary_- : T = summable.inverse(l)
  }

  implicit class VecorOps[T](l: T)(implicit vec: VectorSpace[T, Double]) {
    def *(r: Double): T = vec.timesr(l, r)
    def /(r: Double): T = vec.divr(l, r)
  }

  implicit class RingOps[T](l: T)(implicit field: Field[T]) {
    def *(r: T): T = field.times(l, r)
    def /(r: T): T = field.div(l, r)
  }

  implicit class VectorPremul(l: Double) {
    def *[T](r: T)(implicit ev: VectorSpace[T, Double]): T = ev.timesl(l, r)
  }

}
