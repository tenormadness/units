package unitsAlgebra

import categories.{Monoid, Summable, VectorSpace}
import main.scala.unitWrapper.UnitContainer._

import scala.language.implicitConversions

/**
  * Created by luca on 1/16/17.
  */
object UnitAlgebraImplementations extends  LowPriority1 {

  implicit def unitsVector[U, T](implicit ev: VectorSpace[T]): VectorSpace[T @@ U] = new VectorSpace[T @@ U] {
    override def zero: @@[T, U] = ev.zero.attachUnit[U]

    override def append(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.append(l.value, r.value).attachUnit[U]

    override def minus(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.minus(l.value, r.value).attachUnit[U]

    override def unaryMinus(l: @@[T, U]): @@[T, U] = ev.unaryMinus(l.value).attachUnit[U]

    override def mul(l: @@[T, U], r: Double): @@[T, U] = ev.mul(l.value, r).attachUnit[U]

    override def div(l: @@[T, U], r: Double): @@[T, U] = ev.div(l.value, r).attachUnit[U]
  }

}

trait LowPriority1 extends LowPriority2 {

  implicit def unitsSummable[U, T](implicit ev: Summable[T]): Summable[T @@ U] = new Summable[T @@ U] {
    override def zero: @@[T, U] = ev.zero.attachUnit[U]

    override def append(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.append(l.value, r.value).attachUnit[U]

    override def minus(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.minus(l.value, r.value).attachUnit[U]

    override def unaryMinus(l: @@[T, U]): @@[T, U] = ev.unaryMinus(l.value).attachUnit[U]
  }
}
//  implicit def unitsRing[U, T](implicit ev: Ring[T]): Ring[T @@ U] = new Ring[T @@ U] {
//    override def zero: @@[T, U] = ev.zero.attachUnit[U]
//
//    override def append(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.append(l.value, r.value).attachUnit[U]
//
//    override def mul(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.mul(l.value, r.value).attachUnit[U]
//
//    override def div(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.div(l.value, r.value).attachUnit[U]
//  }
trait LowPriority2 {

  implicit def unitsMonoid[U, T](implicit ev: Monoid[T]): Monoid[T @@ U] = new Monoid[T @@ U] {
    override def zero: @@[T, U] = ev.zero.attachUnit[U]

    override def append(l: @@[T, U], r: @@[T, U]): T @@ U = ev.append(l.value, r.value).attachUnit[U]
  }
}
