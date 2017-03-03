package unitsAlgebra

import categories._
import unitWrapper._
import unitWrapper.UnitContainer._

/**
  * Created by lucatosatto on 2/21/17.
  */
object UnitsOps extends LowPriorityOps {

//  implicit class UnitsVecOps[T, U](l: T @@ U)(implicit vec: VectorSpace[T @@ U]) {
//
//    def *[V, W](r: Double @@ V)(implicit unitMul: UnitMultiply[U, V, W]): T @@ W = vec.mul(l, r.value).value.attachUnit[W]
//
//    // "repetita iuvant" this is already included in the AlgebraOps but it it necessary to repeat it here to avoid collision
//    def *(r: Double): T @@ U = vec.mul(l, r)
//
//  }


  implicit class GenericUnitOps[T, U](l: T @@ U) {

    def /[R](r: R)(implicit ev: UnitDivision[T @@ U, R]): ev.Result = ev.div(l, r)
    def *[R](r: R)(implicit ev: UnitMultiplication[T @@ U, R]): ev.Result = ev.mul(l, r)

  }
}

trait LowPriorityOps {

//  implicit class RingMul[T, U](l: T @@ U)(implicit ring: Ring[T]) {
//
//    def *[V, W](r: T @@ V)(implicit unitMul: UnitMultiply[U, V, W]): T @@ W = ring.mul(l.value, r.value).attachUnit[W]
//  }

  trait OverloadHack
  implicit object OverloadHackImplementation


  //implicit class SelfDivision[T, U](l: T @@ U)(implicit o: OverloadHack) {
  //  def /[V](r: T @@ U)(implicit ev: Ring[T]): T = ev.div(l.value, r.value)
  //}

//  implicit class VectorPremulUnitLowPriority[U](l: Double @@ U)/*(implicit o: OverloadHack)*/ {
//
//    def *[T, V, W](r: T @@ V)(implicit vec: VectorSpace[T], unitMul: UnitMultiply[U, V, W]): T @@ W = vec.mul(r.value, l.value).attachUnit[W]
//  }

}
