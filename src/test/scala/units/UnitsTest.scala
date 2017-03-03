package units

import unitWrapper.UnitContainer._
import main.scala.units.UnitsDefinitions._
import unitsAlgebra.UnitsOps._
import categories.AlgebraImplementations._
import unitsAlgebra.UnitsImplementations._
import categories.AlgebraOps._
import categories.Ring

import Predef.{any2stringadd => _, _}
import org.scalatest.FlatSpec
import unitsAlgebra.UnitAlgebraImplementations._


class UnitsTest extends FlatSpec {
  
  private implicit class IntelligentEquality[T](l: T) {
    def typeSafeEqual(r: T): Boolean = l == r  //TODO: type safe equal
  }


  "Monoind and summable Ops" should "work" in {
    // categories work

    val four = 1.0 |+| 3.0
    assert(four == 4.0)

    val oneVector = (1.0, 1.0)

    assert(oneVector + oneVector == (2.0, 2.0))
  }

  "Summable and monoid operations on units" should "work" in {
    // you can sum
    val oneMeter: Double @@ Meter.type = 1.0 @@ Meter
    val twoMeters = 2.0 @@ Meter
  //
    val threeMeters: Double @@ Meter.type = oneMeter |+| twoMeters //kill this thing don't be fancy
    val threeMetersAsSum: Double @@ Meter.type = oneMeter + twoMeters
    val negOneMeter: Double @@ Meter.type = oneMeter - twoMeters

    val negation = -twoMeters
  //
    assert(threeMeters typeSafeEqual 3.0 @@ Meter)
    assert(threeMetersAsSum typeSafeEqual 3.0 @@ Meter)
    assert(negOneMeter typeSafeEqual -1.0 @@ Meter)
    assert(negation typeSafeEqual -2.0 @@ Meter)

  }

  "sum operations on vectors" should "preserve units" in {
    val arrayMeter: (Double, Double) @@ Meter = (1.0, 2.0) @@ Meter

    val selfSum = arrayMeter + arrayMeter
    val zeroArray = arrayMeter - arrayMeter
    val negArray = -arrayMeter

    assert(selfSum typeSafeEqual (2.0, 4.0) @@ Meter)
    assert(zeroArray typeSafeEqual (0.0, 0.0) @@ Meter)
    assert(negArray typeSafeEqual (-1.0, -2.0) @@ Meter)
  }

  "array ops" should "Work in pre and post mode" in {

    val oneMeterPerSecond = 1.0 @@ MeterPerSecond
    val twoSeconds = 2.0 @@ Second

    val oneSecond = twoSeconds / 2.0
    val fourSeconds = 2.0 * twoSeconds
    val fourSecondsPostmul = twoSeconds * 2.0
  }

  "vector space ops without units" should "not cause collisions" in {

    val vector = (1.0, 2.0)

    val result1 = vector * 2.0
    val result2 = 3.0 * vector
    val result3 = vector / 4.0

    assert(result1 typeSafeEqual (2.0, 4.0))
    assert(result2 typeSafeEqual (3.0, 6.0))
    assert(result3 typeSafeEqual (0.25, 0.5))


  }

  "ring multiplicative Ops" should "return the correct unit" in {

import unitsAlgebra.UnitDivision._

    val twoSecond = 2.0 @@ Second
    val velocity = 5.0 @@ MeterPerSecond
    val distance = 10.0 @@ Meter

    val divTest1 = distance / velocity
    val divTest2 = distance / twoSecond

    val mulTest1 = velocity * twoSecond
    val mulTest2 = twoSecond * velocity

    val pureRatio = twoSecond / twoSecond

    assert(divTest1 typeSafeEqual 2.0 @@ Second)
    assert(divTest2 typeSafeEqual 5.0 @@ MeterPerSecond)

    assert(mulTest1 typeSafeEqual 10.0 @@ Meter)
    assert(mulTest2 typeSafeEqual 10.0 @@ Meter)

  }

  "Unit multiplicative Ops for vectors with unit" should "just work" in {

    val twoSecond = 2.0 @@ Second
    val velocity = 5.0 @@ MeterPerSecond
    val velocityVector = (5.0, 10.0) @@ MeterPerSecond
    val distance = (10.0, 20.0) @@ Meter

    val divTest1 = distance / velocity //doesn't really make sense from a physics prospect but...
    val divTest2 = distance / twoSecond

    val mulTest1 = velocityVector * twoSecond
    val mulTest2 = twoSecond * velocityVector //TODO: MAKE THIS WORK!

    assert(divTest1 typeSafeEqual (2.0, 4.0) @@ Second)
    assert(divTest2 typeSafeEqual (5.0, 10.0) @@ MeterPerSecond)

    assert(mulTest1 typeSafeEqual (10.0, 20.0) @@ Meter)
    assert(mulTest2 typeSafeEqual (10.0, 20.0) @@ Meter)
  }

  "defining a simple Ring Class" should "not interfere with uits" in {

    trait Boole extends Product with Serializable
    case object O extends Boole
    case object X extends Boole

    implicit object SomeRingIsRing extends Ring[Boole] {
      override def mul(l: Boole, r: Boole): Boole = if (l == O || r == O) O else X
      override def div(l: Boole, r: Boole): Boole = throw new NotImplementedError() //improper but who cares
      override def zero: Boole = O
      override def append(l: Boole, r: Boole): Boole = if (l == X || r == X) X else O
      override def minus(l: Boole, r: Boole): Boole = throw new NotImplementedError() //improper but who cares
      override def unaryMinus(l: Boole): Boole = if (l == X) O else X
    }

    val o: Boole = O
    val x: Boole = X

    val sum = SumOps(o) + o

    assert(x * o == o)
    assert(o + o == o)
    assert(o + x == x)
    assert(-x == o)

  }


}
