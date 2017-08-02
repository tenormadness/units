package unitsAlgebra

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Could not prove that ${Left} times ${Right} yields ${Result}")
trait UnitMultiply[Left, Right, Result]

@implicitNotFound(msg = "Could not prove that ${U1} is the inverse of ${U2}")
trait InverseUnit[U1, U2]

// An axiomatic rule (decided by the user)
trait UnitMultiplyAxiom[Left, Right, Result] extends UnitMultiply[Left, Right, Result]
trait InverseUnitAxiom[Left, Right] extends InverseUnit[Left, Right]


