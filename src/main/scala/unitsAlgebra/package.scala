/**
  * Created by lucatosatto on 3/6/17.
  */
package object unitsAlgebra
  extends UnitAlgebraImplementations
  with UnitsOps {


  implicit def multiplicationIsCommutative[U, V, W](implicit ev: UnitMultiplyAxiom[U, V, W]) = new UnitMultiply[V, U, W] {}
  implicit def inverseIsCommutative[U, V](implicit ev: U InverseUnitAxiom V) = new InverseUnit[V, U] {}

  //Syntactic sugar to define unit multiplication

  class MultiplyBy[L, R] {
      def yields[O]: UnitMultiplyAxiom[L, R, O] = new UnitMultiplyAxiom[L, R, O] {}
      type yields[O] = UnitMultiplyAxiom[L, R, O]
  }

  class DivideBy[L, R] {
      def yields[O]: UnitMultiplyAxiom[O, R, L] = new UnitMultiplyAxiom[O, R, L] {}
      type yields[O] = UnitMultiplyAxiom[O, R, L]
  }

  class IsInverseOf[L, R] extends InverseUnitAxiom[L, R]

}
