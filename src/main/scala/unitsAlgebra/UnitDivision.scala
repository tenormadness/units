package unitsAlgebra

import unitWrapper.UnitContainer._
import unitsAlgebra.UnitDivision.UnitDivisionAux
import spire.algebra._

import scala.annotation.implicitNotFound
import scala.language.implicitConversions

/**
  * Auxiliary typeclass to divide a types (tagged with units)
  * 
  * i.e.  Result = T @ U / T @@ UU
  *
  * This is used to overcome the overload of the division operator that is inherent to natural language
  * covers both the case of a division with units and a self division to yield a "pure number"
  *
  */
@implicitNotFound(msg = "Could not find a way to divide ${L} by ${R}. Result type is unknown")
trait UnitDivision[L, R] {

  type Result
  
  def div(l: L, r: R): Result
  
}

object UnitDivision extends LowPriorityDivRules {

  type UnitDivisionAux[L, R, Out] = UnitDivision[L, R] { type Result = Out }

  implicit def unitVectorDivision[T, U, UU, UR](implicit vector: VectorSpace[T, Double], unitMultiply: UnitMultiply[UR, UU, U]): UnitDivisionAux[T @@ U, Double @@ UU, T @@ UR] = {
    new UnitDivision[T @@ U, Double @@ UU] {

      override type Result = T @@ UR

      override def div(l: T @@ U, r: Double @@ UU): Result = vector.divr(l.value, r.value).attachUnit[UR]
    }
  }

  implicit def doubleVectorDivision[T, U](implicit vector: VectorSpace[T, Double]): UnitDivisionAux[T @@ U, Double, T @@ U] = {
    new UnitDivision[T @@ U, Double] {

      override type Result = T @@ U

      override def div(l: T @@ U, r: Double): Result = vector.divr(l.value, r).attachUnit[U]
    }
  }
}

trait LowPriorityDivRules {

  implicit def selfDivision[T, U](implicit ring: Field[T]): UnitDivisionAux[T @@ U, T @@ U, T] = new UnitDivision[T @@ U, T @@ U] {

    override type Result = T

    override def div(l: T @@ U, r: T @@ U): Result = ring.div(l.value, r.value)
  }

  implicit def unitRingDivision[T, U, UU, UR](implicit ring: Field[T], unitMultiply: UnitMultiply[UR, UU, U]): UnitDivisionAux[T @@ U, T @@ UU, T @@ UR] = new UnitDivision[T @@ U, T @@ UU] {

    override type Result = T @@ UR

    override def div(l: T @@ U, r: T @@ UU): Result = ring.div(l.value, r.value).attachUnit[UR]
  }

}
