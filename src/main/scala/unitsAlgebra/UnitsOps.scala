package unitsAlgebra

import categories._
import unitWrapper._
import unitWrapper.UnitContainer._

/**
  * Created by lucatosatto on 2/21/17.
  */
object UnitsOps {

  implicit class GenericUnitOps[T, U](l: T @@ U) {

    def /[R](r: R)(implicit ev: UnitDivision[T @@ U, R]): ev.Result = ev.div(l, r)
    def *[R](r: R)(implicit ev: UnitMultiplication[T @@ U, R]): ev.Result = ev.mul(l, r)

  }
}
