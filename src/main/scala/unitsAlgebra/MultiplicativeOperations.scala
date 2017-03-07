package unitsAlgebra

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Could not prove that ${Left} times ${Right} yields ${Result}")
trait UnitMultiply[Left, Right, Result]

// An axiomatic rule (decided by the user)
trait UnitMultiplyAxiom[Left, Right, Result] extends UnitMultiply[Left, Right, Result]


