package unitsAlgebra

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Could not prove that ${Left} times ${Right} yields ${Result}")
trait UnitMultiply[Left, Right, Result]

// An axiomatic rule (decided by the user)
trait UnitMultiplyAxiom[Left, Right, Result] extends UnitMultiply[Left, Right, Result]
// An axiom that states a unit can be squared e.g. m * m = m^2
trait SquareUnitRule[T, TSquared] extends UnitMultiply[T, T, TSquared]

// Multiplication rule inferred using commutative multiplication
trait UnitMultiplyCommutative[Left, Right, Result] extends UnitMultiply[Left, Right, Result]

