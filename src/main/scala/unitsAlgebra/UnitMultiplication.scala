package unitsAlgebra

import categories.VectorSpace
import unitWrapper.UnitContainer._
import unitsAlgebra.UnitMultiplication.UnitMultiplicationAux
import spire.algebra._
import scala.annotation.implicitNotFound

/**
  * Typeclass that proves that two types L and R (which are eventually bound to units) can be multiplied
  *
  */
@implicitNotFound(msg = "Could not find a way to multiply ${L} times ${R}. Result type is unknown")
trait UnitMultiplication[L, R] {

  type Result

  def mul(l: L, r: R): Result

}

object UnitMultiplication extends LowPriorityMulRules {

  type UnitMultiplicationAux[L, R, Out] = UnitMultiplication[L, R] { type Result = Out }

  implicit def unitVectorMultiply[T, U, UU, UR](implicit vector: VectorSpace[T], unitMultiply: UnitMultiply[U, UU, UR]): UnitMultiplicationAux[T @@ U, Double @@ UU, T @@ UR] = {
    new UnitMultiplication[T @@ U, Double @@ UU] {

      override type Result = T @@ UR

      override def mul(l: T @@ U, r: Double @@ UU): Result = vector.mul(l.value, r.value).attachUnit[UR]
    }
  }

  implicit def doubleVectorMultiply[T, U](implicit vector: VectorSpace[T]): UnitMultiplicationAux[T @@ U, Double, T @@ U] = {
    new UnitMultiplication[T @@ U, Double] {

      override type Result = T @@ U

      override def mul(l: T @@ U, r: Double): Result = vector.mul(l.value, r).attachUnit[U]
    }
  }

  implicit def doubleVectorPreMultiply[T, U](implicit vector: VectorSpace[T]): UnitMultiplicationAux[Double, T @@ U, T @@ U] = {
    new UnitMultiplication[Double, T @@ U] {

      override type Result = T @@ U

      override def mul(l: Double, r: T @@ U): Result = vector.mul(r.value, l).attachUnit[U]
    }
  }
}

trait LowPriorityMulRules extends LowestLevelPremultiply {

  implicit def unitRingMultiply[T, U, UU, UR](implicit ring: Field[T], unitMultiply: UnitMultiply[U, UU, UR]): UnitMultiplicationAux[T @@ U, T @@ UU, T @@ UR] = {
    new UnitMultiplication[T @@ U, T @@ UU] {

      override type Result = T @@ UR

      override def mul(l: T @@ U, r: T @@ UU): Result = ring.times(l.value, r.value).attachUnit[UR]
    }
  }

}

trait LowestLevelPremultiply {

  implicit def unitVectorPreMultiply[T, U, UU, UR](implicit vector: VectorSpace[T], unitMultiply: UnitMultiply[U, UU, UR]): UnitMultiplicationAux[Double @@ UU, T @@ U, T @@ UR] = {
    new UnitMultiplication[Double @@ UU, T @@ U] {

      override type Result = T @@ UR

      override def mul(l: Double @@ UU, r: T @@ U): Result = vector.mul(r.value, l.value).attachUnit[UR]
    }
  }
}
