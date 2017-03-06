package unitsAlgebra

import unitWrapper.UnitContainer.@@

import scala.language.implicitConversions

object UnitsImplementations {

  case class Meter()
  case class MeterSq()
  case class Second()
  case class MeterPerSecond()
  case class MeterPerSecondSq()

  implicit object MetersPerSecond extends UnitMultiplyAxiom[MeterPerSecond, Second, Meter]  // (m/s) * s = m
  implicit object MetersPerSecondSq extends UnitMultiplyAxiom[MeterPerSecondSq, Second, MeterPerSecond]
  implicit object MeterToMeterSq extends SquareUnitRule[Meter, MeterSq]

}

