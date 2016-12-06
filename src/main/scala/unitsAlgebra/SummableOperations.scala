package unitsAlgebra

import typeTag.TypeTag._
import units.UnitsDefinitions.ErrorUnitNotFound



object SummableOperations {

  trait CanSum[T] {

    def plus[Left, Right, Result](l: T @@ Left, r: T @@ Right)(implicit unitSummer: UnitSummer[Left, Right, Result]): T @@ Result

  }

  trait CanSubtract[T] {

    def minus[Left, Right, Result](l: T @@ Left, r: T @@ Right)(implicit unitSummer: UnitSummer[Left, Right, Result]): T @@ Result

  }

}

trait UnitSummer[Left, Right, Result]


