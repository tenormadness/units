/**
  * Created by lucatosatto on 3/6/17.
  */
package object unitsAlgebra
  extends UnitAlgebraImplementations
    with UnitsOps {

  implicit def multiplicationIsCommutative[U, V, W](implicit ev: UnitMultiplyAxiom[U, V, W]) = new UnitMultiply[V, U, W] {}

}
