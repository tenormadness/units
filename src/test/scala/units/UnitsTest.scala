package units

import typeTag.TypeTag._
import units.UnitsDefinitions._

import Predef.{any2stringadd => _, _}
import unitsAlgebra.UnitOperationSyntax._
import unitsAlgebra.BasicOperationImplementations._

import scala.math.Numeric._

object UnitsTest extends App {

  import unitsAlgebra.ImplicitUnitResolution._

  // you can sum
  val oneMeter: Double @@ Meter = 1.0 @@ meter
  val twoMeters = 2.0 @@ meter

  val threeMeters = oneMeter + twoMeters

  println(threeMeters)


  // even integers
  val oneMeteri = 1 @@ meter
  val twoMetersi = 2 @@ meter

  val threeMetersi: Int @@ Meter = oneMeteri + twoMetersi

  println(threeMetersi)


  // Or arrays!
  val arrayMeter: Seq[Double] @@ Meter = Seq(1.0, 2.0) @@ meter

  val selfSum: Seq[Double] @@ Meter = arrayMeter + arrayMeter

  println(selfSum)  //Seq(1.0, 2.0) @@ Meter

  //But only of the same unit!
  val oneSecond = 1.0 @@ second
  val error = oneSecond + oneMeter // compiles
  //val error2: Double @@ Meter = oneSecond + oneMeter // but this! does not compile!

  println(s"auch! $error")

  // I can divide

  val velocity: Double @@ MeterPerSecond = twoMeters / oneSecond
  println(velocity)

  //and multiply it back!
  val meterFromVelocity1 = velocity * oneSecond
  val meterFromVelocity2 = oneSecond * velocity
  println(meterFromVelocity1)
  println(meterFromVelocity2)

  def plus2(a: Double) = a + 2

}
