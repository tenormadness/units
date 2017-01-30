package main.scala.unitsAlgebra

import main.scala.categories.{Operations, Ring, Summable, Vector}
import main.scala.unitWrapper.UnitContainer._


trait UnitOperationSyntax /*extends VectorOps*/ {

//  implicit class SumOps[T, U](l: T)(implicit ev: Summable[T]) {
//
//    def +(r: T): T = ev.plus(l, r)
//
//    def -(r: T): T = ev.minus(l, r)
//
//  }

//  implicit class UnitMulOps[T, U](l: T @@ U)(implicit ev: Ring[T]) {
//
//    def *[V, W](r: T @@ V)(implicit mul: UnitMultiply[U, V, W]): T @@ W = new @@[T, W](ev.mul(l.value, r.value))
//
//  }

  implicit class RingUnitOps[T, U](l: T @@ U)(implicit ev: Ring[T]) {

    def *[V, W](r: T @@ V)(implicit mul: UnitMultiply[U, V, W]): T @@ W = {
      ev.mul(l.value, r.value).attachUnit[W]
    }

    //self divide
    def /(r: T @@ U): T = {
      ev.div(l.value, r.value)
    }


    def /[V, W](r: T @@ V)(implicit div: UnitDivide[U, V, W]): T @@ W = {
      ev.div(l.value, r.value).attachUnit[W]
    }

  }

}

//trait VectorOps extends Operations {
//  implicit class UnitVectorOps[T, U](l: T @@ U)(implicit ev: Vector[T]) {
//
//    def *(r: Double): T @@ U = ev.mul(l.value, r).attachUnit[U]
//    def *[V, W](r: Double @@ V)(implicit mul: UnitMultiply[U, V, W]): T @@ W = {
//      ev.mul(l.value, r.value).attachUnit[W]
//    }
//
//    def /(r: Double): T @@ U = ev.div(l.value, r).attachUnit[U]
//    def /[V, W](r: Double @@ V)(implicit uDiv: UnitDivide[U, V, W]): T @@ W = {
//      ev.div(l.value, r.value).attachUnit[W]
//    }
//
//  }

//  implicit class VectorPremulDouble(l: Double) {
//    def *[T,U](r: T @@ U)(implicit vec: Vector[T]): T @@ U = vec.mul(r.value, l).attachUnit[U]
//  }
//
//  implicit class VectorPremulUnit[U](l: Double @@ U) {
//    def *[T,V,W](r: T @@ V)(implicit vec: Vector[T], mulRule: UnitMultiply[U, V, W]): T @@ W = {
//      vec.mul(r.value, l.value).attachUnit[W]
//    }
//  }

//}
