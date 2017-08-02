package unitsAlgebra

import spire.algebra._
import categories.{VectorSpace}
import unitWrapper.UnitContainer._

import scala.language.implicitConversions

/**
  * Created by luca on 1/16/17.
  */
trait UnitAlgebraImplementations extends  LowPriority1 {

  implicit def unitsVector[U, T](implicit ev: VectorSpace[T]): VectorSpace[T @@ U] = new VectorSpace[T @@ U] {
    override def id: @@[T, U] = ev.id.attachUnit[U]

    override def op(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.op(l.value, r.value).attachUnit[U]

    override def opInverse(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.opInverse(l.value, r.value).attachUnit[U]

    override def inverse(l: @@[T, U]): @@[T, U] = ev.inverse(l.value).attachUnit[U]

    override def mul(l: @@[T, U], r: Double): @@[T, U] = ev.mul(l.value, r).attachUnit[U]

    override def div(l: @@[T, U], r: Double): @@[T, U] = ev.div(l.value, r).attachUnit[U]
  }

}

trait LowPriority1 extends LowPriority2 {

  implicit def unitsSummable[U, T](implicit ev: Group[T]): Group[T @@ U] = new Group[T @@ U] {
    override def id: @@[T, U] = ev.id.attachUnit[U]

    override def op(l: @@[T, U], r: @@[T, U]): @@[T, U] = ev.op(l.value, r.value).attachUnit[U]

    override def inverse(l: @@[T, U]): @@[T, U] = ev.inverse(l.value).attachUnit[U]
  }
}

trait LowPriority2 {

  implicit def unitsMonoid[U, T](implicit ev: Monoid[T]): Monoid[T @@ U] = new Monoid[T @@ U] {
    override def id: @@[T, U] = ev.id.attachUnit[U]

    override def op(l: @@[T, U], r: @@[T, U]): T @@ U = ev.op(l.value, r.value).attachUnit[U]
  }
}
