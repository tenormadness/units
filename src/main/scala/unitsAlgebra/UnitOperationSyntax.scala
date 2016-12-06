package unitsAlgebra

import typeTag.TypeTag._
import unitsAlgebra.MultiplicativeOperations.{CanDivide, CanMultiply}
import unitsAlgebra.SummableOperations._

/**
 * Created by lucatosatto on 8/25/16.
 */
object UnitOperationSyntax {

  implicit class SumOps[T, U](l: T @@ U)(implicit ev: CanSum[T]) {

    def +[V, W](r: T @@ V)(implicit canSum: UnitSummer[U, V, W]): T @@ W = ev.plus(l, r)
    //    def +(r: T @@ U)(implicit canSum: UnitMultiply[U, U, U]): T @@ U = ev.plus(l, r)

  }

  implicit class MulOps[T, U](l: T @@ U)(implicit ev: CanMultiply[T]) {

    def *[V, W](r: T @@ V)(implicit canSum: UnitMultiply[U, V, W]): T @@ W = ev.times(l, r)

  }

  implicit class DivOps[T, U](l: T @@ U)(implicit ev: CanDivide[T]) {

    def /[V, W](r: T @@ V)(implicit canSum: UnitDivide[U, V, W]): T @@ W = ev.divide(l, r)

  }

}
