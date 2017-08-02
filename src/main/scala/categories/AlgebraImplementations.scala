package categories

import spire.algebra._
/**
  * Created by luca on 12/8/16.
  */
object AlgebraImplementations {

  implicit object DoubleAlgebra extends VectorSpace[Double] with Group[Double] with Field[Double] {
    override def times(l: Double, r: Double): Double = l * r

    override def div(l: Double, r: Double): Double = l / r
    override def id: Double = 0.0
    override def op(x: Double, y: Double): Double = x + y
    override def inverse(a: Double): Double = 1/a

    override def mul(l: Double, r: Double): Double = l*r

    override def quot(a: Double, b: Double): Double = a/b

    override def mod(a: Double, b: Double): Double = mod(a, b)

    override def gcd(a: Double, b: Double): Double = throw new IllegalStateException("gigawhat!")

    override def one: Double = 1.0

    override def negate(x: Double): Double = -1

    override def zero: Double = 0.0

    override def plus(x: Double, y: Double): Double = x+y
  }

  implicit object IntAlgebra extends Group[Int] with Field[Int] {

    override def inverse(a: Int): Int = -a

    override def quot(a: Int, b: Int): Int = a / b

    override def mod(a: Int, b: Int): Int = mod(a, b)

    override def gcd(a: Int, b: Int): Int = throw new IllegalStateException("Gigawhat!")

    override def one: Int = 1

    override def times(x: Int, y: Int): Int = x * y

    override def negate(x: Int): Int = -x

    override def zero: Int = 0

    override def plus(x: Int, y: Int): Int = x + y

    override def div(l: Int, r: Int): Int = l / r
    override def id: Int = 0
    override def op(x: Int, y: Int): Int = x+y
  }

  implicit object VectorAlgebra extends VectorSpace[(Double, Double)] {
    override def mul(l: (Double, Double), r: Double): (Double, Double) = (l._1*r, l._2*r)

    override def div(l: (Double, Double), r: Double): (Double, Double) = (l._1/r, l._2/r)
    override def id: (Double, Double) = (0.0, 0.0)

    override def op(l: (Double, Double), r: (Double, Double)): (Double, Double) = (l._1+r._1, l._2+r._2)

    override def inverse(l: (Double, Double)) = (-l._1, -l._2)
  }
}
