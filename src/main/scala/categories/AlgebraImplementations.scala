package categories

/**
  * Created by luca on 12/8/16.
  */
object AlgebraImplementations {

  implicit object DoubleAlgebra extends VectorSpace[Double] with Summable[Double] with Ring[Double] {
    override def mul(l: Double, r: Double): Double = l * r
    override def div(l: Double, r: Double): Double = l / r
    override def zero: Double = 0.0
    override def append(l: Double, r: Double): Double = l + r
    override def minus(l: Double, r: Double): Double = l - r
    override def unaryMinus(l: Double): Double = -l
  }

  implicit object IntAlgebra extends Summable[Int] with Ring[Int] {

    override def mul(l: Int, r: Int): Int = l * r
    override def div(l: Int, r: Int): Int = l / r
    override def zero: Int = 0
    override def append(l: Int, r: Int): Int = l + r
    override def minus(l: Int, r: Int): Int = l - r
    override def unaryMinus(l: Int): Int = -l
  }

  implicit object VectorAlgebra extends VectorSpace[(Double, Double)] {
    override def mul(l: (Double, Double), r: Double): (Double, Double) = (l._1*r, l._2*r)

    override def div(l: (Double, Double), r: Double): (Double, Double) = (l._1/r, l._2/r)

    override def minus(l: (Double, Double), r: (Double, Double)): (Double, Double) = (l._1-r._1, l._2-r._2)

    override def zero: (Double, Double) = (0.0, 0.0)

    override def append(l: (Double, Double), r: (Double, Double)): (Double, Double) = (l._1+r._1, l._2+r._2)

    override def unaryMinus(l: (Double, Double)): (Double, Double) = (-l._1, -l._2)
  }
}
