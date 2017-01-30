package main.scala.categories

/**
  * Created by luca on 12/8/16.
  */
trait AlgebraImplementations {

  implicit object DoubleAlgebra extends Vector[Double] with Summable[Double] with Ring[Double] {
    override def mul(l: Double, r: Double): Double = l * r
    override def div(l: Double, r: Double): Double = l / r
    override def zero: Double = 0.0
    override def append(l: Double, r: Double): Double = l + r
    override def minus(l: Double, r: Double): Double = l - r
    override def unaryMinus(l: Double): Double = -l
  }

  implicit object VectorAlgebra extends Vector[(Double, Double)] {
    override def mul(l: (Double, Double), r: Double): (Double, Double) = (l._1*r, l._2*r)

    override def div(l: (Double, Double), r: Double): (Double, Double) = (l._1/r, l._2/r)

    override def minus(l: (Double, Double), r: (Double, Double)): (Double, Double) = (l._1-r._1, l._2-r._2)

    override def zero: (Double, Double) = (0.0, 0.0)

    override def append(l: (Double, Double), r: (Double, Double)): (Double, Double) = (l._1+r._1, l._2+r._2)

    override def unaryMinus(l: (Double, Double)): (Double, Double) = (-l._1, -l._2)
  }
}
