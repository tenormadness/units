package units

import categories.AlgebraImplementations._
import unitsAlgebra._
import categories.AlgebraOps._
import spire.algebra._
import unitsAlgebra.UnitsImplementations._
import org.scalatest.FlatSpec
import unitWrapper.UnitContainer._

class UnitsTest extends FlatSpec {
  
  private implicit class IntelligentEquality[T](l: T) {
    def typeSafeEqual(r: T): Boolean = l == r
  }


  "Monoid and summable Ops" should "work" in {
    // categories work
    import spire.syntax.monoid._

    val four = 1.0 |+| 3.0
    assert(four == 4.0)

    val oneVector = (1.0, 1.0)

    assert(SumOps(oneVector)(VectorAlgebra) + oneVector == (2.0, 2.0))

    //todo: test with units
  }

  "Summable operations on units" should "work" in {
    // you can sum
    val oneMeter: Double @@ Meter = 1.0.@@[Meter]
    val twoMeters = 2.0.@@[Meter]
  //
    val threeMetersAsSum: Double @@ Meter = oneMeter + twoMeters
    val negOneMeter: Double @@ Meter = oneMeter - twoMeters

    val negation = -twoMeters
  //
    assert(threeMetersAsSum typeSafeEqual 3.0.@@[Meter])
    assert(negOneMeter typeSafeEqual -1.0.@@[Meter], s"$negOneMeter does not equal -1.0")
    assert(negation typeSafeEqual -2.0.@@[Meter])

  }

  "Sum operations on vectors" should "preserve units" in {
    val arrayMeter: (Double, Double) @@ Meter = (1.0, 2.0).@@[Meter]

    val selfSum = arrayMeter + arrayMeter
    val zeroArray = arrayMeter - arrayMeter
    val negArray = -arrayMeter

    assert(selfSum typeSafeEqual (2.0, 4.0).@@[Meter])
    assert(zeroArray typeSafeEqual (0.0, 0.0).@@[Meter])
    assert(negArray typeSafeEqual (-1.0, -2.0).@@[Meter])
  }

  "Array ops" should "Work in pre and post mode" in {

    val oneMeterPerSecond = 1.0.@@[MeterPerSecond]
    val twoSeconds = 2.0.@@[Second]

    val oneSecond = twoSeconds / 2.0
    val fourSeconds = 2.0 * twoSeconds
    val fourSecondsPostmul = twoSeconds * 2.0
  }

  "Vector space ops without units" should "not cause collisions" in {

    val vector = (1.0, 2.0)

    val result1 = vector * 2.0
    val result2 = 3.0 * vector
    val result3 = vector / 4.0

    assert(result1 typeSafeEqual (2.0, 4.0))
    assert(result2 typeSafeEqual (3.0, 6.0))
    assert(result3 typeSafeEqual (0.25, 0.5))


  }

  "Ring multiplicative Ops" should "return the correct unit" in {

    val twoSecond = 2.0.@@[Second]
    val velocity = 5.0.@@[MeterPerSecond]
    val distance = 10.0.@@[Meter]

    val divTest1 = distance / velocity
    val divTest2 = distance / twoSecond

    val mulTest1 = velocity * twoSecond
    val mulTest2 = twoSecond * velocity

    val pureRatio = twoSecond / twoSecond

    assert(divTest1 typeSafeEqual 2.0.@@[Second])
    assert(divTest2 typeSafeEqual 5.0.@@[MeterPerSecond])

    assert(mulTest1 typeSafeEqual 10.0.@@[Meter])
    assert(mulTest2 typeSafeEqual 10.0.@@[Meter])

  }

  "Unit multiplicative Ops for vectors with unit" should "just work" in {

    val twoSecond = 2.0.@@[Second]
    val velocity = 5.0.@@[MeterPerSecond]
    val velocityVector = (5.0, 10.0).@@[MeterPerSecond]
    val distance = (10.0, 20.0).@@[Meter]

    val divTest1 = distance / velocity //doesn't really make sense from a physics prospect but...
    val divTest2 = distance / twoSecond

    val mulTest1 = velocityVector * twoSecond
    val mulTest2 = twoSecond * velocityVector

    assert(divTest1 typeSafeEqual (2.0, 4.0).@@[Second])
    assert(divTest2 typeSafeEqual (5.0, 10.0).@@[MeterPerSecond])

    assert(mulTest1 typeSafeEqual (10.0, 20.0).@@[Meter])
    assert(mulTest2 typeSafeEqual (10.0, 20.0).@@[Meter])
  }

  "Defining a simple Ring Class" should "not interfere with units" in {

    trait Boole extends Product with Serializable
    case object O extends Boole
    case object X extends Boole

    implicit object SomeRingIsRing extends Group[Boole] {

      override def inverse(a: Boole): Boole = if (a == X) O else X

      override def empty: Boole = X

      override def combine(x: Boole, y: Boole): Boole = if (x == X && y == X) X else O

    }

    val oMeter: Boole @@ Meter = O.@@[Meter]
    val xMeter: Boole @@ Meter = X.@@[Meter]

    //assert(x * o == o) // TODO: Put this back in Rings ain't hard now
    assert(oMeter + oMeter == O.@@[Meter])
    assert(oMeter + xMeter == O.@@[Meter])
    assert(xMeter + xMeter == X.@@[Meter])
    assert(-xMeter == O.@@[Meter])

  }

  "Chaining together operations with different units" should "not create problems, units are resolved as we go" in {

    val test1: Double @@ MeterPerSecondSq = 7.0 * (1.0.@@[Meter] / 2.0.@@[Second] + 1.5.@@[MeterPerSecond]) / 4.0.@@[Second] / 0.025

    assert(test1 typeSafeEqual 140.0.@@[MeterPerSecondSq])

  }

  "Squaring things up " should "work well" in {

    val squareMeter = 2.0.@@[Meter] * 3.0.@@[Meter]

    val backToMeter = squareMeter / 4.0.@@[Meter]

    assert(squareMeter typeSafeEqual 6.0.@@[MeterSq])
    assert(backToMeter typeSafeEqual 1.5.@@[Meter])

  }
}
