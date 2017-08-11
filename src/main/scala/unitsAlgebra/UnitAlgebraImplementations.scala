package unitsAlgebra

import algebra.ring.AdditiveCommutativeGroup
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

  implicit def unitsSummable[U, T](implicit ev: AdditiveCommutativeGroup[T]): AdditiveCommutativeGroup[T @@ U] = new AdditiveCommutativeGroup[T @@ U] {

    override def zero: @@[T, U] = ev.zero.attachUnit[U]

    override def plus(x: @@[T, U], y: @@[T, U]): @@[T, U] = ev.plus(x.value, y.value).attachUnit[U]

    override def negate(l: @@[T, U]): @@[T, U] = ev.negate(l.value).attachUnit[U]
  }
}

trait LowPriority2 {

  implicit def unitsGroup[U, T](implicit ev: AdditiveMonoid[T]): AdditiveMonoid[T @@ U] = new AdditiveMonoid[T @@ U] {
    override def zero: @@[T, U] = ev.zero.attachUnit[U]

    override def plus(l: @@[T, U], r: @@[T, U]): T @@ U = ev.plus(l.value, r.value).attachUnit[U]
  }
}
