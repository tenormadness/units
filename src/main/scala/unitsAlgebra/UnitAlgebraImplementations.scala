package unitsAlgebra

import spire.algebra._
import unitWrapper.UnitContainer._

import scala.language.implicitConversions

/**
  * Created by luca on 1/16/17.
  */
trait UnitAlgebraImplementations extends  LowPriority1 {

  implicit def unitsVector[U, T](implicit ev: VectorSpace[T, Double]): VectorSpace[T @@ U, Double] = new VectorSpace[T @@ U, Double] {

    override implicit def scalar: Field[Double] = implicitly

    override def timesl(r: Double, v: @@[T, U]): @@[T, U] = ev.timesl(r, v.value).attachUnit[U]

    override def negate(x: @@[T, U]): @@[T, U] = {
      ev.negate(x.value).attachUnit[U]
    }

    override def zero: @@[T, U] = ev.zero.attachUnit[U]

    override def plus(x: @@[T, U], y: @@[T, U]): @@[T, U] = ev.plus(x.value, y.value).attachUnit[U]

  }

}

trait LowPriority1 extends LowPriority2 {

  implicit def unitsSummable[U, T](implicit ev: Group[T]): Group[T @@ U] = new Group[T @@ U] {

    override def empty: @@[T, U] = ev.empty.attachUnit[U]

    override def combine(x: @@[T, U], y: @@[T, U]): @@[T, U] = ev.combine(x.value, y.value).attachUnit[U]

    override def inverse(l: @@[T, U]): @@[T, U] = ev.inverse(l.value).attachUnit[U]
  }
}

trait LowPriority2 {

  implicit def unitsMonoid[U, T](implicit ev: Monoid[T]): Monoid[T @@ U] = new Monoid[T @@ U] {
    override def empty: @@[T, U] = ev.empty.attachUnit[U]

    override def combine(l: @@[T, U], r: @@[T, U]): T @@ U = ev.combine(l.value, r.value).attachUnit[U]
  }
}
