package categories

import spire.algebra.Group

trait VectorSpace[T] extends Group[T] {

  def mul(l: T, r: Double): T
  def div(l: T, r: Double): T

}