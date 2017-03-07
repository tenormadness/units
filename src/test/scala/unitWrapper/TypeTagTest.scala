package typeTag

import unitWrapper.UnitContainer._
import unitsAlgebra.UnitsImplementations._

object TypeTagTest extends App {


  val oneMeter: Double @@ Meter = 1.0.@@[Meter]

  val one: Double = oneMeter.value

  println(oneMeter)

  println(one)

  (one, oneMeter)

}
