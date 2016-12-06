package unitsAlgebra

import units.UnitsDefinitions._

object ImplicitUnitResolution extends LowPrioritySummableImplicits with LowPriorityMultiplicativeImplicits {

  implicit object MetersPerSecond extends UnitMultiply[MeterPerSecond, Second, Meter]  // (m/s) * s = m

  // implementation of unit summation rules
  implicit def sameUnitCanBeSummed[U] = new UnitSummer[U, U, U] {}

  implicit def multiplicationIsCommutative[U, V, W](implicit ev: UnitMultiply[U, V, W]) = new UnitMultiply[V, U, W] {}

  implicit def divisionIsMulInverse1[U, V, W](implicit ev: UnitMultiply[U, V, W]) = new UnitDivide[W, U, V] {}
  //implicit def divisionIsMulInverse2[U, V, W](implicit ev: UnitMultiply[U, V, W]) = new UnitDivide[W, V, U] {}

}

sealed trait LowPriorityMultiplicativeImplicits {

  implicit object failedMultiplicationResolution extends UnitMultiply[Any, Any, ErrorUnitNotFound] {}
  implicit object failedDivisionResolution extends UnitDivide[Any, Any, ErrorUnitNotFound] {}

}

sealed trait LowPrioritySummableImplicits {

  final implicit def differentUnitsCannotBeSummed[U, V] = new UnitSummer[U, V, ErrorUnitNotFound] {} //perhaps add Miles Sabin's inequality trick

}
