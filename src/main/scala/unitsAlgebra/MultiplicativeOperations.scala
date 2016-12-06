package unitsAlgebra

import typeTag.TypeTag._
import units.UnitsDefinitions.ErrorUnitNotFound

/**
  * Created by lucatosatto on 8/25/16.
  */
object MultiplicativeOperations {

  trait CanMultiply[T] {

    def times[Left, Right, Result](l: T @@ Left, r: T @@ Right)(implicit unitSummer: UnitMultiply[Left, Right, Result]): T @@ Result

  }

  trait CanDivide[T] {

    def divide[Left, Right, Result](l: T @@ Left, r: T @@ Right)(implicit unitSummer: UnitDivide[Left, Right, Result]): T @@ Result

  }

}

trait UnitMultiply[Left, Right, Result]
trait UnitDivide[Left, Right, Result]
