package typeTag

import scala.language.higherKinds

trait TypeTagDef {

  type TypeTag[U, V]
}


object TypeTag {

  type @@[U, V] = TypeTagDef#TypeTag[U, V] {

    type unwrap = U

    type tag = V
  }


  implicit class Unwrapper[U, V](in: U @@ V) {

    def unwrap: @@[U, V]#unwrap = in.asInstanceOf[U]

    //def apply[UU](f: U => UU) = f(in.asInstanceOf[U]).wrap[V] // I don't like this

  }

  implicit class Wrapper[U](in: U) {

    def @@[V](tag: V): U @@ V = in.asInstanceOf[U @@ V]

    def wrap[V]: U @@ V = in.asInstanceOf[U @@ V]


  }
}

