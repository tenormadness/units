package main.scala.unitsAlgebra

import main.scala.categories.{Operations, Ring, Summable, Vector}
import main.scala.unitWrapper.UnitContainer._


object UnitOperationSyntax extends VectorOps {

  implicit class UnitSumOps[T, U](l: T @@ U)(implicit ev: Summable[T]) {

    def +(r: T @@ U): T @@ U = ev.plus(l.value, r.value).attachUnit[U]

    def -(r: T @@ U): T @@ U = ev.minus(l.value, r.value).attachUnit[U]

  }

  implicit class UnitMulOps[T, U](l: T @@ U)(implicit ev: Ring[T]) {

    def *[V, W](r: T @@ V)(implicit mul: UnitMultiply[U, V, W]): T @@ W = new @@[T, W](ev.mul(l.value, r.value))

  }

  implicit class RingOps[T, U](l: T @@ U)(implicit ev: Ring[T]) {

    def *[V, W](r: T @@ V)(implicit mul: UnitMultiply[U, V, W]): T @@ W = {
      ev.mul(l.value, r.value).attachUnit[W]
    }

    def /[V, W](r: T @@ V)(implicit div: UnitDivide[U, V, W]): T @@ W = {
      ev.div(l.value, r.value).attachUnit[W]
    }

  }

}

trait VectorOps extends Operations {
  implicit class UnitVectorOps[T, U](l: T @@ U)(implicit ev: Vector[T]) {

    def *(r: Double): T @@ U = ev.mul(l.value, r).attachUnit[U]
    def *[V, W](r: Double @@ V)(implicit mul: UnitMultiply[U, V, W]): T @@ W = {
      ev.mul(l.value, r.value).attachUnit[W]
    }

    def /(r: Double): T @@ U = ev.div(l.value, r).attachUnit[U]
    def /[V, W](r: Double @@ V)(implicit uDiv: UnitDivide[U, V, W]): T @@ W = {
      ev.div(l.value, r.value).attachUnit[W]
    }

  }

  implicit class VectorPremulDouble(l: Double) {
    def *[T,U](r: T @@ U)(implicit vec: Vector[T]): T @@ U = vec.mul(r.value, l).attachUnit[U]
  }

  implicit class VectorPremulUnit[U](l: Double @@ U) {
    def *[T,V,W](r: T @@ V)(implicit vec: Vector[T], mulRule: UnitMultiply[U, V, W]): T @@ W = {
      vec.mul(r.value, l.value).attachUnit[W]
    }
  }

}
