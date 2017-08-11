package categories

import spire.algebra._
/**
  * Created by luca on 12/8/16.
  */
object AlgebraImplementations {

  implicit object DoubleAlgebra extends VectorSpace[Double, Double] with Group[Double] with Field[Double] {
    override def times(l: Double, r: Double): Double = l * r

    override def div(l: Double, r: Double): Double = l / r
    override def empty: Double = 0.0
    override def combine(x: Double, y: Double): Double = x + y
    override def inverse(a: Double): Double = -a

    override implicit def scalar: Field[Double] = implicitly

    override def timesl(r: Double, v: Double): Double = r * v

    override def quot(a: Double, b: Double): Double = a/b

    override def mod(a: Double, b: Double): Double = mod(a, b)

    override def lcm(a: Double, b: Double)(implicit ev: Eq[Double]): Double = (a*b).abs/gcd(a,b)

    override def gcd(a: Double, b: Double)(implicit ev: Eq[Double]): Double = if(b == empty) a else gcd(b, mod(a,b))

    override def one: Double = 1.0

    override def negate(x: Double): Double = -x

    override def zero: Double = 0.0

    override def plus(x: Double, y: Double): Double = x + y
  }

  implicit object IntAlgebra extends Group[Int] with Field[Int] {

    override def inverse(a: Int): Int = -a

    override def quot(a: Int, b: Int): Int = a / b

    override def mod(a: Int, b: Int): Int = mod(a, b)

    override def gcd(a: Int, b: Int)(implicit ev: Eq[Int]): Int = if(b == empty) a else gcd(b, mod(a,b))

    override def lcm(a: Int, b: Int)(implicit ev: Eq[Int]): Int = (a*b).abs/gcd(a,b)

    override def one: Int = 1

    override def times(x: Int, y: Int): Int = x * y

    override def negate(x: Int): Int = -x

    override def zero: Int = 0

    override def plus(x: Int, y: Int): Int = x + y

    override def div(l: Int, r: Int): Int = l / r
    override def empty: Int = 0
    override def combine(x: Int, y: Int): Int = x + y
  }

  implicit object VectorAlgebra extends VectorSpace[(Double, Double), Double] with Group[(Double, Double)] {

    override implicit def scalar: Field[Double] = implicitly

    override def timesl(r: Double, v: (Double, Double)): (Double, Double) = (v._1 * r, v._2 * r)

    override def negate(x: (Double, Double)): (Double, Double) = (-x._1, -x._2)

    override def zero: (Double, Double) = (0.0, 0.0)

    override def plus(x: (Double, Double), y: (Double, Double)): (Double, Double) = (x._1+y._1, x._2+y._2)

    override def inverse(a: (Double, Double)): (Double, Double) = (-a._1, -a._2)

    override def empty: (Double, Double) = (0.0, 0.0)

    override def combine(x: (Double, Double), y: (Double, Double)): (Double, Double) = (x._1+y._1, x._2+y._2)
  }
}
