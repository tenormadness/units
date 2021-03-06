package unitWrapper

import algebra.Eq

import scala.language.higherKinds

class UnitContainer[+V, U](val in: V) extends AnyVal {  // V @@ U

  def value: V = in

  override def toString: String = in.toString + " with some unknown unit"
}

object UnitContainer {

  type @@[+V, U] = UnitContainer[V, U]

  implicit class Wrapper[V](in: V) {

    def @@[U]: V @@ U = new @@[V, U](in)

    def @@[U](tag: U): V @@ U = new @@[V, U](in)

    def attachUnit[U]: V @@ U = new @@[V, U](in)

  }

  // type safe equality
  implicit def UnitEquality[V, U]: Eq[UnitContainer[V, U]] = new Eq[UnitContainer[V, U]] {
    override def eqv(x: @@[V, U], y: @@[V, U]): Boolean = x.value == y.value
  }

}

