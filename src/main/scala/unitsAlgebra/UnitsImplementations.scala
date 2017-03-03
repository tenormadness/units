package unitsAlgebra

import unitWrapper.UnitContainer.@@
import main.scala.units.UnitsDefinitions._
import scala.language.implicitConversions

object UnitsImplementations {

  implicit object MetersPerSecond extends UnitMultiplyAxiom[MeterPerSecond, Second, Meter]  // (m/s) * s = m

  implicit def multiplicationIsCommutative[U, V, W](implicit ev: UnitMultiplyAxiom[U, V, W]) = new UnitMultiply[V, U, W] {}
}

