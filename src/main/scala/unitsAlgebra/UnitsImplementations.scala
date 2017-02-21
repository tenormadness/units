package unitsAlgebra

import unitWrapper.UnitContainer.@@
import main.scala.units.UnitsDefinitions._
import scala.language.implicitConversions

object UnitsImplementations {

  implicit object MetersPerSecond extends UnitMultiplyAxiom[MeterPerSecond, Second, Meter]  // (m/s) * s = m

  //implicit def selfDivision[U]: UnitDivide[U, U, Nothing] = new UnitDivide[U, U, Nothing] {}

  //implicit def noUnitsCanStripUnits[T](in: T @@ Nothing): T = in.value

  //implicit def divisionIsMulInverse1[U, V, W](implicit ev: UnitMultiplyAxiom[U, V, W]) = new UnitDivide[W, U, V] {}

  implicit def multiplicationIsCommutative[U, V, W](implicit ev: UnitMultiplyAxiom[U, V, W]) = new UnitMultiply[V, U, W] {}
}

