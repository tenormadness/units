package unitWrapper

import scala.language.higherKinds


object UnitContainer {

  // V @@ U    value @@ unit  also Double @@ DollarUnit = Dollars
  class @@[+V, U](val in: V) extends AnyVal {

    def value: V = in

    override def toString: String = in.toString + " with some unknown unit"
  }

  implicit class Wrapper[V](in: V) {

    def @@[U](tag: U): V @@ U = new @@[V, U](in)

    def attachUnit[U]: V @@ U = new @@[V, U](in)

  }
}

