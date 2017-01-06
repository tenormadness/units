package main.scala.unitsAlgebra

trait UnitMultiply[Left, Right, Result]
trait UnitMultiplyAxiom[Left, Right, Result] extends UnitMultiply[Left, Right, Result]
trait UnitMultiplyCommutative[Left, Right, Result] extends UnitMultiply[Left, Right, Result]

trait UnitDivide[Left, Right, Result]
