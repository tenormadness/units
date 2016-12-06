package unitsAlgebra

import typeTag.TypeTag._
import unitsAlgebra.MultiplicativeOperations.{CanDivide, CanMultiply}
import unitsAlgebra.SummableOperations.CanSum


object BasicOperationImplementations {

  implicit def numbersCanSum[T](implicit ev: Numeric[T]): CanSum[T] = new CanSum[T] {

    override def plus[Left, Right, Result](l: @@[T, Left], r: @@[T, Right])(implicit unitSummer: UnitSummer[Left, Right, Result]): @@[T, Result] = {

      ev.plus(l.unwrap, r.unwrap).wrap[Result]

    }
  }

  implicit def numbersCanMultiply[T](implicit ev: Numeric[T]): CanMultiply[T] = new CanMultiply[T] {
    override def times[Left, Right, Result](l: @@[T, Left], r: @@[T, Right])
                                           (implicit unitSummer: UnitMultiply[Left, Right, Result]): @@[T, Result] =
      ev.times(l.unwrap, r.unwrap).wrap[Result]
  }

  implicit def numbersCanDivide[T](implicit ev: Fractional[T]): CanDivide[T] = new CanDivide[T] {
    override def divide[Left, Right, Result](l: @@[T, Left], r: @@[T, Right])(implicit unitSummer: UnitDivide[Left, Right, Result]): @@[T, Result] =
      ev.div(l.unwrap, r.unwrap).wrap[Result]
  }




  implicit object SeqSum extends CanSum[Seq[Double]] {
    override def plus[Left, Right, Result](l: Seq[Double] @@ Left, r: @@[Seq[Double], Right])(implicit unitSummer: UnitSummer[Left, Right, Result]): @@[Seq[Double], Result] = {

      val ll = l.unwrap
      val rr = r.unwrap

      val value = (ll zip rr).map {case (x, y) => x + y}

      value.wrap[Result]

    }
  }

}
