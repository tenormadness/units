package typeTag

import typeTag.TypeTag._
import units.UnitsDefinitions._


object TypeTagTest extends App {


  val oneMeter: Double @@ Meter = 1.0 @@ meter

  val one: Double = oneMeter.unwrap

  println(oneMeter)

  println(one)

  (one, oneMeter)

}
