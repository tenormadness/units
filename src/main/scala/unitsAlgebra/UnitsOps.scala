package unitsAlgebra

import categories._
import spire.algebra.{Field, VectorSpace}
import unitWrapper._
import unitWrapper.UnitContainer._

/**
  * Created by lucatosatto on 2/21/17.
  */
trait UnitsOps extends LowPriorityUnitlessOps {

  implicit class GenericUnitOps[T, U](l: T @@ U) {

    def /[R](r: R)(implicit ev: UnitDivision[T @@ U, R]): ev.Result = ev.div(l, r)

    def *[R](r: R)(implicit ev: UnitMultiplication[T @@ U, R]): ev.Result = ev.mul(l, r)

  }

  implicit class RingUnitlessOps[T](l: T)(implicit field: Field[T]) {
    def *(r: T)(implicit field: Field[T]): T = field.times(l, r)
    def /(r: T)(implicit field: Field[T]): T = field.div(l, r)

    def /[U, UR](r: T @@ U)(implicit field: Field[T], ev: U InverseUnit UR): T @@ UR = field.div(l, r.value).attachUnit[UR]
  }
}

trait LowPriorityUnitlessOps {

  implicit class VectorUnitLessOps[T](l: T) {
    def *(r: Double)(implicit vec: VectorSpace[T, Double]): T = vec.timesr(l, r)
    def /(r: Double)(implicit vec: VectorSpace[T, Double]): T = vec.divr(l, r)

    def /[U, UR](r: Double @@ U)(implicit vec: VectorSpace[T, Double], ev: U InverseUnit UR): T @@ UR = vec.divr(l, r.value).attachUnit[UR]

  }

  implicit class VectorPremul(l: Double) {
    def *[T](r: T)(implicit ev: VectorSpace[T, Double]): T = ev.timesl(l, r)
  }
}
