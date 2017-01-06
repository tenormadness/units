package main.scala.categories

/**
  * Created by luca on 12/8/16.
  */
trait Operations {

  implicit def zero[T](implicit monoid: Monoid[T]): T = monoid.zero

  implicit class MonoidOps[T](l: T)(implicit monoid: Monoid[T]) {
    def |+|(r: T): T = monoid.append(l,r)
  }

  implicit class SumOps[T](l: T)(implicit summable: Summable[T]) {
    def +(r: T): T = summable.append(l,r)
    def -(r: T): T = summable.minus(l,r)
    def unary_- : T = summable.unaryMinus(l)
  }

  implicit class VecorOps[T](l: T)(implicit vec: Vector[T]) {
    def *(r: Double): T = vec.mul(l, r)
    def /(r: Double): T = vec.div(l, r)
  }

  implicit class RingOps[T](l: T)(implicit ring: Ring[T]) {
    def *(r: T): T = ring.mul(l, r)
    def /(r: T): T = ring.div(l, r)
  }

  implicit class VectorPremul(l: Double) {
    def *[T](r: T)(implicit ev: Vector[T]): T = ev.mul(r, l)
  }
}


