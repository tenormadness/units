package categories

trait VectorSpace[T] extends Summable[T] {

  def mul(l: T, r: Double): T
  def div(l: T, r: Double): T

}