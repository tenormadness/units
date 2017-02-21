package units

import main.scala.unitWrapper.UnitContainer._
import main.scala.units.UnitsDefinitions._

import Predef.{any2stringadd => _, _}
import categories.AlgerbraOps._
import categories.AlgebraImplementations._
import unitsAlgebra.UnitAlgebraImplementations._
import unitsAlgebra.UnitsOps._

object UnitsTest extends App {

  // categories work

  val four = 1.0 |+| 3.0
  assert(four == 4.0)


  // you can sum
  val oneMeter: Double @@ Meter.type = 1.0 @@ Meter
  val twoMeters = 2.0 @@ Meter
//
  val threeMeters: Double @@ Meter.type = oneMeter |+| twoMeters
  val threeMetersAsSum: Double @@ Meter.type = oneMeter + twoMeters
//
  println(threeMeters)
  println(threeMetersAsSum)

  // also arrays!
  val arrayMeter: (Double, Double) @@ Meter = (1.0, 2.0) @@ Meter

  val selfSum = arrayMeter + arrayMeter
  val zeroArray = arrayMeter - arrayMeter

  println(selfSum)
  println(zeroArray)

  //But only of the same unit!
  val oneMeterPerSecond = 1.0 @@ MeterPerSecond
  val twoSecond = 2.0 @@ Second


//  val distance: Double @@ Meter = oneMeterPerSecond * twoSecond
//  val distance2: Double @@ Meter = 2.0 * twoSecond * oneMeterPerSecond
//  val distance3 = twoSecond * 2.0 * oneMeterPerSecond
//  val distance3: Double @@ Meter = twoSecond * 2.0 * oneMeterPerSecond
//  println(distance)
//
//  // on arrays
//  val arrayMul1 = arrayMeter * 2.0
//  val arrayMul2 = 2.0 * arrayMeter / 3.0
//
//  // array with units
//  val velocityVector = (-1.0, -1.0) @@ MeterPerSecond
//  val arrayMul3 = arrayMeter / twoSecond
//
//  val threeSeconds = 3.0 @@ Second
//
//  val arraydiv = arrayMeter / twoSecond * threeSeconds

  //val SelfDivTest = twoSecond / threeSeconds * oneMeter //todo: fix this


  //and multiply it back!
//
//  def plus2(a: Double) = a + 2

}
