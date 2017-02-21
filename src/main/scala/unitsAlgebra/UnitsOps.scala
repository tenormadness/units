package unitsAlgebra

import categories._
import unitWrapper._
import unitWrapper.UnitContainer._

/**
  * Created by lucatosatto on 2/21/17.
  */
object UnitsOps extends LowPriority {

  implicit class UnitsVecOps[T, U](l: T @@ U) {

    def *[V, W](r: Double @@ V)(implicit vec: VectorSpace[T @@ U], unitMul: UnitMultiply[U, V, W]): T @@ W = vec.mul(l, r.value).value.attachUnit[W]
    def /[V, W](r: Double @@ V)(implicit vec: VectorSpace[T @@ U], unitMul: UnitMultiply[W, V, U]): T @@ W = vec.div(l, r.value).value.attachUnit[W]

    // "repetita iuvant" this is already included in the AlgebraOps but it it necessary to repeat it here to avoid collision
    def *(r: Double)(implicit vec: VectorSpace[T @@ U]): T @@ U = vec.mul(l, r)
    def /(r: Double)(implicit vec: VectorSpace[T @@ U]): T @@ U = vec.div(l, r)

  }

}

trait LowPriority {

  implicit class UnitsRingOps[T, U](l: T @@ U) {

    def *[V, W](r: T @@ V)(implicit ev: Ring[T], unitMul: UnitMultiply[U, V, W]): T @@ W = ev.mul(l.value, r.value).attachUnit[W]

    def /[V, W](r: T @@ V)(implicit ev: Ring[T], unitMul: UnitMultiply[W, V, U]): T @@ W = ev.div(l.value, r.value).attachUnit[W]
  }

  trait OverloadHack
  implicit object OverloadHackImplementation


  implicit class VectorPremulUnitLowPriority[U](l: Double @@ U)(implicit o: OverloadHack) {

    def *[T, V, W](r: T @@ V)(implicit vec: VectorSpace[T], unitMul: UnitMultiply[U, V, W]): T @@ W = vec.mul(r.value, l.value).attachUnit[W]
  }

}
